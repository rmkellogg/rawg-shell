package rawg.shell.utils;

import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ManifestUtils {
	/**
	 * Look in the Jar MANIFEST.MF file for a Build-Time entry.  Relies on the proper 
	 * configuration of the maven-jar-plugin to include this information.
	 * 
	 * @return Build-Time information if available.
	 */
	public static String getBuildTime() {
		String result = null;
		
		try 
		{
			Resource resource = new ClassPathResource("META-INF/MANIFEST.MF");
			Manifest manifest = new Manifest(resource.getInputStream());
			Attributes attributes = manifest.getMainAttributes();
			if (attributes != null) {
				for(Object key :attributes.keySet()) {
					Attributes.Name name = (Attributes.Name) key;
					if ("Build-Time".equals(name.toString())) {
						Object valueAttribute = attributes.get(name);
						result = (String) valueAttribute;
					}
				}
			} 
		} catch (Exception e) {
			// Fail silently
		}

		return result;
	}
}
