package rawg.shell.domain;

import rawg.shell.utils.StringUtils;

/**
 * Proxy wrapper used to generate CSV output from Game
 */
public class GameCSV {
	private String id;
	private String name;
	private String description;
	private String publisher;
	private String developer;
	private String released;
	private String tags;
	private String genres;

	public GameCSV(Game game) {
		this.id = game.getId();
		this.name = game.getName();
		this.description = game.getDescription();
		this.publisher = game.getPublisher();
		this.developer = game.getDeveloper();
		this.released = game.getReleased();
		this.tags = StringUtils.toCommaList(game.getTags());
		this.genres = StringUtils.toCommaList(game.getGenres());
	}
}
