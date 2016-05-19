//
// Fichier :
// BouteilleDaoMongo.java, v 1.0 19 mai 2016 15:00:03
//
package fr.tic.gvin.dao;


import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import fr.tic.gvin.utils.ConstantesAMBROSIA;


/**
 * Impl persistance pour Mongo
 * 
 * @author Bull
 *         $Id$
 */
public class RegleDaoMongo extends AbstractDaoMongo implements RegleDaoInterface
{

    /*
     * (non-Javadoc)
     * @see fr.tic.gvin.dao.RegleDaoInterface#save(org.bson.Document, java.lang.String)
     */
    public void save(Document p_Document, String p_NomRegle)
    {
        MongoCollection<Document> bouteille = getCollection();
        p_Document.put(ConstantesAMBROSIA.TAG_ID, p_NomRegle);
        bouteille.insertOne(p_Document);
        System.out.println(p_Document.get("_id"));
    }

    /*
     * (non-Javadoc)
     * @see fr.tic.gvin.dao.AbstractDaoMongo#getCollection()
     */
    @Override
    protected MongoCollection<Document> getCollection()
    {
        return getDatabase().getCollection("regle");
    }

    /*
     * (non-Javadoc)
     * @see fr.tic.gvin.dao.RegleDaoInterface#find(java.lang.String)
     */
    public Document find(String p_NomRegle)
    {
        Document res = null;

        Document filter = new Document(ConstantesAMBROSIA.TAG_ID, p_NomRegle);

        List<Document> all = getCollection().find(filter).into(new ArrayList<Document>());

        if (all != null && all.size() > 0)
        {
            res = all.get(0);
        }

        return res;
    }
}
