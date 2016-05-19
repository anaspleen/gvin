//
// Fichier :
// BusinessException.java , v 1.0 21 janv. 2015

package fr.tic.gvin.exception;


import org.slf4j.Logger;


/**
 * Exception levée lors d'erreur métier.
 * 
 * @author Bull
 */
public class BusinessException extends Exception
{

    /** Code erreur 1, statut Http 400, "Argument invalide". */
    public static final int CODE_1_ARGUMENT_INVALIDE = 1;
    /** Code erreur 2, statut Http 400, "Url invalide". */
    public static final int CODE_2_URL_INVALIDE = 2;
    /** Code erreur 3, statut Http 500, "Argument invalide". */
    public static final int CODE_3_PARAMETRAGE_INCORRECT = 3;
    /** Code erreur 4, statut Http 400, "Requête de recherche ROSA invalide". */
    public static final int CODE_4_REQUETE_RECHERCHE_ROSA_INVALIDE = 4;
    /** Code erreur 5, statut Http 500, "Code réponse HTTP inattendu". */
    public static final int CODE_5_REPONSE_CODE_HTTP_INATTENDU = 5;
    /** Code erreur 6, statut Http 500, "Donnée incohérente". */
    public static final int CODE_6_DONNEE_INCOHERENTE = 6;
    /** Code erreur 7, statut Http 404, "Ressource non trouvée". */
    public static final int CODE_7_RESSOURCE_NON_TROUVEE = 7;
    /** Code erreur 8, statut Http 404, "Ressource non trouvée pour la version donnée". */
    public static final int CODE_8_VERSION_RESSOURCE_NON_TROUVEE = 8;
    /** Code erreur 9, statut Http 500, "Paramétrage non trouvé". */
    public static final int CODE_9_PARAMETRAGE_NON_TROUVE = 9;
    /** Code erreur 10, statut Http 500, "Opération non réalisable". */
    public static final int CODE_10_OPERATION_NON_REALISABLE = 10;
    /** Code erreur 11, statut Http 500, "Erreur interne au serveur". */
    public static final int CODE_11_MESSAGE_ERREUR_RETOURNE_PAR_SERVEUR = 11;
    /** Code erreur 12, statut Http 400, "Autorité incorrecte (type d'identifiant d'opérateur)". */
    public static final int CODE_12_AUTORITE_OPERATEUR_INCORRECTE = 12;
    /** Code erreur 13, statut Http 404, "Template non trouvé". */
    public static final int CODE_13_TEMPLATE_RESSOURCE_NON_TROUVE = 13;
    /** Code erreur 14, statut Http 401, "Argument invalide". */
    public static final int CODE_14_NON_AUTHENTIFIE = 14;
    /** Code erreur 15, statut Http 403, "L'accès à la ressource est refusé". */
    public static final int CODE_15_NON_AUTORISE = 15;
    /** Code erreur 16, statut Http 400, "Les paramètres de la requête ne sont pas valides". */
    public static final int CODE_16_PARAMETRES_REQUETE_INVALIDES = 16;

    /**
     * Serial UID.
     */
    private static final long serialVersionUID = 6065200293755880133L;

    /**
     * @deprecated utiliser l'enum {@link #exceptionEnum} à la place.
     *             Code de l'erreur métier.
     */
    @Deprecated
    private final Integer codeErreur;

    /**
     * Enumération permettant de connaitre les détails de l'exception.
     */
    private final BusinessExceptionEnum exceptionEnum;

    /**
     * Constructeur privé, on veut forcer l'utilisation des constructeurs avec code d'erreur.
     * 
     * @param p_message
     *            message expliquant l'exception
     * @param p_cause
     *            de l'exception
     */
    private BusinessException(final String p_message, final Throwable p_cause)
    {
        super(p_message, p_cause);
        codeErreur = 0;
        exceptionEnum = null;
    }

    /**
     * Constructeur privé, on veut forcer l'utilisation des constructeurs avec code d'erreur.
     * 
     * @param p_message
     *            message expliquant l'exception
     */
    private BusinessException(final String p_message)
    {
        super(p_message);
        codeErreur = 0;
        exceptionEnum = null;
    }

    /**
     * Constructeur.
     * 
     * @deprecated utiliser {@link #BusinessException(BusinessExceptionEnum, String)}
     * @param p_messageTechnique
     *            message expliquant l'exception
     * @param p_code
     *            code de l'erreur métier
     */
    @Deprecated
    public BusinessException(final int p_code, final String p_messageTechnique)
    {
        super(p_messageTechnique);
        codeErreur = p_code;
        exceptionEnum = BusinessExceptionEnum.newFromCodeErreur(p_code);
    }

