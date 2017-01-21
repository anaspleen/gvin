//
// Fichier :
// BouteilleDaoMongo.java, v 1.0 19 mai 2016 15:00:03
//
package fr.greeniot.ambrosia.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * Impl persistance pour Mongo
 */
public class BouteilleDaoMongo extends AbstractDaoMongo implements BouteilleDaoInterface
{
  @Override
  protected MongoCollection<Document> getCollection(MongoClient p_Client)
  {
    return getDatabase(p_Client).getCollection("bouteille");
  }
}
