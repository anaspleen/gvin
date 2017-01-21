//
// Fichier :
// BouteilleDaoMongo.java, v 1.0 19 mai 2016 15:00:03
//
package fr.greeniot.ambrosia.service;

import org.junit.Assert;
import org.junit.Test;

import fr.greeniot.ambrosia.AbstractAmbrosiaTest;
import fr.greeniot.exception.TechnicalException;

public class TestParametreService extends AbstractAmbrosiaTest
{
  private static final String TAG_PARAMETRE = "parametreAmbrosia";

  @Test
  public void testEnregistrer() throws Exception
  {
    // insertion de la régle bouteille
    String fichier = "./src/test/resources/parametre/parametreAmbrosia.json";
    getParametreDao().save(createDocumentFromFile(fichier), TAG_PARAMETRE);

    // on le recherche
    String urlGoogle = getParametreService().obtenirString("urlGoogle");
    Assert.assertEquals("http://www.google.fr", urlGoogle);

    Assert.assertNull(getParametreService().obtenirString("urlGoogleXX"));
  }

  @Test
  public void testException() throws Exception
  {
    // on le recherche sans avoir rensigné la table
    try
    {
      String urlGoogle = getParametreService().obtenirString("urlGoogle");
      Assert.fail();
    }
    catch (TechnicalException e)
    {
      System.err.println(e);
    }
  }
}
