//
// Fichier :
// AbstractDaoMongo.java, v 1.0 19 mai 2016 15:01:19
//
package fr.greeniot.ambrosia.dao;


import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import fr.greeniot.ambrosia.utils.ConstantesAMBROSIA;


/**
 * Gestion des conenxions et cie
 * 
 * @author Bull
 *         $Id$
 */
public abstract class AbstractDaoMongo
{

    /** le logger... */
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDaoMongo.class);

    /** l'URL database */
    private String m_DatabaseURL;

    /** le nom database */
    private String m_DatabaseNom;

    /**
     * @return
     */
    protected abstract MongoCollection<Document> getCollection(MongoClient p_Client);

    /**
     * @return le client
     */
    protected MongoClient getClient()
    {
        MongoClient client = new MongoClient(new MongoClientURI(getDatabaseURL()));
        return client;
    }

    /**
     * @return la base
     */
    protected MongoDatabase getDatabase(MongoClient p_Client)
    {
        MongoDatabase database = p_Client.getDatabase(getDatabaseNom());
        return database;
    }

    /**
     * @return la base
     */
    protected void releaseClient(MongoClient p_Client)
    {
        try
        {
            p_Client.close();
        }
        catch (Exception e)
        {
            LOG.error("Impossible de fermer la conenxion", e);
        }
    }

    /**
     * @return databaseURL
     */
    public String getDatabaseURL()
    {
        return m_DatabaseURL;
    }

    /**
     * Méthode permettant d'initialiser la valeur de databaseURL.
     * 
     * @param p_DatabaseURL
     *            le/la databaseURL à initialiser
     */
    public void setDatabaseURL(String p_DatabaseURL)
    {
        m_DatabaseURL = p_DatabaseURL;
    }

    /**
     * @return databaseNom
     */
    public String getDatabaseNom()
    {
        return m_DatabaseNom;
    }

    /**
     * Méthode permettant d'initialiser la valeur de databaseNom.
     * 
     * @param p_DatabaseNom
     *            le/la databaseNom à initialiser
     */
    public void setDatabaseNom(String p_DatabaseNom)
    {
        m_DatabaseNom = p_DatabaseNom;
    }
    
    /*
     * (non-Javadoc)
     * @see fr.greeniot.ambrosia.dao.BouteilleDaoInterface#save(org.bson.Document)
     */
    public String save(Document p_Document)
    {
        MongoClient client = getClient();
        MongoCollection<Document> bouteille = getCollection(client);
        bouteille.insertOne(p_Document);

        releaseClient(client);
        return p_Document.get(ConstantesAMBROSIA.TAG_ID).toString();
    }

    /*
     * (non-Javadoc)
     * @see fr.greeniot.ambrosia.dao.BouteilleDaoInterface#find(java.lang.String)
     */
    public Document findById(String p_Id)
    {
        Document res = null;
        MongoClient client = getClient();
        Document filter = new Document(ConstantesAMBROSIA.TAG_ID, new ObjectId(p_Id));

        MongoCollection<Document> bouteille = getCollection(client);
        List<Document> docs = bouteille.find(filter).into(new ArrayList<Document>());

        if (docs != null && docs.size() > 0)
        {
            res = docs.get(0);
        }

        releaseClient(client);

        return res;
    }

}
