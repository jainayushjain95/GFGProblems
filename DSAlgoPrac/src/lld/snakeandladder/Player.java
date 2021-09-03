package lld.snakeandladder;

import java.util.UUID;

public class Player {

	private String id;
	private String name;
	
	public Player(String name) {
		super();
		this.name = name;
		this.id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	
	
}
