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


import java.util.Map;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.tic.gvin.dao.BouteilleDaoInterface;


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
     * @see fr.tic.gvin.service.BouteilleServiceInterface#enregistrerBouteille(java.util.Map, int, int, java.util.Map)
     */
    public void enregistrerBouteille(Map<String, Object> p_Valeurs, int p_Longitude, int p_Latitude,
            Map<String, Object> p_ValeursAchat)
    {
        // TODO Auto-generated method stub

        //        {
        //            "nom":"TODO",
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

        Document doc = new Document(p_Valeurs);

        
        
        
        getBouteilleDao().save(doc);
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
