//
// Fichier :
// Etats.java, v 1.0 18 nov. 2015 15:31:18
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
// DGDDI 2004-2015
//
// Date :
// Créé le 18 nov. 2015 à 15:31:18
//
// Historique :
//
// Révision 1.0 18 nov. 2015 15:31:18 caiatit
// Création.
//
//
package fr.tic.gvin.utils;


/**
 * Les constantes de appli
 * 
 * @author Bull
 *         $Id$
 */
public final class ConstantesAMBROSIA
{

    /** tags bouteille */
    public static final String TAG_BOUTEILLE_NOM = "nom";
    public static final String TAG_BOUTEILLE_VIGNOBLE = "vignoble";
    public static final String TAG_BOUTEILLE_AOC = "aoc";
    public static final String TAG_BOUTEILLE_APPELLATION = "appellation";
    public static final String TAG_BOUTEILLE_ANNEE_MISE_EN_BOUTEILLE = "anneeMiseEnBouteille";
    public static final String TAG_BOUTEILLE_ANNEE_CONSOMMATION_OPTIMALE = "anneeConsommationOptimale";

    /** achat bouteille */
    public static final String TAG_BOUTEILLE_ACHAT = "achat";
    public static final String TAG_BOUTEILLE_ACHAT_DATE = "date";
    public static final String TAG_BOUTEILLE_ACHAT_MAGASIN = "magasin";
    public static final String TAG_BOUTEILLE_ACHAT_PRIX = "prix";

    /** location bouteille mongo */
    public static final String TAG_BOUTEILLE_LOCATION = "location";
    public static final String TAG_BOUTEILLE_LOCATION_TYPE = "type";
    public static final String TAG_BOUTEILLE_LOCATION_TYPE_POINT = "Point";
    public static final String TAG_BOUTEILLE_LOCATION_COORDINATES = "coordinates";

    /**
     * Const par défaut
     */
    private ConstantesAMBROSIA()
    {
        // RAF
    }
}
