//
// Fichier :
// CommandService.java, v 1.0 20 nov. 2015 10:50:34
//
package fr.greeniot.ambrosia.service.api.rest;


import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.greeniot.ambrosia.bean.UtilisateurInterface;
import fr.greeniot.commun.exception.BusinessException;
import fr.greeniot.commun.exception.TechnicalException;


/**
 * @author Bull
 *         $Id$
 */
public class CommandService implements CommandServiceInterface
{

    /** le logger */
    private final static Logger LOG = LoggerFactory.getLogger(CommandService.class);

    /*
     * (non-Javadoc)
     * @see fr.greeniot.ambrosia.service.api.rest.CommandServiceInterface#execute(java.lang.String, java.lang.String, org.json.JSONObject, fr.greeniot.ambrosia.bean.UtilisateurInterface)
     */
    public JSONObject execute(String p_Domaine, String p_Action, JSONObject p_Requete, UtilisateurInterface p_Usager)
            throws BusinessException, TechnicalException
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see fr.greeniot.ambrosia.service.api.rest.CommandServiceInterface#execute(java.lang.String, java.lang.String, org.json.JSONObject, fr.greeniot.ambrosia.bean.UtilisateurInterface)
     */
//    @Override
//    public JSONObject execute(String p_Domaine, String p_Action, JSONObject p_Requete, UtilisateurInterface p_Usager)
//            throws BusinessException, TechnicalException
//    {
//        JSONObject requeteEntree = null;
//
//        // prise du JSON de "entree"
//        try
//        {
//            requeteEntree = p_Requete.getJSONObject(ConstantesAPIREST.TAG_REQUETE_ENVOYEE);
//        }
//        catch (JSONException e)
//        {
//            throw new TechnicalException("Le JSON n'est pas au format valide : " + e);
//        }
//
//        // appel du bon bean ici déjà casté (evite quand même de pouvoir appeler tous les services)
//        APIRESTServiceInterface service = ServiceLocator.getService(p_Domaine);
//
//        if (service == null)
//        {
//            throw new TechnicalException("Aucun service : " + p_Domaine + " - " + p_Action + " trouvé");
//        }
//
//        if (p_Action.equals("creation"))
//        {
//            return service.commandCreation(requeteEntree, p_Usager);
//        }
//        else if (p_Action.equals("consultation"))
//        {
//            return service.commandConsultation(requeteEntree, p_Usager);
//        }
//        else
//        {
//            throw new TechnicalException("URL invalide");
//        }
//    }
}
