package rawg.shell.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.ReadContext;

import rawg.shell.domain.Game;
import rawg.shell.domain.GameHeader;

/**
 * Parser for JSON Game information.  
 * 
 * Note: Lots of room for error handling improvements and testing
 */
public class GameParser {
	private static final Logger logger = LoggerFactory.getLogger(GameParser.class);

	/**
	 * Configuration used to suppress missing attributes
	 */
	private static Configuration jsonPathConfig;
	
	static {
		Configuration conf = Configuration.defaultConfiguration();
		jsonPathConfig = conf.addOptions(Option.SUPPRESS_EXCEPTIONS);
	}
	
	public static Optional<Game> parseGame(String jsonText) {
		Game result = new Game();
		
		try {
			ReadContext context = JsonPath.using(jsonPathConfig).parse(jsonText);
			
			result = new Game();

			result.setId(context.read("$.id", String.class));
			result.setName(context.read("$.name"));
			result.setDescription(context.read("$.description_raw"));
			result.setReleased(context.read("$.released"));
			result.setDeveloper(context.read("$.developers[0].name"));
			result.setPublisher(context.read("$.publishers[0].name"));
			result.setGenres(context.read("$.genres[*].name", List.class));
			result.setTags(context.read("$.tags[*].name", List.class));
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		return Optional.ofNullable(result);
	}
	
	public static List<GameHeader> parseGamesResult(String jsonText) {
		List<GameHeader> result = new ArrayList<GameHeader>();

		try {
			// Parse complex results using Jackson manually
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(jsonText);
			
			JsonNode resultsArray = root.get("results");
			for(int index = 0; index < resultsArray.size(); index++) {
				JsonNode resultNode = resultsArray.get(index);
				GameHeader header = new GameHeader();
				
				header.setId(resultNode.get("id").asInt());
				header.setName(resultNode.get("name").asText());
				
				result.add(header);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		return result;
	}
}
