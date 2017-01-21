//
// Fichier :
// OperateurCritere.java, v 1.0 18 août 2015 10:27:55
// /
package fr.greeniot.ambrosia.service;

import fr.greeniot.ambrosia.service.api.rest.APIRESTServiceInterface;
import fr.greeniot.exception.TechnicalException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe qui permet d'obtenir la factory de service.
 *
 * @author thomas
 */
public final class ServiceLocator
{

  /** le logger... */
  private static final Logger LOG = LoggerFactory.getLogger(ServiceLocator.class);

  /** Contexte d'application Spring. */
  private static ServiceFactory s_ServiceFactory = null;

  /** Le fichier de configuration Spring globale */
  public static String GLOBAL_AC = "/module/ambrosia/globalAC.xml";

  /** Map des fichiers spring d'initialisation */
  private static Map<String, String> s_MapFichiersSpring = null;

  /** Contexte d'application Spring pure pour utilisation dans API. */
  private static ApplicationContext s_ContextSpring = null;

  /** gnark */
  private ServiceLocator()
  {
    // RAF
  }

  /**
   * Permet de retourner la factory proprement
   *
   * @param p_Cle
   *     la clé du globalAC
   *
   * @return la factory
   */
  public static ServiceFactory getServiceFactory(String p_Cle)
  {
    if (null == s_ServiceFactory)
    {
      s_ServiceFactory = initialiserServiceFactory(p_Cle);
    }

    return s_ServiceFactory;
  }

  /**
   * Permet de retourner la factory proprement
   *
   * @return la factory
   */
  public static ServiceFactory getServiceFactory(ApplicationContext p_Context)
  {
    if (null == s_ServiceFactory)
    {
      s_ServiceFactory = initialiserServiceFactory(p_Context);
    }

    return s_ServiceFactory;
  }

  /**
   * @param p_Cle
   *     la clé de la factory
   *
   * @return la factory
   */
  public synchronized static ServiceFactory initialiserServiceFactory(String p_Cle)
  {
    if (null == s_ServiceFactory)
    {
      try
      {
        s_MapFichiersSpring = new HashMap<String, String>();

        String fichierConf = s_MapFichiersSpring.get(p_Cle);
        if (null == fichierConf)
        {
          fichierConf = GLOBAL_AC;
        }

        s_ContextSpring = new ClassPathXmlApplicationContext(fichierConf);
        s_ServiceFactory = initialiserServiceFactory(s_ContextSpring);
      }
      catch (Exception e)
      {
        throw new RuntimeException("Erreur lors de l'initialisation de la factory", e);
      }
    }
    return s_ServiceFactory;
  }

  /**
   * @return la factory
   */
  public synchronized static ServiceFactory initialiserServiceFactory(ApplicationContext p_Context)
  {
    if (null == s_ServiceFactory)
    {
      try
      {
        if (LOG.isInfoEnabled())
        {
          String[] beans = p_Context.getBeanDefinitionNames();

          if (beans != null && beans.length > 0)
          {
            String services = StringUtils.join(beans, ", ");
            LOG.info("Services spring : " + services);
          }
        }
        s_ServiceFactory = (ServiceFactory) p_Context.getBean("ambrosiaServiceFactory");

        // OK pour Tomcat
        LOG.info("Version Services : " + s_ServiceFactory.getInformationsVersion());
      }
      catch (Exception e)
      {
        throw new RuntimeException("Erreur lors de l'initialisation de la factory", e);
      }
    }
    return s_ServiceFactory;
  }

  /**
   * @return la factory
   */
  public static ServiceFactory getServiceFactory()
  {
    // par défaut, on initialise tout
    return getServiceFactory(GLOBAL_AC);
  }

  /**
   * Pour les Tests Unitaires
   */
  public static void setGlobalACTest()
  {
    GLOBAL_AC = "classpath:/moduleTest/globalAC.xml";
  }

  /**
   * Permet d'obtenir le bon service avec un minimum de filtre au cast
   *
   * @param p_NomBean
   *     le nom du bean
   *
   * @return le service s'il existe
   *
   * @throws TechnicalException
   */
  public static APIRESTServiceInterface getService(String p_NomBean) throws TechnicalException
  {

    if (s_ContextSpring == null)
    {
      initialiserServiceFactory(GLOBAL_AC);
    }

    try
    {
      return (APIRESTServiceInterface) s_ContextSpring.getBean(p_NomBean);
    }
    catch (Exception e)
    {
      String msg = "Impossible d'obtenir le bean : " + p_NomBean;
      LOG.error(msg, e);
      throw new TechnicalException(msg);
    }
  }
}
