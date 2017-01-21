//
// Fichier :
// BouteilleDaoMongo.java, v 1.0 19 mai 2016 15:00:03
//
package fr.greeniot.ambrosia.dao;

import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;

import fr.greeniot.ambrosia.AbstractAmbrosiaTest;

public class TestBouteilleDao extends AbstractAmbrosiaTest
{

  @Test
  public void testPersist() throws Exception
  {

    Document bouteille = new Document();
    bouteille.put("test", "test");
    String id = getBouteilleDao().save(bouteille);

    System.out.println(id);

    // recup
    Document recup = getBouteilleDao().findById(id);
    Assert.assertNotNull(recup);

    recup = getBouteilleDao().findById("57470b041a59e22fd2e0a6ea");
    Assert.assertNull(recup);
  }
}
