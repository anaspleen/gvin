//
// Fichier :
// ValidationService.java, v 1.0 20 mai 2016 11:35:18
//
package fr.tic.gvin.service;


import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.tic.gvin.dao.RegleDaoInterface;
import fr.tic.gvin.exception.BusinessException;
import fr.tic.gvin.exception.TechnicalException;


/**
 * @author Bull
 *         $Id$
 */
public class ValidationService implements ValidationServiceInterface
{

    /** le logger... */
    private static final Logger LOG = LoggerFactory.getLogger(ValidationService.class);

    /** dao */
    private RegleDaoInterface m_RegleDao;

    /*
     * (non-Javadoc)
     * @see fr.tic.gvin.service.ValidationServiceInterface#validerObjet(org.bson.Document, java.lang.String)
     */
    public List<String> validerObjet(Document p_Document, String p_TypeObjet) throws BusinessException,
            TechnicalException
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return regleDao
     */
    public RegleDaoInterface getRegleDao()
    {
        return m_RegleDao;
    }

    /**
     * Méthode permettant d'initialiser la valeur de regleDao.
     * 
     * @param p_RegleDao
     *            le/la regleDao à initialiser
     */
    public void setRegleDao(RegleDaoInterface p_RegleDao)
    {
        m_RegleDao = p_RegleDao;
    }
}