    /**
     * Constructeur.
     * 
     * @param p_messageTecnhique
     *            message expliquant l'exception
     * @param p_code
     *            code de l'erreur métier
     */
    public BusinessException(final BusinessExceptionEnum p_businessExceptionEnum, final String p_messageTecnhique)
    {
        super(p_messageTecnhique);
        //Pour rétrocompatibilité
        codeErreur = p_businessExceptionEnum.getCodeErreur();
        exceptionEnum = p_businessExceptionEnum;
    }

    /**
     * Log et lève une exception BusinessException avec un logger slf4j en entrée, un code d'erreur et un message.
     * 
     * @deprecated Utiliser {@link #logAndThrow(Logger, BusinessExceptionEnum, String)}
     * @param p_logger
     *            logger SLF4J
     * @param p_businessExceptionCode
     *            Code d'erreur (voir les constantes publiques dans {@link BusinessException})
     * @param p_message
     *            message
     * @throws BusinessException
     *             erreur métier
     */
    @Deprecated
    public static void logAndThrow(final Logger p_logger, final int p_businessExceptionCode, final String p_message)
            throws BusinessException
    {
        p_logger.error("Erreur métier " + p_businessExceptionCode + ". " + p_message);
        throw new BusinessException(p_businessExceptionCode, p_message);
    }

    /**
     * Log et lève une exception BusinessException avec un logger slf4j en entrée, un code d'erreur et un message.
     * 
     * @param p_logger
     *            logger SLF4J
     * @param p_businessExceptionCode
     *            Code d'erreur (voir les constantes publiques dans {@link BusinessException})
     * @param p_messageTechnique
     *            message
     * @throws BusinessException
     *             erreur métier
     */
    public static void logAndThrow(final Logger p_logger, final BusinessExceptionEnum p_businessExceptionEnum,
            final String p_messageTechnique) throws BusinessException
    {
        p_logger.error("Erreur métier " + p_businessExceptionEnum.getCodeErreur() + ". " + p_messageTechnique);
        throw new BusinessException(p_businessExceptionEnum, p_messageTechnique);
    }

    /**
     * Constructeur.
     * 
     * @deprecated Utiliser {@link #BusinessException(BusinessExceptionEnum, String, Throwable)}
     * @param p_code
     *            code de l'erreur métier
     * @param p_message
     *            message de l'erreur métier
     * @param p_cause
     *            exception causant l'erreur métier
     */
    @Deprecated
    public BusinessException(final int p_code, final String p_message, final Throwable p_cause)
    {
        super(p_message, p_cause);
        codeErreur = p_code;
        exceptionEnum = BusinessExceptionEnum.newFromCodeErreur(p_code);
    }

    /**
     * Constructeur.
     * 
     * @param p_businessExceptionEnum
     *            code de l'erreur métier
     * @param p_message
     *            message de l'erreur métier
     * @param p_cause
     *            exception causant l'erreur métier
     */
    public BusinessException(final BusinessExceptionEnum p_businessExceptionEnum, final String p_message,
            final Throwable p_cause)
    {
        super(p_message, p_cause);

        codeErreur = p_businessExceptionEnum.getCodeErreur();
        this.exceptionEnum = p_businessExceptionEnum;
    }

    /**
     * @return codeErreur
     */
    public Integer getCodeErreur()
    {
        return codeErreur;
    }

    public BusinessExceptionEnum getExceptionEnum()
    {
        return exceptionEnum;
    }

