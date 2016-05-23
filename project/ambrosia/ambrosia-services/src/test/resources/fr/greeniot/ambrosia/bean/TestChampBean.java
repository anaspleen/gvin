//
// Fichier :
// BouteilleDaoMongo.java, v 1.0 19 mai 2016 15:00:03
//
package fr.greeniot.ambrosia.bean;


import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;

import fr.greeniot.ambrosia.AbstractAmbrosiaTest;
import fr.greeniot.ambrosia.service.bean.ChampBean;
import fr.greeniot.ambrosia.service.bean.ChampInterface;
import fr.greeniot.ambrosia.service.bean.TypeChamp;


/**
 * @author Bull
 *         $Id$
 */
public class TestChampBean extends AbstractAmbrosiaTest
{

    private static final String TAG_VIGNOBLE = "vignoble";

    @Test
    public void testChampString() throws Exception
    {
        String fichier = "./src/test/resources/regle/regle-simple-pour-test-vignoble.json";
        Document regleVignoble = createDocumentFromFile(fichier);

        ChampInterface regle = new ChampBean(regleVignoble, TAG_VIGNOBLE);
        Assert.assertTrue(regle.isObligatoire());
        Assert.assertEquals(TypeChamp.string, regle.getType());
        Assert.assertEquals("123456", regle.getRegexp());
        Assert.assertEquals(TAG_VIGNOBLE, regle.getNom());
        Assert.assertEquals(0, regle.valideDonnee("valeur").size());
        Assert.assertEquals(1, regle.valideDonnee(null).size());
        Assert.assertEquals("champ.obligatoire", regle.valideDonnee(null).get(0));
    }

    @Test
    public void testChampInteger() throws Exception
    {
        String fichier = "./src/test/resources/regle/regle-simple-pour-test-integer.json";
        Document regleVignoble = createDocumentFromFile(fichier);

        ChampInterface regle = new ChampBean(regleVignoble, TAG_VIGNOBLE);
        Assert.assertTrue(regle.isObligatoire());
        Assert.assertEquals(TypeChamp.integer, regle.getType());
        Assert.assertEquals("123456", regle.getRegexp());
        Assert.assertEquals(TAG_VIGNOBLE, regle.getNom());
        System.out.println(regle.valideDonnee("valeur"));
        Assert.assertEquals("champ.pasInteger", regle.valideDonnee("valeur").get(0));
        Assert.assertEquals(1, regle.valideDonnee("valeur").size());
        Assert.assertEquals(2, regle.valideDonnee(null).size());
        Assert.assertEquals("champ.obligatoire", regle.valideDonnee(null).get(0));
    }
}
