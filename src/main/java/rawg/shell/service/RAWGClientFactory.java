package rawg.shell.service;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import feign.Feign;
import feign.Logger;
import feign.slf4j.Slf4jLogger;
import rawg.shell.utils.AuthRequestInterceptor;
import rawg.shell.utils.RAWGClient;

/**
 * Spring FactoryBean for creation of Feign RAWGClient.
 * 
 * Requires OS Environment variable RAWG_API_KEY with an RAWG API key.  If missing 
 * an exception will be thrown and the process will terminate. 
 */
@Service
public class RAWGClientFactory implements FactoryBean<RAWGClient>, InitializingBean {
	@Value("#{environment.RAWG_API_KEY}")
	private String rawgAPIKey;
	
	@Override
	public RAWGClient getObject() throws Exception {
		RAWGClient client = Feign.builder().requestInterceptor(new AuthRequestInterceptor(rawgAPIKey))
		    	.logger(new Slf4jLogger())
		    	.logLevel(Logger.Level.FULL)
		    	.target(RAWGClient.class,"https://api.rawg.io/api/");
				
		return client;
	}

	@Override
	public Class<?> getObjectType() {
		return RAWGClient.class;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (rawgAPIKey == null) {
			System.out.println("RAWG_API_KEY environment variable is missing.");
			throw new IllegalArgumentException();
		} else {
			System.out.println("Using RAWG_API_KEY found in environment variable.");
		}
	}
}
