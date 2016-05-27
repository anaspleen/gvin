//
// Fichier :
// ParametrageService.java, v 1.0 27 mai 2016 15:24:49
//
package fr.greeniot.ambrosia.service;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.greeniot.ambrosia.service.ValidationService;
import fr.greeniot.commun.exception.BusinessException;
import fr.greeniot.commun.exception.TechnicalException;


/**
 * @author Bull
 *         $Id$
 */
public class ParametrageService implements ParametrageServiceInterface
{

    /** le logger... */
    private static final Logger LOG = LoggerFactory.getLogger(ValidationService.class);

    /** le cache */
    private static Map<String, Object> s_Map = new HashMap<String, Object>();

    /* (non-Javadoc)
     * @see fr.greeniot.commun.service.ParametrageServiceInterface#obtenirString(java.lang.String)
     */
    public String obtenirString(String p_Cle) throws BusinessException, TechnicalException
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see fr.greeniot.commun.service.ParametrageServiceInterface#initialiserParametrage()
     */
    public synchronized void initialiserParametrage() throws BusinessException, TechnicalException
    {
        LOG.info("Intialisation du parametrage");
        s_Map = new HashMap<String, Object>();
        LOG.info("Intialisation du parametrage : fait");
    }
}
