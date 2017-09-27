package db.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class University extends Organisation{
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "locatedIn", nullable = false)
	private City locatedIn;

	@OneToMany(mappedBy = "university")
	private Set<UniversityMembership> memberships = new HashSet<UniversityMembership>();
	
	public University() {}
	
	public University(int id, String name, City city) {
		this.id = id;
		this.name = name;
		this.locatedIn = city;
	}

	/* getters and setters */
	public City getLocatedIn() {
		return locatedIn;
	}

	public void setLocatedIn(City locatedIn) {
		this.locatedIn = locatedIn;
	}
	
	public Set<UniversityMembership> getMemberships() {
		return memberships;
	}

	public void setMemberships(Set<UniversityMembership> memberships) {
		this.memberships = memberships;
	}
}
