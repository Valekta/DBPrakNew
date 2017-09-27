package db.model;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Post extends Message {
	@Column(length = 2)
	private String language;
	
	@Column(length = 200)
	private String imageFile;
	
	@ManyToOne
	@JoinColumn(name = "forum", nullable = false)
	private Forum forum;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
		name = "POSTTAG", 
		joinColumns = {@JoinColumn(name = "post")}, 
		inverseJoinColumns = {@JoinColumn(name = "tag")}
	)
	protected Set<Tag> tags = new HashSet<Tag>();
	
	public Post() {}
	
	public Post(Long id, Calendar creationDate, String locationIP, Person creator, Country locatedIn, Forum forum) {
		this.id = id;
		this.creationDate = creationDate;
		this.locationIP = locationIP;
		this.creator = creator;
		this.locatedIn = locatedIn;
		this.forum = forum;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	public void addTag(Tag tag) {
		tags.add(tag);
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}
}
