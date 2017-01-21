//
// Fichier :
// ConfigurationServiceInterface.java, v 1.0 20 mai 2015 15:18:35
//
package fr.greeniot.ambrosia.service.api.rest;


import org.bson.Document;

import fr.greeniot.ambrosia.bean.UtilisateurInterface;
import fr.greeniot.exception.BusinessException;
import fr.greeniot.exception.TechnicalException;


/**
 * Les services qui répondent à l'API REST AMBROSIA pour la webapp IHM. C'est cette Interface qui est appelée par le
 * CommandService. Pour qu'un service soit accessible par l'API, il faut que ce service implémente cette Interface.
 * 
 * @author Bull
 *         $Id$
 */
public interface APIRESTServiceInterface
{

    /**
     * Méthode de GET
     * 
     * @param p_Requete
     *            le JSON Requête
     * @param p_Usager
     *            l'usager provenant de la session
     * @return le JSON résultant
     * @throws BusinessException
     *             soucis applicatif
     * @throws TechnicalException
     *             soucis technique
     */
    Document commandGET(Document p_Requete, UtilisateurInterface p_Usager) throws BusinessException, TechnicalException;

    /**
     * Méthode de POST
     * 
     * @param p_Requete
     *            le JSON Requête
     * @param p_Usager
     *            l'usager provenant de la session
     * @return le JSON résultant
     * @throws BusinessException
     *             soucis applicatif
     * @throws TechnicalException
     *             soucis technique
     */
    Document commandPOST(Document p_Requete, UtilisateurInterface p_Usager) throws BusinessException,
            TechnicalException;
}
