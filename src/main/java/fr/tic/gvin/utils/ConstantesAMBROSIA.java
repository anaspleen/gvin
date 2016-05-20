//
// Fichier :
// Etats.java, v 1.0 18 nov. 2015 15:31:18
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
    public static final String TAG_BOUTEILLE_CHATEAU = "chateau";

    /** achat bouteille */
    public static final String TAG_BOUTEILLE_ACHAT_DATE = "achatDate";
    public static final String TAG_BOUTEILLE_ACHAT_MAGASIN = "achatMagasin";
    public static final String TAG_BOUTEILLE_ACHAT_PRIX = "achatPrix";

    /** location bouteille mongo */
    public static final String TAG_BOUTEILLE_LOCATION = "location";
    public static final String TAG_BOUTEILLE_LOCATION_TYPE = "type";
    public static final String TAG_BOUTEILLE_LOCATION_TYPE_POINT = "Point";
    public static final String TAG_BOUTEILLE_LOCATION_COORDINATES = "coordinates";

    /** tags id */
    public static final String TAG_ID = "_id";

    /**
     * Const par défaut
     */
    private ConstantesAMBROSIA()
    {
        // RAF
    }
}
