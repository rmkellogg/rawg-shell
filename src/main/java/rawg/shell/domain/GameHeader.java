package rawg.shell.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GameHeader {
	private int id;
	private String name;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
