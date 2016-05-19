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
package fr.tic.gvin.service;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.tic.gvin.AbstractAmbrosiaTest;
import fr.tic.gvin.utils.ConstantesAMBROSIA;


/**
 * @author Bull
 *         $Id$
 */
public class TestBouteilleService extends AbstractAmbrosiaTest
{

    @Test
    public void testEnregistrer() throws Exception
    {
        Map<String, Object> valeurs = new HashMap<String, Object>();
        valeurs.put(ConstantesAMBROSIA.TAG_BOUTEILLE_NOM, "à faire");
        valeurs.put(ConstantesAMBROSIA.TAG_BOUTEILLE_ANNEE_CONSOMMATION_OPTIMALE, 2020);
        valeurs.put(ConstantesAMBROSIA.TAG_BOUTEILLE_ANNEE_MISE_EN_BOUTEILLE, 2012);
        valeurs.put(ConstantesAMBROSIA.TAG_BOUTEILLE_AOC, "Saint-Emilion");
        valeurs.put(ConstantesAMBROSIA.TAG_BOUTEILLE_APPELLATION, "Saint-Emilion Grand Cru");
        valeurs.put(ConstantesAMBROSIA.TAG_BOUTEILLE_VIGNOBLE, "Bordeaux");

        Map<String, Object> valeursAchat = new HashMap<String, Object>();
        valeursAchat.put(ConstantesAMBROSIA.TAG_BOUTEILLE_ACHAT_MAGASIN, "sur place");
        valeursAchat.put(ConstantesAMBROSIA.TAG_BOUTEILLE_ACHAT_PRIX, 20);
        valeursAchat.put(ConstantesAMBROSIA.TAG_BOUTEILLE_ACHAT_DATE, new Date());

        getBouteilleService().enregistrerBouteille(valeurs, -0.1571643, 44.8949179, valeursAchat);

        //        Document bouteille = new Document();
        //        bouteille.put("test", "test");
        //        dao.save(bouteille);
    }
}
