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

    /** l'URL database */
    private String m_DatabaseURL;

    /** le nom database */
    private String m_DatabaseNom;

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
    protected MongoDatabase getDatabase()
    {
        MongoDatabase database = getClient().getDatabase(getDatabaseNom());
        return database;
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
}
