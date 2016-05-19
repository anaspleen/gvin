//
// Fichier :
// enregistrerBouteille.java, v 1.0 19 mai 2016 14:54:35
package fr.tic.gvin.service;


import java.util.Map;

import fr.tic.gvin.exception.BusinessException;
import fr.tic.gvin.exception.TechnicalException;


/**
 * Service de gestion des bouteilles
 * 
 * @author Bull
 *         $Id$
 */
public interface BouteilleServiceInterface
{

    /**
     * enregistre une bouteille : gvin/src/test/resources/bouteille/bouteille.json
     * 
     * @param p_Valeurs
     *            les valeurs
     * @param p_Longitude
     *            longitude
     * @param p_Latitude
     *            latitude
     * @param p_ValeursAchat
     *            les valeurs achat : prix, date, magasin
     */
    void enregistrerBouteille(Map<String, Object> p_Valeurs, double p_Longitude, double p_Latitude,
            Map<String, Object> p_ValeursAchat) throws BusinessException, TechnicalException;
}
