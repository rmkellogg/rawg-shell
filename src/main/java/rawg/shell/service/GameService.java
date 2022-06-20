package rawg.shell.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import feign.FeignException;
import rawg.shell.domain.Game;
import rawg.shell.domain.GameHeader;
import rawg.shell.utils.GameParser;
import rawg.shell.utils.RAWGClient;
import rawg.shell.utils.StringUtils;

/**
 * Spring Service interface to RAWG Games related information
 */
@Service
public class GameService {
	private static final Logger logger = LoggerFactory.getLogger(GameService.class);

	@Autowired
	private RAWGClient client;
	
	/**
	 * Lookup a game using either the numeric identifier or name.
	 * 
	 * @param id ID (required)
	 * 
	 * @return Game if found 
	 */
	public Optional<Game> describe(String id) {
		Optional<Game> result = Optional.empty();
	
		try {
			// Clean up ID a bit
			String cleanID = StringUtils.cleanID(id);
			
			String jsonText = client.getGame(cleanID);

			logger.debug(jsonText);
			
			result = GameParser.parseGame(jsonText);
		} catch (FeignException ex) {
			logger.error(ex.getMessage());
		}
		
		return result;
	}

	/**
	 * Lookup games based on query text
	 * 
	 * @param query Query text (required)
	 * 
	 * @return List of GameHeaders
	 */
	public List<GameHeader> find(String query) {
		List<GameHeader> result = new ArrayList<GameHeader>();
		
		try {
			String jsonText = client.findGame(query);
			
			logger.debug(jsonText);
			
			result = GameParser.parseGamesResult(jsonText);
			
			// Sort results numerically by Id
			Comparator<GameHeader> comp = Comparator.comparing(GameHeader::getId);
			Collections.sort(result, comp);
		} catch (FeignException ex) {
			logger.error(ex.getMessage());
		}

		return result;
	}
}
