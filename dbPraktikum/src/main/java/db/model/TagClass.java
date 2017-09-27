package db.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class TagClass {
	@Id
	private int id;
	
	@Column(nullable = false, unique = true, length = 50)
	private String name;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
		name = "tagclasstype", 
		joinColumns = {@JoinColumn(name = "tagClass")}, 
		inverseJoinColumns = {@JoinColumn(name = "hasType")}
	)
	private Set<TagClass> types;
	
	public TagClass() {}
	
	public TagClass(int id, String name) {
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
