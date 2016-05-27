//
// Fichier :
// ValidationServiceInterface.java, v 1.0 19 mai 2016 14:54:35
package fr.greeniot.ambrosia.service;


import java.util.List;
import java.util.Map;

import org.bson.Document;

import fr.greeniot.ambrosia.bean.ChampInterface;
import fr.greeniot.commun.exception.BusinessException;
import fr.greeniot.commun.exception.TechnicalException;


/**
 * Service de validation des objets
 * 
 * @author Bull
 *         $Id$
 */
public interface ValidationServiceInterface
{

    /**
     * Valide un objet et retourne les erreurs
     * 
     * @param p_DocumentAValider
     *            le document à valider
     * @param p_TypeObjet
     *            le type, exemple : "bouteille"
     * @return les erreurs : nomTag / liste erreurs
     * @throws BusinessException
     *             soucis applicatif
     * @throws TechnicalException
     *             soucis technique
     */
    Map<String, List<String>> validerObjet(Document p_DocumentAValider, String p_TypeObjet) throws BusinessException,
            TechnicalException;

    /**
     * Obtention de la régle. Méthode utile pour testU et appelée par validerObjet
     * 
     * @param p_TypeObjet
     *            le type : bouteille
     * @return les tags/champs régles
     * @throws BusinessException
     *             soucis applicatif
     * @throws TechnicalException
     *             soucis technique
     */
    Map<String, ChampInterface> obtenirRegles(String p_TypeObjet) throws BusinessException, TechnicalException;

    /**
     * Initialise les régles (vidage du cache map)
     * 
     * @throws BusinessException
     *             soucis applicatif
     * @throws TechnicalException
     *             soucis technique
     */
    void initialiserRegles() throws BusinessException, TechnicalException;
}
