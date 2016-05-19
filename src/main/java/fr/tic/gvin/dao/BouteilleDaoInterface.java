//
// Fichier :
// BouteilleDaoInterface.java, v 1.0 19 mai 2016 14:56:51
//
// Projet :
// LAQME
//
// Ministère :
// Ministère de l'économie et des finances.
//
// Direction :
// DGDDI
//
// Prestation :
// Bull
//
// Copyright :
// DGDDI 2004-2016
//
// Date :
// Créé le 19 mai 2016 à 14:56:51
//
// Historique :
//
// Révision 1.0 19 mai 2016 14:56:51 caiatit
// Création.
//
//
package fr.tic.gvin.dao;


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
     * enregistre une bouteille
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
