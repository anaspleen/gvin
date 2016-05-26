//
// Fichier :
// BouteilleDaoMongo.java, v 1.0 19 mai 2016 15:00:03
//
package fr.greeniot.ambrosia.dao;


import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

import fr.greeniot.ambrosia.utils.ConstantesAMBROSIA;


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
     * @see fr.tic.gvin.dao.AbstractDaoMongo#getCollection(com.mongodb.MongoClient)
     */
    @Override
    protected MongoCollection<Document> getCollection(MongoClient p_Client)
    {
        return getDatabase(p_Client).getCollection("bouteille");
    }
}
