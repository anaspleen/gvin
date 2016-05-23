//
// Fichier :
// enregistrerBouteille.java, v 1.0 19 mai 2016 14:54:35
package fr.greeniot.ambrosia.service;


import java.util.Map;

import fr.greeniot.commun.exception.BusinessException;
import fr.greeniot.commun.exception.TechnicalException;


/**
 * Service de gestion des bouteilles
 * 
 * @author Bull
 *         $Id$
 */
public interface BouteilleServiceInterface
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
     * @throws BusinessException
     *             soucis applicatif
     * @throws TechnicalException
     *             soucis technique
     */
    void enregistrerBouteille(Map<String, Object> p_Valeurs, double p_Longitude, double p_Latitude)
            throws BusinessException, TechnicalException;
}
