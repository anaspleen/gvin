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
package ft.tic.gvin.service;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.tic.gvin.service.BouteilleServiceInterface;


/**
 * @author Bull
 *         $Id$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "/moduleTest/globalAC.xml" })
public class TestBouteilleService
{

    @Autowired
    @Qualifier("bouteilleService")
    private BouteilleServiceInterface service;

    @Test
    public void testperist() throws Exception
    {
        // TODO

        Map<String, Object> valeurs = new HashMap<String,Object>();

        valeurs.put("nom","st émou");
        valeurs.put("prix",20);
        
        service.enregistrerBouteille(valeurs, 0, 0, null);
        
        //        Document bouteille = new Document();
        //        bouteille.put("test", "test");
        //        dao.save(bouteille);
    }
}
