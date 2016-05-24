//
// Fichier :
// ConfigurationServiceInterface.java, v 1.0 12 mars 2015 10:42:58
// /
package fr.greeniot.ambrosia.api.rest.resource.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.greeniot.ambrosia.service.ServiceFactory;
import fr.greeniot.ambrosia.service.ServiceLocator;


/**
 * @author thomas
 */
public class InitialiserServlet extends HttpServlet
{

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * L'instance de Logging.
     */
    private static final Logger LOG = LoggerFactory.getLogger(InitialiserServlet.class);

    /**
    *
    */
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

        //        try
        //        {
        getServiceFactory();

        LOG.info("Version Webapps : "
                + ServiceLocator.getServiceFactory().getInformationsVersionWebapp(
                        getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF")));

        // le scheduler
        //            SchedulerFactory sf = new StdSchedulerFactory();
        //            Scheduler scheduler = sf.getScheduler();
        //
        //            List<CronJobBean> jobs = recuperationDesJobs();
        //
        //            JobDetailImpl jobImpl = null;
        //            CronTriggerImpl triggerImpl = null;
        //            String nom = null;
        //            String cronExpression = null;
        //
        //            // itération sur les jobs récupéres
        //            for (CronJobBean job : jobs)
        //            {
        //                nom = job.getNom();
        //
        //                // ici chercher le cronExpression
        //                cronExpression = getServiceFactory().getConfigurationService().getCronBatch(nom);
        //                if (StringUtils.isEmpty(cronExpression))
        //                {
        //                    cronExpression = job.getCronExpression();
        //                    LOG.info("Prise du cron par défaut du job : " + nom);
        //                }
        //
        //                // job
        //                jobImpl = new JobDetailImpl();
        //
        //                jobImpl.setName(nom);
        //                jobImpl.setDescription("Description du job : " + nom);
        //
        //                // vérifier l'implémentation
        //                try
        //                {
        //                    jobImpl.setJobClass((Class<Job>) job.getJobClass());
        //                }
        //                catch (Exception e)
        //                {
        //                    LOG.error("Impossible de caster la classe job", e);
        //                    throw e;
        //                }
        //
        //                // trigger
        //                triggerImpl = new CronTriggerImpl();
        //                triggerImpl.setCronExpression(new CronExpression(cronExpression));
        //                triggerImpl.setName("trigger" + nom);
        //
        //                // ajout dans le scheduler
        //                scheduler.scheduleJob(jobImpl, triggerImpl);
        //
        //                if (LOG.isInfoEnabled())
        //                {
        //                    String[] traces =
        //                    { nom, job.getJobClass().getName(), cronExpression };
        //                    LOG.info("Ajout du job : {}, class : {}, cron : {}", traces);
        //                }
        //            }
        //
        //            // set le nom
        //            getServletContext().setAttribute(QuartzInitializerListener.QUARTZ_FACTORY_KEY, sf);
        //
        //            // start
        //            scheduler.start();
        //
        //        }
        //        catch (SchedulerException e)
        //        {
        //            throw new RuntimeTechnicalException("Erreur lors de la création du job scheduling", e);
        //        }

    }

    /**
     * @return
     */
    private ServiceFactory getServiceFactory()
    {
        return ServiceLocator.getServiceFactory("GLOBAL_AC");
    }

    /**
     * Renvoie la liste des jobs déclarés par la factory.
     * 
     * @return la liste des jobs déclarés par la factory.
     * @throws ApplicationException
     *             en cas de soucis
     */
    //    private List<CronJobBean> recuperationDesJobs() throws ApplicationException
    //    {
    //        List<CronJobBean> jobs = getServiceFactory().getListCron();
    //        if (LOG.isDebugEnabled())
    //        {
    //            if (null == jobs)
    //            {
    //                LOG.debug("Il n'y a aucun job.");
    //            }
    //            else
    //            {
    //                LOG.debug("Il y a {} jobs.", jobs.size());
    //            }
    //        }
    //        return jobs;
    //    }
}
