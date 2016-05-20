//
// Fichier :
// ValidationService.java, v 1.0 20 mai 2016 11:35:18
//
package fr.tic.gvin.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.tic.gvin.bean.ChampBean;
import fr.tic.gvin.bean.ChampInterface;
import fr.tic.gvin.dao.RegleDaoInterface;
import fr.tic.gvin.exception.BusinessException;
import fr.tic.gvin.exception.TechnicalException;
import fr.tic.gvin.utils.ConstantesAMBROSIA;


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

    /** le cache */
    private static Map<String, Map<String, ChampInterface>> s_Map = new HashMap<String, Map<String, ChampInterface>>();

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

    /*
     * (non-Javadoc)
     * @see fr.tic.gvin.service.ValidationServiceInterface#obtenirRegles(java.lang.String)
     */
    public Map<String, ChampInterface> obtenirRegles(String p_TypeObjet) throws BusinessException, TechnicalException
    {
        Map<String, ChampInterface> res = s_Map.get(p_TypeObjet);

        if (res == null)
        {
            res = new HashMap<String, ChampInterface>();

            // obtention du document
            Document regles = getRegleDao().find(p_TypeObjet);

            if (regles != null)
            {
                // itération sur les clé
                for (String tag : regles.keySet())
                {
                    if (!tag.equals(ConstantesAMBROSIA.TAG_ID))
                    {
                        res.put(tag, new ChampBean((Document) regles.get(tag), tag));
                    }
                }
            }

            // ajout dans la map
            s_Map.put(p_TypeObjet, res);
        }

        return res;
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
