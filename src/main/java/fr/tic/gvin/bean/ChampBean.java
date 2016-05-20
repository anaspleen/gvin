//
// Fichier :
// ChampBean.java, v 1.0 20 mai 2016 13:06:07
//
package fr.tic.gvin.bean;


import java.io.Serializable;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.tic.gvin.exception.BusinessException;
import fr.tic.gvin.exception.BusinessException.BusinessExceptionEnum;


/**
 * @author Bull
 *         $Id$
 */
public class ChampBean implements ChampInterface, Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** le logger... */
    private static final Logger LOG = LoggerFactory.getLogger(ChampBean.class);

    // les tags 
    private static final String TAG_REGEXP = "regex";
    private static final String TAG_TYPE = "type";
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
     * Ici, transformation du document en champ exploitable
     * 
     * @param p_Document
     *            le document
     * @param p_Tag
     *            le tag "vignoble" par exemple
     */
    public ChampBean(Document p_Document, String p_Tag) throws BusinessException
    {
        try
        {
            m_Nom = p_Tag;
            Document donnees = (Document) p_Document.get(p_Tag);

            m_Regexp = donnees.getString(TAG_REGEXP);
            m_Type = TypeChamp.valueOf(donnees.getString(TAG_TYPE));
            m_Obligatoire = donnees.getBoolean(TAG_OBLIGATOIRE, false);
        }
        catch (Exception e)
        {
            String msg = "Erreur lors de la construction du champ : " + p_Tag;
            LOG.error(msg, e);
            throw new BusinessException(BusinessExceptionEnum.CODE_1_ARGUMENT_INVALIDE, msg);
        }
    }

    /**
     * Const
     */
    private ChampBean()
    {
        // RAF
    }

    /*
     * (non-Javadoc)
     * @see fr.tic.gvin.bean.ChampInterface#getNom()
     */
    public String getNom()
    {
        return m_Nom;
    }

    /*
     * (non-Javadoc)
     * @see fr.tic.gvin.bean.ChampInterface#isObligatoire()
     */
    public boolean isObligatoire()
    {
        return m_Obligatoire;
    }

    /*
     * (non-Javadoc)
     * @see fr.tic.gvin.bean.ChampInterface#getRegexp()
     */
    public String getRegexp()
    {
        return m_Regexp;
    }

    /*
     * (non-Javadoc)
     * @see fr.tic.gvin.bean.ChampInterface#getType()
     */
    public TypeChamp getType()
    {
        return m_Type;
    }

}
