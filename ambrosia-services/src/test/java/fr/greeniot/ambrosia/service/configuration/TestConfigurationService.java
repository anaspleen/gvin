package fr.greeniot.ambrosia.service.configuration;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import fr.greeniot.ambrosia.AbstractAmbrosiaTest;

/**
 * test de ConfigurationService
 * 
 * @author anaspleen
 *
 */
public class TestConfigurationService extends AbstractAmbrosiaTest {

	@Autowired
	@Qualifier("configurationService")
	private ConfigurationServiceInterface configurationService;

	@Test
	public void testParameter() throws Exception {

		String parameterString = configurationService.getParameterString(ConfigurationConstant.DATABASE_NAME);
		Assert.assertEquals("ambrosia_test", parameterString);
		
		parameterString = configurationService.getParameterString(ConfigurationConstant.DATABASE_URL);
		Assert.assertEquals("mongodb://localhost:27017", parameterString);
	}
}
