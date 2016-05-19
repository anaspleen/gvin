//
// Fichier :
// enregistrerBouteille.java, v 1.0 19 mai 2016 14:54:35
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
// Créé le 19 mai 2016 à 14:54:35
//
// Historique :
//
// Révision 1.0 19 mai 2016 14:54:35 caiatit
// Création.
//
//
package fr.tic.gvin.service;


import java.util.Map;


/**
 * Service de gestion des bouteilles
 * 
 * @author Bull
 *         $Id$
 */
public interface BouteilleServiceInterface
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
    void enregistrerBouteille(Map<String, String> p_Valeurs, int p_Longitude, int p_Latitude);
}
