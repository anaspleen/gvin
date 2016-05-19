//
// Fichier :
// BouteilleDaoMongo.java, v 1.0 19 mai 2016 15:00:03
//
package fr.tic.gvin.dao;


import org.bson.Document;
import org.junit.Test;

import fr.tic.gvin.AbstractAmbrosiaTest;


/**
 * @author Bull
 *         $Id$
 */
public class TestBouteilleDao extends AbstractAmbrosiaTest
{

    @Test
    public void testPersist() throws Exception
    {
        // TODO

        Document bouteille = new Document();
        bouteille.put("test", "test");
        getBouteilleDao().save(bouteille);
    }
}
