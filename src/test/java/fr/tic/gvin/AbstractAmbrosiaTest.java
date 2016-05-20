package fr.tic.gvin;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import fr.tic.gvin.dao.BouteilleDaoInterface;
import fr.tic.gvin.dao.RegleDaoInterface;
import fr.tic.gvin.service.BouteilleService;
import fr.tic.gvin.service.BouteilleServiceInterface;


/**
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "/moduleTest/globalAC.xml" })
public abstract class AbstractAmbrosiaTest
{

    /** le logger... */
    private static final Logger LOG = LoggerFactory.getLogger(BouteilleService.class);

    @Autowired
    @Qualifier("bouteilleDao")
    private BouteilleDaoInterface m_BouteilleDao;

    @Autowired
    @Qualifier("regleDao")
    private RegleDaoInterface m_RegleDao;

    /**
     * Le Service gérant les flux.
     */
    @Autowired
    @Qualifier("bouteilleService")
    private BouteilleServiceInterface m_BouteilleService;

    /**
     * Cette méthode est appelée avant chaque test.<br/>
     * Nettoie la base. Peut être surchargée pour insérer des données en base avant chaque test.
     * 
     * @throws Exception
     *             en cas de souci.
     */
    @Before
    public void setUp() throws Exception
    {
        getDatabase().drop();
        LOG.info("Base ambrosia droppée");
    }

    /**
     * Cette méthode est appelée après chaque test.<br/>
     * Utile essentiellement pour relâcher des ressources allouées avec la méthode setUp().
     * 
     * @throws Exception
     *             en cas de souci.
     */
    @After
    public void tearDown() throws Exception
    {
        // RAF
    }

    /**
     * @return le client
     */
    protected MongoClient getClient()
    {
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        return client;
    }

    /**
     * @return la base
     */
    protected MongoDatabase getDatabase()
    {
        MongoDatabase database = getClient().getDatabase("ambrosia_test");
        return database;
    }

    /**
     * @return bouteilleService
     */
    public BouteilleServiceInterface getBouteilleService()
    {
        return m_BouteilleService;
    }

    /**
     * @return bouteilleDao
     */
    public BouteilleDaoInterface getBouteilleDao()
    {
        return m_BouteilleDao;
    }

    /**
     * @return regleDao
     */
    public RegleDaoInterface getRegleDao()
    {
        return m_RegleDao;
    }

    /**
     * @param p_CheminFichier
     *            le chemin du fichier
     * @return le JSON
     * @throws IOException
     *             soucis
     */
    protected Document createDocumentFromFile(String p_CheminFichier) throws IOException
    {
        return Document.parse(FileUtils.readFileToString(new File(p_CheminFichier)));
    }
}
