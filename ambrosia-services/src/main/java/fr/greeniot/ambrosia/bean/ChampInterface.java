//
// Fichier :
// RegleInterface.java, v 1.0 20 mai 2016 12:50:50
//
package fr.greeniot.ambrosia.bean;

import java.util.List;

/**
 * Définition d'une régle.
 */
public interface ChampInterface
{

  /** @return la regex */
  String getRegexp();

  /** @return le type */
  TypeChamp getType();

  /** @return true si obligatoire */
  boolean isObligatoire();

  /** @return le nom du tag */
  String getNom();

  /**
   * @param p_DonneeAVerifier
   *     la donnnée à vérifier
   *
   * @return les erreurs relevées
   */
  List<String> valideDonnee(Object p_DonneeAVerifier);
}
