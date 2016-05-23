//
// Fichier :
// ValidationService.java, v 1.0 20 mai 2016 11:35:18
//
package fr.greeniot.ambrosia.service;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.greeniot.ambrosia.dao.RegleDaoInterface;
import fr.greeniot.ambrosia.service.bean.ChampBean;
import fr.greeniot.ambrosia.service.bean.ChampInterface;
import fr.greeniot.commun.exception.BusinessException;
import fr.greeniot.commun.exception.TechnicalException;
import fr.greeniot.utils.ConstantesAMBROSIA;


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

        if (regles == null)
        {
            throw new TechnicalException("Aucune régle trouvée pour l'objet : " + p_TypeObjet);
        }

        // dans l'autre sens, on vérifie que les champs sont bien là
        for (String tagRegle : regles.keySet())
        {
            // prise de la régle
            regle = regles.get(tagRegle);

            if (p_DocumentAValider.containsKey(tagRegle))
            {
                // ici on vérife
                res.put(tagRegle, regle.valideDonnee(p_DocumentAValider.get(tagRegle)));
            }
            else
            {
                res.put(tagRegle, Arrays.asList("tag.inexistant"));
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
