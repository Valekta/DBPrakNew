package db.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Country extends Place {
	
	//To-Do: Cascade-Option überprüfen
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "continent", nullable = false)
	private Continent continent;
	
	public Country() {}
	
	public Country(int id, String name, Continent continent) {
		this.id = id;
		this.name = name;
		this.continent = continent;
	}
	
	/* getters & setters */
	public Continent getContinent() {
		return continent;
	}
	
	public void setContinent(Continent continent) {
		this.continent = continent;
	}	
}
