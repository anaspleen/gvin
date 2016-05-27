//
// Fichier :
// ParametrageService.java, v 1.0 27 mai 2016 15:24:49
//
package fr.greeniot.ambrosia.service;


import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.greeniot.ambrosia.dao.ParametreDaoInterface;
import fr.greeniot.ambrosia.utils.ConstantesAMBROSIA;
import fr.greeniot.commun.exception.BusinessException;
import fr.greeniot.commun.exception.TechnicalException;


/**
 * @author Bull
 *         $Id$
 */
public class ParametreService implements ParametreServiceInterface
{

    /** le logger... */
    private static final Logger LOG = LoggerFactory.getLogger(ValidationService.class);

    /** le document représentant le paramétre */
    private static Document s_DocumentParametre;

    /** dao */
    private ParametreDaoInterface m_ParametreDao;

    /* (non-Javadoc)
     * @see fr.greeniot.commun.service.ParametrageServiceInterface#obtenirString(java.lang.String)
     */
    public String obtenirString(String p_Cle) throws BusinessException, TechnicalException
    {
        return obtenirStringImpl(p_Cle);
    }

    private String obtenirStringImpl(String p_Cle) throws BusinessException, TechnicalException
    {
        if (s_DocumentParametre == null)
        {
            initialiserParametrage();
        }
        return s_DocumentParametre.getString(p_Cle);
    }

    /* (non-Javadoc)
     * @see fr.greeniot.commun.service.ParametrageServiceInterface#initialiserParametrage()
     */
    public synchronized void initialiserParametrage() throws BusinessException, TechnicalException
    {
        LOG.info("Intialisation du parametrage");
        s_DocumentParametre = getParametreDao().find(ConstantesAMBROSIA.CLE_PARAMETRE_PARAMETRAGE);
        if (s_DocumentParametre == null)
        {
            throw new TechnicalException("Aucun parametre : " + ConstantesAMBROSIA.CLE_PARAMETRE_PARAMETRAGE + " trouvé");
        }
        LOG.info("Intialisation du parametrage : fait");
    }

    /**
     * @return parametreDao
     */
    public ParametreDaoInterface getParametreDao()
    {
        return m_ParametreDao;
    }

    /**
     * Méthode permettant d'initialiser la valeur de parametreDao.
     * 
     * @param p_ParametreDao
     *            le/la parametreDao à initialiser
     */
    public void setParametreDao(ParametreDaoInterface p_ParametreDao)
    {
        m_ParametreDao = p_ParametreDao;
    }
}
