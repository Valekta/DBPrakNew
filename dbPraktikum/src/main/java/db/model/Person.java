package db.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Person {
	@Id
	@Column(name = "pid")
	private Long pid;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar creationDate;
	
	@Column(nullable = false, length = 50)
	private String firstName;
	
	@Column(nullable = false, length = 50)
	private String lastName;
	
	@Column(length = 30)
	private String gender;
	
	@Column(columnDefinition = "BIRTHDATE")
	private Calendar birthday;
	
	@Column(length = 50)
	private String browserUsed;
	
	@Column(nullable = false, columnDefinition = "INET")
	private String locationIP;

	@ElementCollection
	@CollectionTable(name = "EMAIL", joinColumns = @JoinColumn(name = "owner"))
	@Column(name = "address", columnDefinition = "MAILADDRESS PRIMARY KEY")
	private Set<String> emails = new HashSet<String>();
	
	@ElementCollection
	@CollectionTable(name = "LANGUAGE", joinColumns = @JoinColumn(name = "speaker"))
	@Column(name = "ISOcode", nullable = false, length = 2)
	private Set<String> languages = new HashSet<String>();
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "locatedIn", nullable = false)
	private City locatedIn;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
		name = "interest", 
		joinColumns = {@JoinColumn(name = "pid")}, 
		inverseJoinColumns = {@JoinColumn(name = "tag")}
	)
	private Set<Tag> interests = new HashSet<Tag>();
	
	@ElementCollection
	@CollectionTable(name = "COMMENTLIKES", joinColumns = @JoinColumn(name = "PID"))
	@Column(name = "creationDate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@MapKeyJoinColumn(name = "comment")
	private Map<Comment, Calendar> commentLikes = new HashMap<Comment, Calendar>();
	
	@ElementCollection
	@CollectionTable(name = "POSTLIKES", joinColumns = @JoinColumn(name = "PID"))
	@Column(name = "creationDate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@MapKeyJoinColumn(name = "post")
	private Map<Post, Calendar> postLikes = new HashMap<Post, Calendar>();

	@OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST)
	private Set<UniversityMembership> uniMemberships = new HashSet<UniversityMembership>();
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.PERSIST)
	private Set<CompanyMembership> companyMemberships = new HashSet<CompanyMembership>();
	
	@ElementCollection
	@CollectionTable(name = "KNOWS", joinColumns = @JoinColumn(name = "PID"))
	@Column(name = "creationDate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@MapKeyJoinColumn(name = "knows")
	private Map<Person, Calendar> friends = new HashMap<Person, Calendar>();
	
	public Person() {}
	
	public Person(Long id, Calendar creationDate, String firstName, String lastName, String locationIP, City locatedIn) {
		this.pid = id;
		this.creationDate = creationDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.locationIP = locationIP;
		this.locatedIn = locatedIn;
	}

	public long getPid() {
		return pid;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Calendar getBirthday() {
		return birthday;
	}

	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	public String getBrowserUsed() {
		return browserUsed;
	}

	public void setBrowserUsed(String browserUsed) {
		this.browserUsed = browserUsed;
	}

	public String getLocationIP() {
		return locationIP;
	}

	public void setLocationIP(String locationIP) {
		this.locationIP = locationIP;
	}

	public City getLocatedIn() {
		return locatedIn;
	}

	public void setLocatedIn(City loactedIn) {
		this.locatedIn = loactedIn;
	}

	public Set<String> getEmails() {
		return emails;
	}

	public void setEmails(Set<String> emails) {
		this.emails = emails;
	}
	
	public void addEmail(String address) {
		this.emails.add(address);
	}

	public Set<String> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<String> languages) {
		this.languages = languages;
	}
	
	public void addLanguage(String language) {
		this.languages.add(language);
	}

	public Set<Tag> getInterests() {
		return interests;
	}

	public void setInterests(Set<Tag> interests) {
		this.interests = interests;
	}
	
	public void addInterest(Tag interest) {
		interests.add(interest);
	}

	public Map<Comment, Calendar> getCommentLikes() {
		return commentLikes;
	}

	public void setCommentLikes(Map<Comment, Calendar> commentLikes) {
		this.commentLikes = commentLikes;
	}
	
	public void addCommentLike(Comment comment) {
		commentLikes.put(comment, new GregorianCalendar());
	}

	public Map<Post, Calendar> getPostLikes() {
		return postLikes;
	}

	public void setPostLikes(Map<Post, Calendar> postLikes) {
		this.postLikes = postLikes;
	}
	
	public void addPostLike(Post post) {
		postLikes.put(post, new GregorianCalendar());
	}

	public Set<UniversityMembership> getUniMemberships() {
		return uniMemberships;
	}

	public void setUniMemberships(Set<UniversityMembership> uniMemberships) {
		this.uniMemberships = uniMemberships;
	}
	
	public void addUniMembership(UniversityMembership ms) {
		uniMemberships.add(ms);
	}

	public Set<CompanyMembership> getCompanyMemberships() {
		return companyMemberships;
	}

	public void setCompanyMemberships(Set<CompanyMembership> companyMemberships) {
		this.companyMemberships = companyMemberships;
	}
	
	public void addCompanyMembership(CompanyMembership ms) {
		companyMemberships.add(ms);
	}

	public Map<Person, Calendar> getFriends() {
		return friends;
	}

	public void setFriends(Map<Person, Calendar> friends) {
		this.friends = friends;
	}
	
	public void addFriend(Person friend) {
		if(friends.containsKey(friend) != true) {
			Calendar date = new GregorianCalendar();
			friends.put(friend, date);
		}
	}
}
