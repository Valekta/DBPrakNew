package db.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Company extends Organisation{

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "locatedIn", nullable = false)
	private Country locatedIn;
	
	@OneToMany(mappedBy = "company")
	private Set<CompanyMembership> memberships = new HashSet<CompanyMembership>();
	
	public Company() {}
	
	public Company(int id, String name, Country country) {
		this.id = id;
		this.name = name;
		this.locatedIn = country;
	}

	public Country getLocatedIn() {
		return locatedIn;
	}

	public void setLocatedIn(Country locatedIn) {
		this.locatedIn = locatedIn;
	}

	public Set<CompanyMembership> getMemberships() {
		return memberships;
	}

	public void setMemberships(Set<CompanyMembership> memberships) {
		this.memberships = memberships;
	}	
}
