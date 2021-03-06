//
// Fichier :
// ChampBean.java, v 1.0 20 mai 2016 13:06:07
//
package fr.greeniot.ambrosia.bean;

import fr.greeniot.exception.BusinessException;
import fr.greeniot.exception.BusinessException.BusinessExceptionEnum;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChampBean implements ChampInterface, Serializable
{
  /** Serial Uid.. */
  private static final long serialVersionUID = 1L;

  /** le logger... */
  private static final Logger LOG = LoggerFactory.getLogger(ChampBean.class);

  // les tags
  private static final String TAG_REGEXP      = "regexp";
  private static final String TAG_TYPE        = "type";
  private static final String TAG_OBLIGATOIRE = "obligatoire";

  /** la regexp */
  private String m_Regexp;

  /** le type */
  private TypeChamp m_Type;

  /** obligatoire */
  private boolean m_Obligatoire;

  /** nom */
  private String m_Nom;

  /**
   * Transformation du document en champ exploitable
   *
   * @param p_Document
   *     le document
   * @param p_Tag
   *     le tag "vignoble" par exemple
   */
  public ChampBean(Document p_Document, String p_Tag) throws BusinessException
  {
    try
    {
      m_Nom = p_Tag;
      m_Regexp = p_Document.getString(TAG_REGEXP);
      m_Type = TypeChamp.valueOf(p_Document.getString(TAG_TYPE));
      m_Obligatoire = p_Document.getBoolean(TAG_OBLIGATOIRE, false);
    }
    catch (Exception e)
    {
      String msg = "Erreur lors de la construction du champ : " + p_Tag;
      LOG.error(msg, e);
      throw new BusinessException(BusinessExceptionEnum.CODE_1_ARGUMENT_INVALIDE, msg);
    }
  }

  /** Const */
  private ChampBean()
  {
    // RAF
  }

  public String getNom()
  {
    return m_Nom;
  }

  public boolean isObligatoire()
  {
    return m_Obligatoire;
  }

  public String getRegexp()
  {
    return m_Regexp;
  }

  public TypeChamp getType()
  {
    return m_Type;
  }

  public List<String> valideDonnee(Object p_DonneeAVerifier)
  {
    List<String> erreurs = new ArrayList<String>();

    // oblig
    if (isObligatoire() && p_DonneeAVerifier == null)
    {
      erreurs.add("champ.obligatoire");
    }

    // le type
    switch (getType())
    {
      case date:
        // TODO later
        break;
      case integer:
        try
        {
          Integer.valueOf(p_DonneeAVerifier + "");
        }
        catch (Exception e)
        {
          LOG.error("Erreur lors du cast de : " + p_DonneeAVerifier, e);
          erreurs.add("champ.pasInteger");
        }

        break;
      case string:
        // rien à faire, c'est valide
        break;
      default:
        break;
    }

    // TODO la regex

    // retourne les erreurs
    return erreurs;
  }
}
