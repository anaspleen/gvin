//
// Fichier :
// enregistrerBouteille.java, v 1.0 19 mai 2016 14:54:35
package fr.greeniot.ambrosia.service;


import java.util.List;
import java.util.Map;

import org.bson.Document;

import fr.greeniot.ambrosia.service.api.rest.APIRESTServiceInterface;
import fr.greeniot.exception.BusinessException;
import fr.greeniot.exception.TechnicalException;


/**
 * Service de gestion des bouteilles
 * 
 * @author Bull
 *         $Id$
 */
public interface BouteilleServiceInterface extends APIRESTServiceInterface
{

    /**
     * enregistre une bouteille : gvin/src/test/resources/bouteille/bouteille-st-emilion-valide.json
     * 
     * @param p_Valeurs
     *            les valeurs
     * @param p_Longitude
     *            longitude
     * @param p_Latitude
     *            latitude
     * @return l'id en base
     * @throws BusinessException
     *             soucis applicatif
     * @throws TechnicalException
     *             soucis technique
     */
    String enregistrerBouteille(Map<String, Object> p_Valeurs, double p_Longitude, double p_Latitude)
            throws BusinessException, TechnicalException;

    /**
     * Obtention de la bouteille
     * 
     * @param p_ObjectId
     *            l'id
     * @return l'objet si trouvé
     * @throws BusinessException
     *             soucis applicatif
     * @throws TechnicalException
     *             soucis technique
     */
    Document obtenirBouteille(String p_ObjectId) throws BusinessException, TechnicalException;

    /**
     * Obtention toutes les bouteilles en fonction d'attributs
     * 
     * @param p_ObjectId
     *            l'id
     * @return l'objet si trouvé
     * @throws BusinessException
     *             soucis applicatif
     * @throws TechnicalException
     *             soucis technique
     */
    List<Document> obtenirBouteilles(Map<String, String> p_Valeurs) throws BusinessException, TechnicalException;
}
