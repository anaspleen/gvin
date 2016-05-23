//
// Fichier :
// ConfigurationServiceInterface.java, v 1.0 12 mars 2015 10:42:58
//
package fr.greeniot.ambrosia.api.rest.resource;


import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.greeniot.ambrosia.service.ServiceLocator;
import fr.greeniot.ambrosia.service.api.rest.CommandServiceInterface;
import fr.greeniot.ambrosia.utils.ConstantesAPIREST;
import fr.greeniot.commun.exception.BusinessException;
import fr.greeniot.commun.exception.TechnicalException;


/**
 * REST Service
 */
@Path("/")
public class ObtenirAmbrosia
{

    private static final String IF_NONE_MATCH = "If-None-Match";
    private static final String IF_MATCH = "If-Match";

    private static final String CACHE_CONTROL = "Cache-Control";
    private static final String ENTITY_TAG = "ETag";

    private static final String ERREUR_SERVEUR = "erreurServeur";
    /**
     * Le logge de la classe.
     */
    private final static Logger LOG = LoggerFactory.getLogger(ObtenirAmbrosia.class);

    /**
     * Creates a new instance of RelationResource
     */
    public ObtenirAmbrosia()
    {
    }

    /**
     * Réponse de base de l'api nomenclature.
     * 
     * @returnje
     * @throws BusinessException
     * @throws TechnicalException
     */
    @GET
    public Response index() throws BusinessException, TechnicalException
    {
        return Response.status(Status.OK).entity("API AMBROSIA").build();

    }

    /**
     * @return le service
     */
    private CommandServiceInterface getCommandService()
    {
        return ServiceLocator.getServiceFactory().getCommandService();
    }

    private Response getResponse(Document p_JsonObjet)
    {
        return Response.status(Status.OK).entity(p_JsonObjet.toString()).header("Vary", "Accept, Accept-Encoding")
                .header(CACHE_CONTROL, "public, max-age=3600").header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
    }

    /**
     * Traitement JSON de la réponse
     * 
     * @param p_Msg
     *            le message
     * @param p_E
     *            l'exception
     * @return la string
     */
    private String traitementErreur(String p_Msg, Exception p_E)
    {
        JSONObject json = new JSONObject();
        LOG.error(p_Msg);
        json.put(ERREUR_SERVEUR, p_Msg);

        return json.toString();
    }

    /*
     * Returns the ETag value in the given header from the provided request
     * context. Returns null if there is no ETag or there are multiple ETAG
     * values.
     */
    private String getETagFromRequest(HttpHeaders headers, String header)
    {
        List<String> eTags = headers.getRequestHeader(header);
        if (eTags != null && eTags.size() == 1)
        {
            return eTags.get(0);
        }
        return null;
    }

    /*
     * Returns true if the ETag value in the If-None-Match header matches the ETag
     * of the resource in the server cache. Meaning the requesting client has the
     * latest version of the resource.
     */
    private boolean isNotModified(HttpHeaders headers, String etagCourant)
    {
        String eTag = getETagFromRequest(headers, IF_NONE_MATCH);

        if (StringUtils.isNotBlank(eTag) && StringUtils.isNotBlank(etagCourant))
        {
            if (eTag.equals(etagCourant))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Action générale
     * 
     * @param p_Json
     *            le json
     * @return le retour
     */
    @POST
    @Path("/execute/{domaine}/{action}")
    @Consumes("application/json; charset=UTF-8")
    @Produces(MediaType.APPLICATION_JSON)
    public Response execute(InputStream p_JsonInputStream, @PathParam("domaine") final String p_Domaine,
            @PathParam("action") final String p_Action)
    {
        String retour = "";
        int codeRetour = 500;

        try
        {
            // chope la session courante qui contient le login de l'agent connecté
            //            Session session = GestionnaireSessions.donnerSession(true);

            Document requeteJsonPure = Document.parse(IOUtils.toString(p_JsonInputStream, "UTF-8"));

            // ajout de code avant le message car ça peut servir à faire passer des trucs du front au back facilement
            Document requeteJsonTravaillee = new Document();
            requeteJsonTravaillee.put("code", 200);
            requeteJsonTravaillee.put("message", "Ok");
            requeteJsonTravaillee.put(ConstantesAPIREST.TAG_REQUETE_ENVOYEE, requeteJsonPure);

            // appel du service avec le JSON enrichi par l'API
            // TODO l'utilisateur
            //            JSONObject reponseJsonPure = getCommandService().execute(p_Domaine, p_Action, requeteJsonTravaillee,
            //                    session.getUtilisateur());
            Document reponseJsonPure = getCommandService().execute(p_Domaine, p_Action, requeteJsonTravaillee, null);

            // ajout d'un bloc serveur - si arrivé ici, tout va bien
            Document retourServeurJson = new Document();
            retourServeurJson.put("code", 200);
            retourServeurJson.put("message", "Ok");

            // ajout du tag "reponse"
            Document retourJson = new Document();
            retourJson.put(ConstantesAPIREST.TAG_REQUETE, requeteJsonPure);
            retourJson.put(ConstantesAPIREST.TAG_REPONSE, reponseJsonPure);
            retourJson.put(ConstantesAPIREST.TAG_REPONSE_SERVEUR, retourServeurJson);

            // retour du service brut
            return getResponse(retourJson);
        }
        catch (BusinessException e)
        {
            String msg = "Une erreur fonctionnelle est intervenue : " + e.getMessage();
            retour = traitementErreur(msg, e);
            codeRetour = e.getExceptionEnum().getStatutHttp();
        }
        catch (TechnicalException e)
        {
            String msg = "Une erreur technique est intervenue : " + e.getMessage();

            retour = traitementErreur(msg, e);
        }
        catch (Exception e)
        {
            String msg = "Une erreur technique sérieuse est intervenue : " + e;

            retour = traitementErreur(msg, e);
        }

        return Response.status(codeRetour).entity(retour).build();
    }
}
