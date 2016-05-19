//
// Fichier :
// enregistrerBouteille.java, v 1.0 19 mai 2016 14:54:35
package fr.tic.gvin.service;


import java.util.List;

import org.bson.Document;

import fr.tic.gvin.exception.BusinessException;
import fr.tic.gvin.exception.TechnicalException;


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
     * @param p_Document
     *            le document à valider
     * @param p_TypeObjet
     *            le type, exemple : "bouteille"
     * @return les erreurs
     * @throws BusinessException
     *             soucis applicatif
     * @throws TechnicalException
     *             soucis technique
     */
    List<String> validerObjet(Document p_Document, String p_TypeObjet) throws BusinessException, TechnicalException;
}