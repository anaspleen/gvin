//
// Fichier :
// BouteilleService.java, v 1.0 19 mai 2016 15:54:04
//
package fr.greeniot.ambrosia.service;

import fr.greeniot.ambrosia.bean.UtilisateurInterface;
import fr.greeniot.ambrosia.dao.BouteilleDaoInterface;
import fr.greeniot.ambrosia.utils.ConstantesAMBROSIA;
import fr.greeniot.ambrosia.utils.ConstantesAPIREST;
import fr.greeniot.exception.BusinessException;
import fr.greeniot.exception.BusinessException.BusinessExceptionEnum;
import fr.greeniot.exception.TechnicalException;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 */
public class BouteilleService implements BouteilleServiceInterface
{

  /** le logger... */
  private static final Logger LOG = LoggerFactory.getLogger(BouteilleService.class);

  /** le dao */
  private BouteilleDaoInterface m_BouteilleDao;

  /** le service de validation */
  private ValidationServiceInterface m_ValidationService;

  public String enregistrerBouteille(Map<String, Object> p_Valeurs, double p_Longitude, double p_Latitude)
      throws BusinessException, TechnicalException
  {
    //        {
    //            "nom":"La rose brisson",
    //            "vignoble":"bordeaux",
    //            "aoc":"saint-émilion",
    //            "appellation":"saint-émilion grand cru",
    //            "chateau":"Galhaud",
    //            "anneeMiseEnBouteille":2012,
    //            "anneeConsommationOptimale":2020,
    //            "achat.date":"15/08/2015",
    //            "achat.magasin":"Chateau Galhaud",
    //            "achat.prix":20,
    //            "location":{
    //               "type":"Point",
    //               "coordinates":[
    //                  -0.1571643,
    //                  44.8949179
    //               ]
    //            }
    //         }

    // ici tester la modification si existe un tag _id=ddd

    String id = null;

    // init avec les valeurs de base
    Document doc = new Document();

    List<String> erreurs      = new ArrayList<String>();
    List<String> erreursDuTag = null;

    // vérif
    Map<String, List<String>> analyse = getValidationService().validerObjet(new Document(p_Valeurs), "bouteille");
    // on ne met que les tags présents dans keySet et sans erreurs
    for (String tagAMettre : analyse.keySet())
    {
      erreursDuTag = analyse.get(tagAMettre);

      if (erreursDuTag != null && erreursDuTag.size() > 0)
      {
        for (String erreurDuTag : erreursDuTag)
        {
          erreurs.add(tagAMettre + " : " + erreurDuTag);
        }
      }
      // on l'ajoute
      else
      {
        doc.put(tagAMettre, p_Valeurs.get(tagAMettre));
      }
    }

    if (erreurs.size() > 0)
    {
      throw new BusinessException(BusinessExceptionEnum.CODE_1_ARGUMENT_INVALIDE, StringUtils.join(erreurs, ", "));
    }
    else
    {
      // ajout de la location
      Document loc = new Document();
      loc.put(ConstantesAMBROSIA.TAG_BOUTEILLE_LOCATION_TYPE, ConstantesAMBROSIA.TAG_BOUTEILLE_LOCATION_TYPE_POINT);
      // longitude en premier (Mongo)
      loc.put(ConstantesAMBROSIA.TAG_BOUTEILLE_LOCATION_COORDINATES, Arrays.asList(p_Longitude, p_Latitude));
      doc.put(ConstantesAMBROSIA.TAG_BOUTEILLE_LOCATION, loc);

      // mise du _id
      // FIXME l'update ne fonctionne pas encore
      if (p_Valeurs.get(ConstantesAMBROSIA.TAG_ID) != null)
      {
        doc.put(ConstantesAMBROSIA.TAG_ID, new ObjectId(p_Valeurs.get(ConstantesAMBROSIA.TAG_ID) + ""));
      }

      id = getBouteilleDao().save(doc);

      if (LOG.isInfoEnabled())
      {
        LOG.info("Enregistrement de la bouteille");
      }
    }

    return id;
  }

  /**
   * @return bouteilleDao
   */
  public BouteilleDaoInterface getBouteilleDao()
  {
    return m_BouteilleDao;
  }

  /**
   * Méthode permettant d'initialiser la valeur de bouteilleDao.
   *
   * @param p_BouteilleDao
   *     le/la bouteilleDao à initialiser
   */
  public void setBouteilleDao(BouteilleDaoInterface p_BouteilleDao)
  {
    m_BouteilleDao = p_BouteilleDao;
  }

  /**
   * @return validationService
   */
  public ValidationServiceInterface getValidationService()
  {
    return m_ValidationService;
  }

  /**
   * Méthode permettant d'initialiser la valeur de validationService.
   *
   * @param p_ValidationService
   *     le/la validationService à initialiser
   */
  public void setValidationService(ValidationServiceInterface p_ValidationService)
  {
    m_ValidationService = p_ValidationService;
  }

  public Document commandGET(Document p_Requete, UtilisateurInterface p_Usager)
      throws BusinessException, TechnicalException
  {
    // TODO Auto-generated method stub
    //        return new Document(createBouteilleValide());
    return null;
  }

  public Document commandPOST(Document p_Requete, UtilisateurInterface p_Usager)
      throws BusinessException, TechnicalException
  {
    Document res = null;

    String id     = null;
    String action = "";

    try
    {
      action = p_Requete.getString(ConstantesAPIREST.ACTION);

      if (action.equals(ConstantesAPIREST.ACTION_CONSULTER))
      {
        id = p_Requete.getString(ConstantesAPIREST.TAG_IDENTIFIANT);
        res = obtenirBouteille(id);
        if (res == null)
        {
          throw new BusinessException(BusinessExceptionEnum.CODE_1_ARGUMENT_INVALIDE,
                                      "Aucune bouteille d'id : " + id + " trouvée");
        }
      }
      else if (action.equals("rechercher"))
      {
        // TODO
        //                res.put(ConstantesAPIREST.TAG_CONTENU,
        //                        importerAlerteAncientFormat(p_Usager.getIdentifiant(), p_Usager, p_Requete));
      }
      else if (action.equals("creer"))
      {
        Document bout = (Document) p_Requete.get("bouteille");

        double longitude = ((Document) bout.get("location")).getDouble("longitude");
        double latitude  = ((Document) bout.get("location")).getDouble("latitude");

        enregistrerBouteille(bout, longitude, latitude);
        //                res.put(ConstantesAPIREST.TAG_CONTENU,
        //                        importerAlerteAncientFormat(p_Usager.getIdentifiant(), p_Usager, p_Requete));
      }
      else
      {
        throw new TechnicalException("Aucune action : " + action + " trouvée");
      }
    }
    catch (BusinessException e)
    {
      throw e;
    }
    catch (Exception e)
    {
      LOG.error("Impossible de lire la requête : " + p_Requete, e);
      throw new TechnicalException("Impossible de lire la requête");
    }

    return res;

  }

  public Document obtenirBouteille(String p_ObjectId) throws BusinessException, TechnicalException
  {
    return getBouteilleDao().findById(p_ObjectId);
  }

  public List<Document> obtenirBouteilles(Map<String, String> p_Valeurs) throws BusinessException, TechnicalException
  {
    // TODO Auto-generated method stub
    return null;
  }
}
