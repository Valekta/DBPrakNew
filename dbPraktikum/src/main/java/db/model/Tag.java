package db.model;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Tag {
	@Id
	private int id;
	
	@Column(nullable = false, unique = true, length = 100)
	private String name;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
		name = "tagtype", 
		joinColumns = {@JoinColumn(name = "tag")}, 
		inverseJoinColumns = {@JoinColumn(name = "hasType")}
	)
	private Set<TagClass> types;
	
	public Tag() {}
	
	public Tag(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<TagClass> getTypes() {
		return types;
	}

	public void setTypes(Set<TagClass> types) {
		this.types = types;
	}	
}