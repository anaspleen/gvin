//
// Fichier :
// ValidationService.java, v 1.0 20 mai 2016 11:35:18
//
package fr.tic.gvin.service;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

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

    /** les tags pas à vérifier */
    private static final List<String> TAGS_PAS_A_VERIFIER = Arrays.asList(ConstantesAMBROSIA.TAG_ID,
            ConstantesAMBROSIA.TAG_BOUTEILLE_LOCATION);

    /*
     * (non-Javadoc)
     * @see fr.tic.gvin.service.ValidationServiceInterface#validerObjet(org.bson.Document, java.lang.String)
     */
    public Map<String, List<String>> validerObjet(Document p_DocumentAValider, String p_TypeObjet)
            throws BusinessException, TechnicalException
    {
        Map<String, List<String>> res = new HashMap<String, List<String>>();

        Map<String, ChampInterface> regles = obtenirRegles(p_TypeObjet);
        ChampInterface regle = null;

        List<String> erreurDuTag = null;

        if (regles == null)
        {
            throw new TechnicalException("Aucune régle trouvée pour l'objet : " + p_TypeObjet);
        }

        for (String tagObjet : p_DocumentAValider.keySet())
        {
            if (!TAGS_PAS_A_VERIFIER.contains(tagObjet))
            {
                // prise de la régle
                regle = regles.get(tagObjet);

                if (regle == null)
                {
                    // si pas de régle, on ne fait rien
                }
                else
                {
                    res.put(tagObjet, regle.valideDonnee(p_DocumentAValider.get(tagObjet)));
                }
            }
        }

        return res;
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

                // ajout dans la map si pas null
                s_Map.put(p_TypeObjet, res);
            }
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
