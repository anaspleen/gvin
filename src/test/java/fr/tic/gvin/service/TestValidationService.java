//
// Fichier :
// BouteilleDaoMongo.java, v 1.0 19 mai 2016 15:00:03
//
package fr.tic.gvin.service;


import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;

import fr.tic.gvin.AbstractAmbrosiaTest;
import fr.tic.gvin.bean.ChampInterface;
import fr.tic.gvin.bean.TypeChamp;


/**
 * @author Bull
 *         $Id$
 */
public class TestValidationService extends AbstractAmbrosiaTest
{

    private static final String BOUTEILLE = "bouteille";
    private static final String TAG_VIGNOBLE = "vignoble";

    @Test
    public void testEnregistrer() throws Exception
    {
        // insertion de la régle bouteille
        String fichier = "./src/test/resources/regle/regle-bouteille.json";
        getRegleDao().save(createDocumentFromFile(fichier), BOUTEILLE);

        // on le recherche
        Map<String, ChampInterface> champs = getValidationService().obtenirRegles(BOUTEILLE);
        Assert.assertEquals(10, champs.size());

        ChampInterface regle = champs.get(TAG_VIGNOBLE);
        Assert.assertTrue(regle.isObligatoire());
        Assert.assertEquals(TypeChamp.string, regle.getType());
        Assert.assertEquals("123456", regle.getRegexp());
        Assert.assertEquals(TAG_VIGNOBLE, regle.getNom());

        // dans cache
        champs = getValidationService().obtenirRegles(BOUTEILLE);
        regle = champs.get(TAG_VIGNOBLE);
        Assert.assertTrue(regle.isObligatoire());
        Assert.assertEquals(TypeChamp.string, regle.getType());
        Assert.assertEquals("123456", regle.getRegexp());
        Assert.assertEquals(TAG_VIGNOBLE, regle.getNom());
    }

    @Test
    public void testVerification() throws Exception
    {
        // insertion de la régle bouteille
        String fichier = "./src/test/resources/regle/regle-bouteille.json";
        getRegleDao().save(createDocumentFromFile(fichier), BOUTEILLE);

        Document docStEmilionValide = createDocumentFromFile("./src/test/resources/bouteille/bouteille-st-emilion-valide.json");

        Map<String, List<String>> res = getValidationService().validerObjet(docStEmilionValide, BOUTEILLE);
        System.out.println(res);
        Assert.assertEquals(10, res.size());
        Assert.assertTrue(res.containsKey(TAG_VIGNOBLE));

        Document docStEmilionValideInvalide = createDocumentFromFile("./src/test/resources/bouteille/bouteille-st-emilion-invalide.json");

        res = getValidationService().validerObjet(docStEmilionValideInvalide, BOUTEILLE);
        System.out.println(res);
        Assert.assertEquals(10, res.size());
        Assert.assertTrue(res.containsKey(TAG_VIGNOBLE));
    }
}
