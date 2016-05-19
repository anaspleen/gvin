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

import ft.tic.gvin.AbstractAmbrosiaTest;


/**
 * @author Bull
 *         $Id$
 */
public class TestBouteilleService extends AbstractAmbrosiaTest
{

    @Test
    public void testEnregistrer() throws Exception
    {
        // TODO

        Map<String, Object> valeurs = new HashMap<String, Object>();

        valeurs.put("nom", "st émou");
        valeurs.put("prix", 20);

        getBouteilleService().enregistrerBouteille(valeurs, 0, 0, null);

        //        Document bouteille = new Document();
        //        bouteille.put("test", "test");
        //        dao.save(bouteille);
    }
}
