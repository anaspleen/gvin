//
// Fichier :
// BouteilleDaoMongo.java, v 1.0 19 mai 2016 15:00:03
//
package fr.greeniot.ambrosia.dao;


import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;

import fr.greeniot.ambrosia.AbstractAmbrosiaTest;


/**
 * @author Bull
 *         $Id$
 */
public class TestParametreDao extends AbstractAmbrosiaTest
{

    @Test
    public void testPersist() throws Exception
    {
        // save
        Document bouteille = new Document();
        bouteille.put("test", "test");
        getParametreDao().save(bouteille, "bout");

        // recup
        Document res = getParametreDao().find("bouty");
        Assert.assertNull(res);

        res = getParametreDao().find("bout");
        Assert.assertNotNull(res);
        System.out.println(res);

        String fichier = "./src/test/resources/regle/regle-bouteille.json";

        Document regle = createDocumentFromFile(fichier);
        System.out.println(regle);
        getParametreDao().save(regle, "bouteille");
    }
}
