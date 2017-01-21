//
// Fichier :
// TechnicalException.java , v 1.0 21 janv. 2015

package fr.greeniot.exception;

/**
 * Exception levée en cas d'erreur technique.
 */
public class TechnicalException extends Exception
{
  /** Serial UID. */
  private static final long serialVersionUID = -4479897795523769658L;

  /**
   * Constructeur.
   *
   * @param p_message
   *     message expliquant l'exception
   * @param p_cause
   *     de l'exception
   */
  public TechnicalException(final String p_message, final Throwable p_cause)
  {
    super(p_message, p_cause);
  }

  /**
   * Constructeur.
   *
   * @param p_message
   *     expliquant l'exception
   */
  public TechnicalException(final String p_message)
  {
    super(p_message);
  }
}
