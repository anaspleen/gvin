/**
 * 
 */
package fr.greeniot.ambrosia.service.configuration;

import fr.greeniot.exception.BusinessException;
import fr.greeniot.exception.TechnicalException;

/**
 * Service de configuration d'accès aux properties
 * 
 * @author anaspleen
 */
public interface ConfigurationServiceInterface {

	/**
	 * Get parameter key
	 * 
	 * @param key
	 *            key to search
	 * @return value string
	 * @throws BusinessException
	 *             applicatives exceptions
	 * @throws TechnicalException
	 *             technical exception
	 */
	String getParameterString(String key) throws BusinessException, TechnicalException;

	/**
	 * Get parameter key
	 * 
	 * @param key
	 *            key to search
	 * @return value integer
	 * @throws BusinessException
	 *             applicatives exceptions
	 * @throws TechnicalException
	 *             technical exception
	 */
	int getParameterInteger(String key) throws BusinessException, TechnicalException;

	/**
	 * Initialize parameters
	 * 
	 * @throws BusinessException
	 *             applicatives exceptions
	 * @throws TechnicalException
	 *             technical exception
	 */
	void initParameters() throws BusinessException, TechnicalException;
}
