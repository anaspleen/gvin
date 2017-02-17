/**
 * 
 */
package fr.greeniot.ambrosia.service.configuration;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.greeniot.exception.BusinessException;
import fr.greeniot.exception.TechnicalException;

/**
 * Impl
 * 
 * @author anaspleen
 *
 */
public class ConfigurationService implements ConfigurationServiceInterface {

	/** logger... */
	private static final Logger LOG = LoggerFactory.getLogger(ConfigurationService.class);

	/** properties */
	private static Properties s_Properties = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.greeniot.ambrosia.service.configuration.ConfigurationServiceInterface#
	 * getParameterString(java.lang.String)
	 */
	@Override
	public String getParameterString(String key) throws BusinessException, TechnicalException {
		Properties property = getProperties();
		return property.getProperty(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.greeniot.ambrosia.service.configuration.ConfigurationServiceInterface#
	 * getParameterInteger(java.lang.String)
	 */
	@Override
	public int getParameterInteger(String key) throws BusinessException, TechnicalException {
		int res = 0;

		String string = getParameterString(key);
		if (!StringUtils.isEmpty(string)) {
			try {
				res = Integer.valueOf(string);
			} catch (Exception e) {
				LOG.error("Error cast to Integer key : " + key, e);
			}
		}

		return res;
	}

	/**
	 * 
	 * @return les properties
	 */
	private Properties getProperties() {
		if (s_Properties == null) {
			s_Properties = loadProperties();
		}

		return s_Properties;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.greeniot.ambrosia.service.configuration.ConfigurationServiceInterface#
	 * initParameters()
	 */
	@Override
	public void initParameters() throws BusinessException, TechnicalException {
		s_Properties = null;
		loadProperties();
	}

	/**
	 * @return properties
	 */
	private synchronized Properties loadProperties() {
		if (s_Properties == null) {
			LOG.info("BEGIN load configuration AMBROSIA");

			Properties property = new Properties();
			String name = "ambrosia-configuration.properties";

			try {
				property.load(this.getClass().getClassLoader().getResourceAsStream(name));
			} catch (Exception e) {
				String msg = "Can't load file : " + name;
				LOG.error(msg, e);
				throw new RuntimeException(msg);
			}

			s_Properties = property;

			LOG.info("END load configuration AMBROSIA");
		}

		return s_Properties;
	}
}
