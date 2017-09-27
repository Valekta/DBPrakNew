package db.model;

import javax.persistence.Entity;

@Entity
public class Continent extends Place {
	
	public Continent() {}
	
	public Continent(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
