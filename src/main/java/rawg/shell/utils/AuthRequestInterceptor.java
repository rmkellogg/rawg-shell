package rawg.shell.utils;

import java.util.ArrayList;
import java.util.List;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Used to append an API key query parameter to all requests
 */
public class AuthRequestInterceptor implements RequestInterceptor {
	private String rawgAPIKey;
	
	public AuthRequestInterceptor(String rawgAPIKey) {
		this.rawgAPIKey = rawgAPIKey; 
	}
	
	@Override
	public void apply(RequestTemplate template) {
		List<String> values = new ArrayList<String>();
		values.add(rawgAPIKey);
		
		template.query("key",values);
	}
}
