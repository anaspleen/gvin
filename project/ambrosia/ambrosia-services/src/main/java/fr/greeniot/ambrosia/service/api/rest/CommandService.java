//
// Fichier :
// CommandService.java, v 1.0 20 nov. 2015 10:50:34
//
package fr.greeniot.ambrosia.service.api.rest;


import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.greeniot.ambrosia.bean.UtilisateurInterface;
import fr.greeniot.ambrosia.service.ServiceLocator;
import fr.greeniot.ambrosia.utils.ConstantesAPIREST;
import fr.greeniot.commun.exception.BusinessException;
import fr.greeniot.commun.exception.TechnicalException;


/**
 * @author thomas
 *         $Id$
 */
public class CommandService implements CommandServiceInterface
{

    /** le logger */
    private final static Logger LOG = LoggerFactory.getLogger(CommandService.class);

    /*
     * (non-Javadoc)
     * @see fr.greeniot.ambrosia.service.api.rest.CommandServiceInterface#execute(java.lang.String, java.lang.String, org.bson.Document, fr.greeniot.ambrosia.bean.UtilisateurInterface)
     */
    public Document execute(String p_Domaine, String p_Action, Document p_Requete, UtilisateurInterface p_Usager)
            throws BusinessException, TechnicalException
    {
        Document requeteEntree = null;

        // prise du JSON de "entree"
        try
        {
            requeteEntree = (Document) p_Requete.get(ConstantesAPIREST.TAG_REQUETE_ENVOYEE);
        }
        catch (Exception e)
        {
            throw new TechnicalException("Le JSON n'est pas au format valide : " + e);
        }

        // appel du bon bean ici déjà casté (evite quand même de pouvoir appeler tous les services)
        APIRESTServiceInterface service = ServiceLocator.getService(p_Domaine);

        if (service == null)
        {
            throw new TechnicalException("Aucun service : " + p_Domaine + " - " + p_Action + " trouvé");
        }

        if (p_Action.equals("GET"))
        {
            return service.commandGET(requeteEntree, p_Usager);
        }
        else if (p_Action.equals("POST"))
        {
            return service.commandPOST(requeteEntree, p_Usager);
        }
        else
        {
            throw new TechnicalException("URL invalide");
        }
    }
}
