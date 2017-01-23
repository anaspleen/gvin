//
// Fichier :
// SetCharacterEncodingFilter.java, v 1.0 6 janv. 2016 09:55:19

//
//
package fr.greeniot.ambrosia.commun.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author thomas
 */
public class SetCharacterEncodingFilter implements Filter
{

  /** encoding */
  private String m_Encoding;

  /** desactive le filtre */
  private boolean m_Ignore;

  /** Constructeur */
  public SetCharacterEncodingFilter()
  {
    m_Encoding = null;
    m_Ignore = true;
  }

  /**
   * C'est la fin !
   *
   * @see javax.servlet.Filter#destroy()
   */
  public void destroy()
  {
    m_Encoding = null;
  }

  /**
   * {@inheritDoc}
   *
   * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, * javax.servlet
   * .FilterChain)
   */
  public void doFilter(ServletRequest p_Request, ServletResponse p_Response, FilterChain p_Chain)
      throws IOException, ServletException
  {
    if (m_Ignore || p_Request.getCharacterEncoding() == null)
    {
      String encoding = selectEncoding(p_Request);
      if (encoding != null)
      {
        p_Request.setCharacterEncoding(encoding);
      }
    }
    p_Chain.doFilter(p_Request, p_Response);
  }

  /**
   * {@inheritDoc}
   *
   * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
   */
  public void init(FilterConfig p_FilterConfig) throws ServletException
  {
    m_Encoding = p_FilterConfig.getInitParameter("encoding");
    String value = p_FilterConfig.getInitParameter("ignore");
    if (value == null)
    {
      m_Ignore = true;
    }
    else if (value.equalsIgnoreCase("true"))
    {
      m_Ignore = true;
    }
    else if (value.equalsIgnoreCase("yes"))
    {
      m_Ignore = true;
    }
    else
    {
      m_Ignore = false;
    }
  }

  /**
   * A priori, ne sert à rien
   *
   * @param p_Request
   *     la requete
   *
   * @return l'encoding, qui est fixe a priori.
   */
  protected String selectEncoding(ServletRequest p_Request)
  {
    return m_Encoding;
  }
}
