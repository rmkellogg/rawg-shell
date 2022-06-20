package rawg.shell.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import rawg.shell.domain.Game;
import rawg.shell.domain.GameCSV;
import rawg.shell.domain.GameHeader;
import rawg.shell.service.GameService;

/**
 * Spring Shell Game related commands
 */
@ShellComponent("Games")
public class GameCommands {
	@Autowired
	private GameService service;

	@ShellMethod(key = "game describe", value = "Retrieve full game information based on RAWG ID or title.")
	public void describe(@ShellOption(help = "ID", value = "-id") String id) {
		Optional<Game> gameResult = service.describe(id);
		if (gameResult.isPresent()) {
			Game game = gameResult.get();

			System.out.println("ID: " + game.getId());
			System.out.println("Name: " + game.getName());
			if (game.getDescription() != null) {
				System.out.println("Description: " + game.getDescription());
			}
			if (game.getReleased() != null) {
				System.out.println("Released: " + game.getReleased());
			}
			if (game.getDeveloper() != null) {
				System.out.println("Developer: " + game.getDeveloper());
			}
			if (game.getPublisher() != null) {
				System.out.println("Publishers: " + game.getPublisher());
			}
			if ((game.getGenres() != null) && !game.getGenres().isEmpty()) {
				System.out.println("Genres: " + rawg.shell.utils.StringUtils.toCommaList(game.getGenres()));
			}
			if ((game.getTags() != null) && !game.getTags().isEmpty()) {
				System.out.println("Tags: " + rawg.shell.utils.StringUtils.toCommaList(game.getTags()));
			}
		} else {
			System.out.println("Game not found");
		}
	}

	@ShellMethod(key = "game export", value = "Retrieve game information on multiple titles.")
	public void export(@ShellOption(help = "Input Filename", value = "-input") String inputFileName,
			                 @ShellOption(help = "Output Filename", value = "-output") String outputFileName) 
	throws Exception {
		File inputFile = new File(inputFileName);
		if (inputFile.exists() && inputFile.canRead()) {
			List<String> lines = FileUtils.readLines(inputFile, Charset.forName("UTF-8"));

			if (lines != null) {
				List<Game> games = new ArrayList<Game>();

				// Retrieve information on each game title
				for (String line : lines) {
					String id = StringUtils.trimToNull(line);
					if (id != null) {
						Optional<Game> gameResult = service.describe(id);
						if (gameResult.isPresent()) {
							games.add(gameResult.get());
						}
					}
				}

				// Output game information to file
				if (!games.isEmpty()) {
					Writer writer = new FileWriter(outputFileName);
					List<GameCSV> gamesCSV = new ArrayList<GameCSV>();
					
					System.out.println("Processing " + games.size() + " records.");

					// Convert to wrapper Game 
					for(Game game : games) {
						GameCSV gameCSV = new GameCSV(game);
						gamesCSV.add(gameCSV);
					}
					
					StatefulBeanToCsv<GameCSV> beanToCsv = new StatefulBeanToCsvBuilder<GameCSV>(writer).build();
					beanToCsv.write(gamesCSV);
					
					writer.close();
				} else {
					System.out.println("No results found.");
				}
			}
		} else {
			System.out.println("Input file " + inputFile.getCanonicalPath() + " does not exist.");
		}
	}

	@ShellMethod(key = "game search", value = "Search for games")
	public void search(@ShellOption(help = "query") String query) {
		List<GameHeader> games = service.find(query);

		for (GameHeader game : games) {
			System.out.println(game.getId() + "/" + game.getName());
		}
	}
}
