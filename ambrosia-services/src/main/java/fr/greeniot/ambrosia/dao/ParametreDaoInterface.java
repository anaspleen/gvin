//
// Fichier :
// ParametreDaoInterface.java, v 1.0 19 mai 2016 14:56:51
//
package fr.greeniot.ambrosia.dao;

import org.bson.Document;

/**
 * Dao de gestion des paramètres : régles, parametres, ... 1 fichier JSON par entrée
 */
public interface ParametreDaoInterface
{

  /**
   * Enregistre un paramètre
   *
   * @param p_Document
   *     le document
   * @param p_NomRegle
   *     : "regleBouteille", "parametreApplication", ... (id)
   */
  void save(Document p_Document, String p_NomRegle);

  /**
   * Récupère une régle
   *
   * @param p_NomRegle
   *     : le nom, exemple : bouteille
   *
   * @return la règle si trouvée
   */
  Document find(String p_NomRegle);
}
