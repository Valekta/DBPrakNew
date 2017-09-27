package db.model;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Forum {
	@Id
	private long id;

	@Column(length = 100)
	private String title;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar creationDate;

	@OneToOne
	@JoinColumn(name = "moderator", nullable = false)
	private Person moderator;
	
	@OneToMany(mappedBy = "forum", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Post> posts;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
		name = "forumtag", 
		joinColumns = {@JoinColumn(name = "forum")}, 
		inverseJoinColumns = {@JoinColumn(name = "tag")}
	)
	private Set<Tag> tags;
	
	@ElementCollection
	@CollectionTable(name = "FORUMMEMBER", joinColumns = @JoinColumn(name = "forum"))
	@Column(name = "joinDate")
	@Temporal(TemporalType.TIMESTAMP)
	@MapKeyJoinColumn(name = "PID")
	private Map<Person, Calendar> members = new HashMap<Person, Calendar>();

	public Forum() {}
	
	public Forum(long id, Calendar creationDate, Person moderator) {
		this.id = id;
		this.creationDate = creationDate;
		this.moderator = moderator;
	}
	
	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public Person getModerator() {
		return moderator;
	}

	public void setModerator(Person moderator) {
		this.moderator = moderator;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Map<Person, Calendar> getMembers() {
		return members;
	}

	public void setMembers(Map<Person, Calendar> members) {
		this.members = members;
	}
}
