//
// Fichier :
// OperateurCritere.java, v 1.0 18 août 2015 10:27:55
// /
package fr.greeniot.ambrosia.service;


import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.jar.Manifest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.greeniot.ambrosia.service.api.rest.CommandServiceInterface;


/**
 * La factory des services disponibles pour AMBROSIA.
 * 
 * @author thomas
 */
public class ServiceFactory
{

    /**
     * le logger...
     */
    private static final Logger LOG = LoggerFactory.getLogger(ServiceFactory.class);

    /** le service pour l'API REST IHM */
    private CommandServiceInterface m_CommandService;

    /**
     * Cette méthode permet de récupérer les informations de version dans le jar de l'application.
     */
    public static void lireInformationsVersion()
    {
        String informations = getInformationsVersion();
        if (LOG.isInfoEnabled())
        {
            LOG.info(informations);
        }
    }

    /**
     * Cette méthode permet de récupérer les informations de version dans le jar de l'application.
     * 
     * @param p_WebappManifest
     *            path vers la racine de la webapp
     * @return les informations de version de Delia sous forme de String
     */
    public static String getInformationsVersionWebapp(InputStream p_WebappManifest)
    {
        String informations = "Aucune information de version.";
        try
        {
            Manifest manifest = new Manifest(p_WebappManifest);

            Iterator<Object> it = manifest.getMainAttributes().keySet().iterator();
            boolean trouve = false;

            while (it.hasNext() && false == trouve)
            {
                Object tmp = it.next();
                String key = tmp.toString();
                if (key.equals("Build-Tag"))
                {
                    informations = manifest.getMainAttributes().get(tmp).toString();
                    trouve = true;
                }
            }
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            informations = "Erreur lors de la lecture des informations de version de l'application " + "lila" + " ("
                    + t.getMessage() + ")";
        }
        return informations;
    }

    /**
     * Cette méthode permet de récupérer les informations de version dans le jar de l'application.
     * 
     * @return les informations de version de Delia sous forme de String
     */
    public static String getInformationsVersion()
    {
        String informations = "Aucune information de version.";
        try
        {
            String classContainer = ServiceFactory.class.getProtectionDomain().getCodeSource().getLocation().toString();

            // jonas : URL manifestUrl = new URL("jar:" + classContainer + "!/META-INF/MANIFEST.MF");
            // Tomcat : URL manifestUrl = new URL("jar:" + classContainer + "!/META-INF/MANIFEST.MF");
            // JBoss : URL manifestUrl = new URL("jar:" + classContainer + "/META-INF/MANIFEST.MF");
            URL manifestUrl = new URL("jar:" + classContainer + "!/META-INF/MANIFEST.MF");
            Manifest manifest = new Manifest(manifestUrl.openStream());

            Iterator<Object> it = manifest.getMainAttributes().keySet().iterator();
            boolean trouve = false;

            while (it.hasNext() && false == trouve)
            {
                Object tmp = it.next();
                String key = tmp.toString();
                if (key.equals("Build-Tag"))
                {
                    informations = manifest.getMainAttributes().get(tmp).toString();
                    trouve = true;
                }
            }
        }
        catch (Throwable t)
        {
            informations = "Erreur lors de la lecture des informations de version de l'application " + " lila " + " ("
                    + t.getMessage() + ")";
        }
        return informations;
    }

    /**
     * @return commandService
     */
    public CommandServiceInterface getCommandService()
    {
        return m_CommandService;
    }

    /**
     * Méthode permettant d'initialiser la valeur de commandService.
     * 
     * @param p_CommandService
     *            le/la commandService à initialiser
     */
    public void setCommandService(CommandServiceInterface p_CommandService)
    {
        m_CommandService = p_CommandService;
    }
}
