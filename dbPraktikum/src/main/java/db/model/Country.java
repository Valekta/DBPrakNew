package db.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Country extends Place {
	
	//To-Do: Cascade-Option überprüfen
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "continent", nullable = false)
	private Continent continent;

	@OneToMany(mappedBy = "locatedIn")
	private Set<Message> messagesById;
	
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

	public Set<Message> getMessagesById() { return messagesById; }

	public void setMessagesById(Set<Message> messagesById) { this.messagesById = messagesById; }
}
