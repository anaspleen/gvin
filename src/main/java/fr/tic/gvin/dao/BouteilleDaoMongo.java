//
// Fichier :
// BouteilleDaoMongo.java, v 1.0 19 mai 2016 15:00:03
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

    /*
     * (non-Javadoc)
     * @see fr.tic.gvin.dao.BouteilleDaoInterface#save(org.bson.Document)
     */
    public void save(Document p_Document)
    {
        // TODO Auto-generated method stub

        MongoCollection<Document> bouteille = getDatabase().getCollection("bouteille");

        bouteille.insertOne(p_Document);

        System.out.println(p_Document.get("_id"));
    }
}
