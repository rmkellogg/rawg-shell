package rawg.shell.utils;

import feign.Param;
import feign.RequestLine;

/**
 * Feign REST client proxy
 */
public interface RAWGClient {
	/**
	 * Precise lookup of game with Slug or ID
	 * 
	 * @param id Lookup ID (required)
	 * 
	 * @return JsonText response
	 */
	@RequestLine("GET /games/{id}")
	String getGame(@Param("id") String id);
	
	/**
	 * Search for games with query text
	 * 
	 * @param query Query Text (required)
	 * 
	 * @return JsonText response of multiple games
	 */
	@RequestLine("GET /games?search={query}")
	String findGame(@Param("query") String query);
}
