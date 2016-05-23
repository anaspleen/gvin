//
// Fichier :
// BouteilleService.java, v 1.0 19 mai 2016 15:54:04
//
package fr.greeniot.ambrosia.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.greeniot.ambrosia.dao.BouteilleDaoInterface;
import fr.greeniot.commun.exception.BusinessException;
import fr.greeniot.commun.exception.BusinessException.BusinessExceptionEnum;
import fr.greeniot.commun.exception.TechnicalException;
import fr.greeniot.utils.ConstantesAMBROSIA;


/**
 * @author Bull
 *         $Id$
 */
public class BouteilleService implements BouteilleServiceInterface
{

    /** le logger... */
    private static final Logger LOG = LoggerFactory.getLogger(BouteilleService.class);

    /** le dao */
    private BouteilleDaoInterface m_BouteilleDao;

    /** le service de validation */
    private ValidationServiceInterface m_ValidationService;

    /*
     * (non-Javadoc)
     * @see fr.tic.gvin.service.BouteilleServiceInterface#enregistrerBouteille(java.util.Map, double, double)
     */
    public void enregistrerBouteille(Map<String, Object> p_Valeurs, double p_Longitude, double p_Latitude)
            throws BusinessException, TechnicalException
    {
        //        {
        //            "nom":"TODO",
        //            "vignoble":"bordeaux",
        //            "aoc":"saint-émilion",
        //            "appellation":"saint-émilion grand cru",
        //            "chateau":"Galhaud",
        //            "anneeMiseEnBouteille":2012,
        //            "anneeConsommationOptimale":2020,
        //            "achat.date":"15/08/2015",
        //            "achat.magasin":"Chateau Galhaud",
        //            "achat.prix":20,
        //            "location":{
        //               "type":"Point",
        //               "coordinates":[
        //                  -0.1571643,
        //                  44.8949179
        //               ]
        //            }
        //         }

        // TODO les vérifications d'usage
        // utiliser les règles de regle-bouteille.json pour vérifier cela
        // TODO donc : un service de vérification qui renvoit une liste d'erreur String
        // prendre uniquement aussi les tags de regle-bouteille.json

        // TODO walider le document avant commit

        // init avec les valeurs de base
        Document doc = new Document();

        List<String> erreurs = new ArrayList<String>();
        List<String> erreursDuTag = null;

        // vérif
        Map<String, List<String>> analyse = getValidationService().validerObjet(new Document(p_Valeurs), "bouteille");
        // on ne met que les tags présents dans keySet et sans erreurs
        for (String tagAMettre : analyse.keySet())
        {
            erreursDuTag = analyse.get(tagAMettre);

            if (erreursDuTag != null && erreursDuTag.size() > 0)
            {
                for (String erreurDuTag : erreursDuTag)
                {
                    erreurs.add(tagAMettre + " : " + erreurDuTag);
                }
            }
            // on l'ajoute
            else
            {
                doc.put(tagAMettre, p_Valeurs.get(tagAMettre));
            }
        }

        if (erreurs.size() > 0)
        {
            throw new BusinessException(BusinessExceptionEnum.CODE_1_ARGUMENT_INVALIDE, StringUtils.join(erreurs, ", "));
        }
        else
        {
            // ajout de la location
            Document loc = new Document();
            loc.put(ConstantesAMBROSIA.TAG_BOUTEILLE_LOCATION_TYPE,
                    ConstantesAMBROSIA.TAG_BOUTEILLE_LOCATION_TYPE_POINT);
            // longitude en premier (Mongo)
            loc.put(ConstantesAMBROSIA.TAG_BOUTEILLE_LOCATION_COORDINATES, Arrays.asList(p_Longitude, p_Latitude));
            doc.put(ConstantesAMBROSIA.TAG_BOUTEILLE_LOCATION, loc);

            getBouteilleDao().save(doc);

            if (LOG.isInfoEnabled())
            {
                LOG.info("Enregistrement de la bouteille");
            }
        }
    }

    /**
     * @return bouteilleDao
     */
    public BouteilleDaoInterface getBouteilleDao()
    {
        return m_BouteilleDao;
    }

    /**
     * Méthode permettant d'initialiser la valeur de bouteilleDao.
     * 
     * @param p_BouteilleDao
     *            le/la bouteilleDao à initialiser
     */
    public void setBouteilleDao(BouteilleDaoInterface p_BouteilleDao)
    {
        m_BouteilleDao = p_BouteilleDao;
    }

    /**
     * @return validationService
     */
    public ValidationServiceInterface getValidationService()
    {
        return m_ValidationService;
    }

    /**
     * Méthode permettant d'initialiser la valeur de validationService.
     * 
     * @param p_ValidationService
     *            le/la validationService à initialiser
     */
    public void setValidationService(ValidationServiceInterface p_ValidationService)
    {
        m_ValidationService = p_ValidationService;
    }
}
