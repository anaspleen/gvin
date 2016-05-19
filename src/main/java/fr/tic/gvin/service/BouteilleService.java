//
// Fichier :
// BouteilleService.java, v 1.0 19 mai 2016 15:54:04
//
// Projet :
// LAQME
//
// Ministère :
// Ministère de l'économie et des finances.
//
// Direction :
// DGDDI
//
// Prestation :
// Bull
//
// Copyright :
// DGDDI 2004-2016
//
// Date :
// Créé le 19 mai 2016 à 15:54:04
//
// Historique :
//
// Révision 1.0 19 mai 2016 15:54:04 caiatit
// Création.
//
//
package fr.tic.gvin.service;


import java.util.Arrays;
import java.util.Map;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.tic.gvin.dao.BouteilleDaoInterface;
import fr.tic.gvin.utils.ConstantesAMBROSIA;


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

    /*
     * (non-Javadoc)
     * @see fr.tic.gvin.service.BouteilleServiceInterface#enregistrerBouteille(java.util.Map, double, double, java.util.Map)
     */
    public void enregistrerBouteille(Map<String, Object> p_Valeurs, double p_Longitude, double p_Latitude,
            Map<String, Object> p_ValeursAchat)
    {
        // TODO Auto-generated method stub

        //        {
        //            "nom":"dgfgdqsdq",
        //            "vignoble":"bordeaux",
        //            "aoc":"saint-émilion",
        //            "appellation":"saint-émilion grand cru",
        //            "chateau":"Galhaud",
        //            "anneeMiseEnBouteille":2012,
        //            "anneeConsommationOptimale":2020,
        //            "achat":{
        //               "date":"15/08/2015",
        //               "magasin":"Chateau Galhaud",
        //               "prix":20
        //            },
        //            "location":{
        //               "type":"Point",
        //               "coordinates":[
        //                  -0.1571643,17,
        //                  44.8949179
        //               ]
        //            }
        //         }

        // TODO les vérifications d'usage

        // init avec les valeurs de base
        Document doc = new Document(p_Valeurs);

        // ajout de l'achat
        doc.put(ConstantesAMBROSIA.TAG_BOUTEILLE_ACHAT, p_ValeursAchat);

        // ajout de la location
        Document loc = new Document();
        loc.put(ConstantesAMBROSIA.TAG_BOUTEILLE_LOCATION_TYPE, ConstantesAMBROSIA.TAG_BOUTEILLE_LOCATION_TYPE_POINT);
        // longitude en premier (Mongo)
        loc.put(ConstantesAMBROSIA.TAG_BOUTEILLE_LOCATION_COORDINATES, Arrays.asList(p_Longitude, p_Latitude));
        doc.put(ConstantesAMBROSIA.TAG_BOUTEILLE_LOCATION, loc);

        getBouteilleDao().save(doc);

        LOG.info("Enregistrement de la bouteille");
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
}
