//
// Fichier :
// BouteilleDaoMongo.java, v 1.0 19 mai 2016 15:00:03
//
package fr.greeniot.ambrosia.service;

import fr.greeniot.ambrosia.AbstractAmbrosiaTest;
import fr.greeniot.ambrosia.bean.ChampInterface;
import fr.greeniot.ambrosia.bean.TypeChamp;
import fr.greeniot.ambrosia.utils.ConstantesAMBROSIA;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class TestValidationService extends AbstractAmbrosiaTest
{
  private static final String TAG_VIGNOBLE = "vignoble";

  @Test
  public void testEnregistrer() throws Exception
  {
    // insertion de la régle bouteille
    String fichier = "./src/test/resources/regle/regle-bouteille.json";
    getParametreDao().save(createDocumentFromFile(fichier), BOUTEILLE);

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
    getParametreDao().save(createDocumentFromFile(fichier), BOUTEILLE);

    // valide
    Document docStEmilionValide = createDocumentFromFile(
        "./src/test/resources/bouteille/bouteille-st-emilion-valide.json");
    Map<String, List<String>> res = getValidationService().validerObjet(docStEmilionValide, BOUTEILLE);
    System.out.println(res);
    Assert.assertEquals(10, res.size());
    Assert.assertTrue(res.containsKey(TAG_VIGNOBLE));

    // ligne en plus
    Document docStEmilionValideInvalide = createDocumentFromFile(
        "./src/test/resources/bouteille/bouteille-invalide-ligne-en-plus.json");
    res = getValidationService().validerObjet(docStEmilionValideInvalide, BOUTEILLE);
    System.out.println(res);
    Assert.assertEquals(10, res.size());
    Assert.assertTrue(res.containsKey(TAG_VIGNOBLE));

    // un champ obligatoire vide
    Document docStEmilionValideInvalide2 = createDocumentFromFile(
        "./src/test/resources/bouteille/bouteille-invalide-champ-obligatoire-vide.json");
    res = getValidationService().validerObjet(docStEmilionValideInvalide2, BOUTEILLE);
    System.out.println(res);
    Assert.assertEquals(10, res.size());
    Assert.assertTrue(res.containsKey(TAG_VIGNOBLE));
    Assert.assertEquals("champ.obligatoire", res.get(ConstantesAMBROSIA.TAG_BOUTEILLE_NOM).get(0));

    // un champ integer pas integer
    Document docStEmilionValideInvalide3 = createDocumentFromFile(
        "./src/test/resources/bouteille/bouteille-invalide-champ-integer-texte.json");
    res = getValidationService().validerObjet(docStEmilionValideInvalide3, BOUTEILLE);
    System.out.println(res);
    Assert.assertEquals(10, res.size());
    Assert.assertTrue(res.containsKey(TAG_VIGNOBLE));
    List<String> resAnneeMEB = res.get(ConstantesAMBROSIA.TAG_BOUTEILLE_ANNEE_MISE_EN_BOUTEILLE);
    Assert.assertNotNull(resAnneeMEB);
    Assert.assertTrue(resAnneeMEB.size() > 0);
    String anneeMiseEnBouteille = resAnneeMEB.get(0);
    Assert.assertNotNull(anneeMiseEnBouteille);
    Assert.assertEquals("champ.pasInteger", anneeMiseEnBouteille);

  }
}
