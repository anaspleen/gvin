//
// Fichier :
// ConfigurationServiceInterface.java, v 1.0 12 mars 2015 10:42:58
// /
package fr.greeniot.ambrosia.api.rest.servlet;

import fr.greeniot.ambrosia.service.ServiceFactory;
import fr.greeniot.ambrosia.service.ServiceLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @author thomas
 */
public class InitialiserServlet extends HttpServlet
{

  /** serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** L'instance de Logging. */
  private static final Logger LOG = LoggerFactory.getLogger(InitialiserServlet.class);

  public InitialiserServlet()
  {
    super();
  }

  /**
   * {@inheritDoc}
   *
   * @see javax.servlet.GenericServlet#init()
   */
  @SuppressWarnings("unchecked")
  public void init() throws ServletException
  {
    super.init();

    getServiceFactory();

    LOG.info("Version Webapps : " + ServiceLocator.getServiceFactory()
                                                  .getInformationsVersionWebapp(getServletContext().getResourceAsStream(
                                                      "/META-INF/MANIFEST.MF")));
  }

  private ServiceFactory getServiceFactory()
  {
    return ServiceLocator.getServiceFactory("GLOBAL_AC");
  }

}
