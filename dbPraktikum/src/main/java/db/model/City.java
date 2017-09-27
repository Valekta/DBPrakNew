package db.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class City extends Place {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "country", nullable = false)
	private Country country;
	
	public City() {}
	
	public City(int id, String name, Country country) {
		this.id = id;
		this.name = name;
		this.country = country;
	}
	/* getters & setters */
	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}	
}