    /**
     * Enumération permettant de gérer les cas d'erreur métier.
     * <p>
     * Contient les informations suivantes
     * <li>Code d'erreur ROSA
     * <li>Statut HTTP
     * <li>Message d'erreur fonctionnel
     * <li>Cause (ou complément d'information). Donne des indices sur ce qui peut cause ce type d'erreur en général.
     * 
     * @author Bull $ Id: $
     */
    public enum BusinessExceptionEnum
    {
        /** Code erreur 1, statut Http 400, "Argument invalide". */
        CODE_1_ARGUMENT_INVALIDE(1, 400, "Argument invalide",
                "Un argument passé à une méthode n'est pas présent ou a une valeur non conforme."),
        /** Code erreur 2, statut Http 400, "Url invalide". */
        CODE_2_URL_INVALIDE(2, 400, "Url invalide", "L'url n'est pas exploitable."),
        /** Code erreur 3, statut Http 500, "Argument invalide". */
        CODE_3_PARAMETRAGE_INCORRECT(3, 500, "Argument invalide",
                "Une valeur est manquante ou incorrect dans un fichier de paramétrage."),
        /** Code erreur 4, statut Http 400, "Requête de recherche ROSA invalide". */
        CODE_4_REQUETE_RECHERCHE_ROSA_INVALIDE(4, 400, "Requête de recherche ROSA invalide",
                "La requête de recherche ROSA est malformée ou comporte des valeurs invalides."),
        /** Code erreur 5, statut Http 500, "Code réponse HTTP inattendu". */
        CODE_5_REPONSE_CODE_HTTP_INATTENDU(5, 500, "Code réponse HTTP inattendu",
                "Le code de la réponse HTTP reçue n'est pas conforme à celui attendu."),
        /** Code erreur 6, statut Http 500, "Donnée incohérente". */
        CODE_6_DONNEE_INCOHERENTE(6, 500, "Donnée incohérente",
                "Une valeur est manquante ou incorrecte dans un objet de donnée."),
        /** Code erreur 7, statut Http 404, "Ressource non trouvée". */
        CODE_7_RESSOURCE_NON_TROUVEE(7, 404, "Ressource non trouvée",
                "La ressource n'a pas été trouvée (l'identifiant est correct mais n'a fourni n'a retourné aucune valeur)."),
        /** Code erreur 8, statut Http 404, "Ressource non trouvée pour la version donnée". */
        CODE_8_VERSION_RESSOURCE_NON_TROUVEE(8, 404, "Ressource non trouvée pour la version donnée",
                "La représentation de la ressource n'existe pas à la version demandée."),
        /** Code erreur 9, statut Http 500, "Paramétrage non trouvé". */
        CODE_9_PARAMETRAGE_NON_TROUVE(9, 500, "Paramétrage non trouvé",
                "Un fichier de paramétrage requis n'a pas été trouvé."),
        /** Code erreur 10, statut Http 500, "Opération non réalisable". */
        CODE_10_OPERATION_NON_REALISABLE(10, 500, "Opération non réalisable", "Opération non réalisable"),
        /** Code erreur 11, statut Http 500, "Erreur interne au serveur". */
        CODE_11_MESSAGE_ERREUR_RETOURNE_PAR_SERVEUR(11, 500, "Erreur interne au serveur",
                "Une erreur inattendue est retournée par le serveur."),
        /** Code erreur 12, statut Http 400, "Autorité incorrecte (type d'identifiant d'opérateur)". */
        CODE_12_AUTORITE_OPERATEUR_INCORRECTE(12, 400, "Autorité incorrecte (type d'identifiant d'opérateur)",
                "Autorité (type d'identifiant opérateur) incorrecte"),
        /** Code erreur 13, statut Http 404, "Template non trouvé". */
        CODE_13_TEMPLATE_RESSOURCE_NON_TROUVE(13, 404, "Template non trouvé",
                "Le template n'a pas été trouvé pour la ressource"),
        /** Code erreur 14, statut Http 401, "Argument invalide". */
        CODE_14_NON_AUTHENTIFIE(14, 401, "Echec lors de l'authentification",
                "Les informations fournies par le téléservice utilisateur de l'api n'ont pas permis de l'authentifié"),
        /** Code erreur 15, statut Http 403, "L'accès à la ressource est refusé". */
        CODE_15_NON_AUTORISE(15, 403, "L'accès à la ressource est refusé",
                "Le téléservice utilisateur n'a pas le droit d'accéder à la ressource"),
        /** Code erreur 16, statut Http 400, "Les paramètres de la requête ne sont pas valides". */
        CODE_16_PARAMETRES_REQUETE_INVALIDES(16, 400, "Les paramètres de la requête ne sont pas valides",
                "Le(s) paramètre(s) de requête est/sont invalide(s) ou incohérent(s)");

        /** Code d'erreur ROSA. */
        private final int codeErreur;
        /** Statut HTTP. */
        private final int statutHttp;
        /** Message métier. */
        private final String message;
        /** Complément d'explication. */
        private final String cause;

        /**
         * @param codeErreur
         * @param statutHttp
         * @param message
         */
        private BusinessExceptionEnum(final int codeErreur, final int statutHttp, final String message,
                final String p_cause)
        {
            this.codeErreur = codeErreur;
            this.statutHttp = statutHttp;
            this.message = message;
            this.cause = p_cause;
        }

        /**
         * Permet de retrouver une enum à partir du code d'erreur ROSA.
         * 
         * @param p_code
         *            code d'erreur ROSA
         * @return BusinessExceptionEnum ; null si non trouvé.
         */
        public static BusinessExceptionEnum newFromCodeErreur(int p_code)
        {

            BusinessExceptionEnum l_resultat = null;
            boolean l_trouve = false;
            int i = 0;
            while (!l_trouve && i < values().length)
            {
                BusinessExceptionEnum l_enum = values()[i];
                if (l_enum.getCodeErreur() == p_code)
                {
                    l_resultat = l_enum;
                    l_trouve = true;
                }
                i++;
            }

            return l_resultat;
        }

        /**
         * @return {@link #codeErreur}
         */
        public int getCodeErreur()
        {
            return codeErreur;
        }

        /**
         * @return {@link #statutHttp}
         */
        public int getStatutHttp()
        {
            return statutHttp;
        }

        /**
         * @return {@link #message}
         */
        public String getMessage()
        {
            return message;
        }

        /**
         * @return {@link #cause}
         */
        public String getCause()
        {
            return cause;
        }

    }
}
