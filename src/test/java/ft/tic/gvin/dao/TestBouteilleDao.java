//
// Fichier :
// BouteilleDaoMongo.java, v 1.0 19 mai 2016 15:00:03
//
// Projet :
// LAQME
//
// Ministère :
// Ministère de l'économie et des finances.
//
// Direction :
// DGDDI
//
// Prestation :
// Bull
//
// Copyright :
// DGDDI 2004-2016
//
// Date :
// Créé le 19 mai 2016 à 15:00:03
//
// Historique :
//
// Révision 1.0 19 mai 2016 15:00:03 caiatit
// Création.
//
//
package ft.tic.gvin.dao;


import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.tic.gvin.dao.BouteilleDaoInterface;


/**
 * @author Bull
 *         $Id$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "/moduleTest/globalAC.xml" })
public class TestBouteilleDao
{

    @Autowired
    @Qualifier("bouteilleDao")
    private BouteilleDaoInterface dao;

    @Test
    public void testperist() throws Exception
    {
        // TODO

        Document bouteille = new Document();
        bouteille.put("test", "test");
        dao.save(bouteille);
    }
}
