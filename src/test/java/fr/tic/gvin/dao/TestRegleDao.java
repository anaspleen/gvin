//
// Fichier :
// BouteilleDaoMongo.java, v 1.0 19 mai 2016 15:00:03
//
package fr.tic.gvin.dao;


import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;

import com.mongodb.DBObject;

import fr.tic.gvin.AbstractAmbrosiaTest;


/**
 * @author Bull
 *         $Id$
 */
public class TestRegleDao extends AbstractAmbrosiaTest
{

    @Test
    public void testPersist() throws Exception
    {
        // save
        Document bouteille = new Document();
        bouteille.put("test", "test");
        getRegleDao().save(bouteille, "bout");

        // recup
        Document res = getRegleDao().find("bouty");
        Assert.assertNull(res);

        res = getRegleDao().find("bout");
        Assert.assertNotNull(res);
        System.out.println(res);

        String fichier = "./src/test/resources/regle/regle-bouteille.json";

        Document regle = createDocumentFromFile(fichier);
        System.out.println(regle);
        getRegleDao().save(regle, "bouteille");
    }
}
