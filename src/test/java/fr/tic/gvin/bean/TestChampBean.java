//
// Fichier :
// BouteilleDaoMongo.java, v 1.0 19 mai 2016 15:00:03
//
package fr.tic.gvin.bean;


import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;

import fr.tic.gvin.AbstractAmbrosiaTest;


/**
 * @author Bull
 *         $Id$
 */
public class TestChampBean extends AbstractAmbrosiaTest
{

    private static final String TAG_VIGNOBLE = "vignoble";

    @Test
    public void testEnregistrer() throws Exception
    {
        String fichier = "./src/test/resources/regle/regle-simple-pour-test-vignoble.json";
        Document regleVignoble = createDocumentFromFile(fichier);

        ChampInterface regle = new ChampBean(regleVignoble, TAG_VIGNOBLE);
        Assert.assertTrue(regle.isObligatoire());
        Assert.assertEquals(TypeChamp.string, regle.getType());
        Assert.assertEquals("123456", regle.getRegexp());
        Assert.assertEquals(TAG_VIGNOBLE, regle.getNom());
    }
}
