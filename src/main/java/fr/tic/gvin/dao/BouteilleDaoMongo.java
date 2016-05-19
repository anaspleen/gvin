//
// Fichier :
// BouteilleDaoMongo.java, v 1.0 19 mai 2016 15:00:03
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
// Créé le 19 mai 2016 à 15:00:03
//
// Historique :
//
// Révision 1.0 19 mai 2016 15:00:03 caiatit
// Création.
//
//
package fr.tic.gvin.dao;


import org.bson.Document;

import com.mongodb.client.MongoCollection;


/**
 * Impl persistance pour Mongo
 * 
 * @author Bull
 *         $Id$
 */
public class BouteilleDaoMongo extends AbstractDaoMongo implements BouteilleDaoInterface
{

    /* (non-Javadoc)
     * @see fr.tic.gvin.dao.BouteilleDaoInterface#persistBouteille(org.bson.Document)
     */
    public void persistBouteille(Document p_Document)
    {
        // TODO Auto-generated method stub

        MongoCollection<Document> bouteille = getDatabase().getCollection("bouteille");

        bouteille.insertOne(p_Document);

        System.out.println(p_Document.get("_id"));
    }
}
