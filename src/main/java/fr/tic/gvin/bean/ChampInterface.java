//
// Fichier :
// RegleInterface.java, v 1.0 20 mai 2016 12:50:50
//
package fr.tic.gvin.bean;


/**
 * Définition d'une régle
 * 
 * @author Bull
 *         $Id$
 */
public interface ChampInterface
{

    /**
     * @return la regex
     */
    String getRegexp();

    /**
     * @return le type
     */
    TypeChamp getType();

    /**
     * @return true si obligatoire
     */
    boolean isObligatoire();

    /**
     * @return le nom du tag
     */
    String getNom();
}
