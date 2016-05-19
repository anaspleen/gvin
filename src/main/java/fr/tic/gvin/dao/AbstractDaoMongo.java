//
// Fichier :
// AbstractDaoMongo.java, v 1.0 19 mai 2016 15:01:19
//
package fr.tic.gvin.dao;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;


/**
 * Gestion des conenxions et cie
 * 
 * @author Bull
 *         $Id$
 */
public abstract class AbstractDaoMongo
{

    /**
     * @return le client
     */
    protected MongoClient getClient()
    {
        //        MongoClientOptions optios = MongoClientOptions.builder().connectionsPerHost(500).build();
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        return client;
    }

    /**
     * @return la base
     */
    protected MongoDatabase getDatabase()
    {
        MongoDatabase database = getClient().getDatabase("ambrosia");
        return database;
    }
}
