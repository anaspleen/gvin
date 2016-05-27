//
// Fichier :
// ParametrageServiceInterface.java, v 1.0 19 mai 2016 14:54:35
package fr.greeniot.ambrosia.service;


import fr.greeniot.commun.exception.BusinessException;
import fr.greeniot.commun.exception.TechnicalException;


/**
 * Service de paramétrage de l'application
 * 
 * @author Bull
 *         $Id$
 */
public interface ParametrageServiceInterface
{

    /**
     * Retourne le paramétre
     * 
     * @param p_Cle
     *            la clé paramétrage à chercher
     * @return le paramétre (null si pas trouvé)
     * @throws BusinessException
     *             soucis applicatif
     * @throws TechnicalException
     *             soucis technique
     */
    String obtenirString(String p_Cle) throws BusinessException, TechnicalException;

    /**
     * Initialise le paramétrage (vidage du cache map)
     * 
     * @throws BusinessException
     *             soucis applicatif
     * @throws TechnicalException
     *             soucis technique
     */
    void initialiserParametrage() throws BusinessException, TechnicalException;
}
