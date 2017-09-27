package db.model;

import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Message {
	@Id
	protected Long id;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Calendar creationDate;
	
	@Column(length = 50)
	protected String browserused;
	
	@Column(nullable = false, columnDefinition = "INET")
	protected String locationIP;
	
	@Column(columnDefinition = "TEXT")
	protected String content;
	
	protected int length;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "locatedIn", nullable = false)
	protected Country locatedIn;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "creator", nullable = false)
	protected Person creator;
	
	public Long getId() {
		return id;
	}
	public Calendar getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}
	public String getBrowserused() {
		return browserused;
	}
	public void setBrowserused(String browserused) {
		this.browserused = browserused;
	}
	public String getLocationIP() {
		return locationIP;
	}
	public void setLocationIP(String locationIP) {
		this.locationIP = locationIP;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public Country getLocatedIn() {
		return locatedIn;
	}
	public void setLocatedIn(Country locatedIn) {
		this.locatedIn = locatedIn;
	}
	public Person getCreator() {
		return creator;
	}
	public void setCreator(Person creator) {
		this.creator = creator;
	}
}
