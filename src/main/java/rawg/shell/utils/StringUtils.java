package rawg.shell.utils;

import java.util.List;

public class StringUtils {
	public static String toCommaList(List<String> items) {
		String result = null;
		
		if (!items.isEmpty()) {
			StringBuilder text = new StringBuilder();
			
			for(String item : items) {
				if (text.length() > 0) {
					text.append(",");
				}
				text.append(item);
			}
		
			result = text.toString();
		}
		
		return result;
	}

	/**
	 * Clean up search text to lower case, replace spaces with dashes and
	 * normalize spaces to single instance
	 * 
	 * @param id Search ID (required)
	 * 
	 * @return Cleaned ID
	 */
	public static String cleanID(String id) {
		String result = null;
		
		if (id != null) {
			// Convert multiple whitespace characters to singular
			result = org.apache.commons.lang3.StringUtils.normalizeSpace(id);
			// Lower case text
			result = result.toLowerCase();
			// Convert whitespace to dashes
			result = result.replaceAll("\\s","-");
		}
		
		return result;
	}
}
