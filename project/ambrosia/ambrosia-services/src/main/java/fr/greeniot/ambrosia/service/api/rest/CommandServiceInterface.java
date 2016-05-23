//
// Fichier :
// ConfigurationServiceInterface.java, v 1.0 20 mai 2015 15:18:35
//
package fr.greeniot.ambrosia.service.api.rest;


import org.bson.Document;

import fr.greeniot.ambrosia.bean.UtilisateurInterface;
import fr.greeniot.commun.exception.BusinessException;
import fr.greeniot.commun.exception.TechnicalException;


/**
 * Le service "switch" utilisée par l'API IHM.
 * 
 * @author Bull
 *         $Id$
 */
public interface CommandServiceInterface
{

    /**
     * Exécution
     * 
     * @param p_Domaine
     *            le domaine
     * @param p_Action
     *            l'action
     * @param p_Requete
     *            la requête pure
     * @param p_Usager
     *            l'usager venant de la session (pour vérification des droits)
     * @throws BusinessException
     *             soucis applicatif
     * @throws TechnicalException
     *             soucis technique
     */
    Document execute(String p_Domaine, String p_Action, Document p_Requete, UtilisateurInterface p_Usager)
            throws BusinessException, TechnicalException;
}
