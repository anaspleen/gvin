//
// Fichier :
// BouteilleDaoInterface.java, v 1.0 19 mai 2016 14:56:51
//
package fr.greeniot.ambrosia.dao;

import org.bson.Document;

/**
 * Dao de gestion des bouteilles
 */
public interface BouteilleDaoInterface
{

  /**
   * Enregistre une bouteille
   *
   * @return l'id : Objectid en string
   */
  String save(Document p_Document);

  /**
   * Obtention d'une bouteille suivant sont id
   */
  Document findById(String p_Id);
}
