//
// Fichier :
// BouteilleDaoMongo.java, v 1.0 19 mai 2016 15:00:03
//
package fr.greeniot.ambrosia.dao;


import org.bson.Document;

import com.mongodb.MongoClient;
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
        MongoClient client = getClient();
        MongoCollection<Document> bouteille = getCollection(client);
        bouteille.insertOne(p_Document);
        System.out.println(p_Document.get("_id"));
        releaseClient(client);
    }

    /*
     * (non-Javadoc)
     * @see fr.tic.gvin.dao.AbstractDaoMongo#getCollection(com.mongodb.MongoClient)
     */
    @Override
    protected MongoCollection<Document> getCollection(MongoClient p_Client)
    {
        return getDatabase(p_Client).getCollection("bouteille");
    }
}
