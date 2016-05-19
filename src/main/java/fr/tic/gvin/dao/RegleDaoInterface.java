//
// Fichier :
// BouteilleDaoInterface.java, v 1.0 19 mai 2016 14:56:51
//
package fr.tic.gvin.dao;


import org.bson.Document;


/**
 * Dao de gestion des régles
 * 
 * @author Bull
 *         $Id$
 */
public interface RegleDaoInterface
{

    /**
     * Enregistre une régle
     * 
     * @param p_Document
     *            le document
     * @param p_NomRegle
     *            le nom de la régle (l'id)
     */
    void save(Document p_Document, String p_NomRegle);

    /**
     * Récupère une régle
     * 
     * @param p_NomRegle
     *            : le nom, exemple : bouteille
     * @return la règle si trouvée
     */
    Document find(String p_NomRegle);
}
