//
// Fichier :
// ConstantesAPIREST.java, v 1.0 18 nov. 2015 15:31:18
//
package fr.greeniot.ambrosia.utils;


/**
 * Les constantes de l'API REST
 * 
 * @author thomas
 *         $Id$
 */
public final class ConstantesAPIREST
{

    /** tag entree poser par l'API de LILA (classe dans lila-war : ObtenirLila) */
    public static final String TAG_REQUETE_ENVOYEE = "requeteEnvoyee";

    /** tag "action" dans la requête POST */
    public static final String ACTION = "action";

    /** tag requete - tjs mis dans le json reponse */
    public static final String TAG_REQUETE = "requete";

    /** tag reponse - tjs mis dans le json reponse */
    public static final String TAG_REPONSE = "reponse";

    /** tag reponse - tjs mis dans le json reponse serveur */
    public static final String TAG_REPONSE_SERVEUR = "reponseServeur";

    /** les actions */
    public static final String ACTION_CREER = "creer";
    public static final String ACTION_MODIFIER = "modifier";
    public static final String ACTION_CONSULTER = "consulter";
    public static final String ACTION_ADMIN_LISTER = "lister";
    public static final String ACTION_SUPPRIMER = "supprimer";

    /**
     * Const par défaut
     */
    private ConstantesAPIREST()
    {
        // RAF
    }
}
