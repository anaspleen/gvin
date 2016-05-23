//
// Fichier :
// BouteilleDaoInterface.java, v 1.0 19 mai 2016 14:56:51
//
package fr.greeniot.ambrosia.dao;


import org.bson.Document;


/**
 * Dao de gestion des bouteilles
 * 
 * @author Bull
 *         $Id$
 */
public interface BouteilleDaoInterface
{

    /**
     * Enregistre une bouteille
     * 
     * @param p_Valeurs
     *            les valeurs
     * @param p_Longitude
     *            longitude
     * @param p_Latitude
     *            latitude
     */
    void save(Document p_Document);
}
