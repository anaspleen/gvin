//
// Fichier :
// BouteilleDaoMongo.java, v 1.0 19 mai 2016 15:00:03
//
package fr.greeniot.ambrosia.service;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.junit.Assert;
import org.junit.Test;

import fr.greeniot.ambrosia.AbstractAmbrosiaTest;
import fr.greeniot.ambrosia.utils.ConstantesAMBROSIA;
import fr.greeniot.exception.BusinessException;


/**
 * @author Bull
 *         $Id$
 */
public class TestBouteilleService extends AbstractAmbrosiaTest
{

    @Test
    public void testEnregistrerAvecVerification() throws Exception
    {
        // insertion de la régle bouteille
        String fichier = "./src/test/resources/regle/regle-bouteille.json";
        getParametreDao().save(createDocumentFromFile(fichier), BOUTEILLE);

        // valide
        //        Document docStEmilionValide = createDocumentFromFile("./src/test/resources/bouteille/bouteille-st-emilion-valide.json");
        getBouteilleService().enregistrerBouteille(createBouteilleValide(), -0.1571643, 44.8949179);

        try
        {
            getBouteilleService().enregistrerBouteille(createBouteilleInvalide(), -0.1571643, 44.8949179);
            Assert.fail();
        }
        catch (BusinessException e)
        {
            System.err.println(e);
        }

    }

    @Test
    public void testEnregistrerPuisRecuperation() throws Exception
    {
        // insertion de la régle bouteille
        String fichier = "./src/test/resources/regle/regle-bouteille.json";
        getParametreDao().save(createDocumentFromFile(fichier), BOUTEILLE);

        // valide
        String id = getBouteilleService().enregistrerBouteille(createBouteilleValide(), -0.1571643, 44.8949179);

        System.out.println(id);

        // reprise
        Document boutRecup = getBouteilleService().obtenirBouteille(id);
        Assert.assertNotNull(boutRecup);
        Assert.assertEquals("Galhaud", boutRecup.getString(ConstantesAMBROSIA.TAG_BOUTEILLE_CHATEAU));

        boutRecup = getBouteilleService().obtenirBouteille("57470ec31a59e2335dcd31fa");
        Assert.assertNull(boutRecup);
    }

    /**
     * @return
     */
    private Map<String, Object> createBouteilleInvalide()
    {
        Map<String, Object> valeurs = new HashMap<String, Object>();
        valeurs.put(ConstantesAMBROSIA.TAG_BOUTEILLE_VIGNOBLE, null);

        valeurs.put(ConstantesAMBROSIA.TAG_BOUTEILLE_ACHAT_MAGASIN, "sur place");
        valeurs.put(ConstantesAMBROSIA.TAG_BOUTEILLE_ACHAT_PRIX, "jhghgjh");
        valeurs.put(ConstantesAMBROSIA.TAG_BOUTEILLE_ACHAT_DATE, new Date());

        return valeurs;
    }

    /**
     * @return
     */
    private Map<String, Object> createBouteilleValide()
    {
        Map<String, Object> valeurs = new HashMap<String, Object>();
        valeurs.put(ConstantesAMBROSIA.TAG_BOUTEILLE_NOM, "à faire");
        valeurs.put(ConstantesAMBROSIA.TAG_BOUTEILLE_ANNEE_CONSOMMATION_OPTIMALE, 2020);
        valeurs.put(ConstantesAMBROSIA.TAG_BOUTEILLE_ANNEE_MISE_EN_BOUTEILLE, 2012);
        valeurs.put(ConstantesAMBROSIA.TAG_BOUTEILLE_AOC, "Saint-Emilion");
        valeurs.put(ConstantesAMBROSIA.TAG_BOUTEILLE_APPELLATION, "Saint-Emilion Grand Cru");
        valeurs.put(ConstantesAMBROSIA.TAG_BOUTEILLE_VIGNOBLE, "Bordeaux");
        valeurs.put(ConstantesAMBROSIA.TAG_BOUTEILLE_CHATEAU, "Galhaud");

        valeurs.put(ConstantesAMBROSIA.TAG_BOUTEILLE_ACHAT_MAGASIN, "sur place");
        valeurs.put(ConstantesAMBROSIA.TAG_BOUTEILLE_ACHAT_PRIX, 20);
        valeurs.put(ConstantesAMBROSIA.TAG_BOUTEILLE_ACHAT_DATE, new Date());

        return valeurs;
    }
}
