package db.model;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Comment extends Message {
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
		name = "COMMENTTAG", 
		joinColumns = {@JoinColumn(name = "comment")}, 
		inverseJoinColumns = {@JoinColumn(name = "tag")}
	)
	private Set<Tag> tags = new HashSet<Tag>();
	
	@ManyToOne
	@JoinColumn(name = "replyOfPost")
	private Post replyOfPost;
	
	@ManyToOne
	@JoinColumn(name = "replyOfComment")
	private Comment replyOfComment;
	
	public Comment() {}
	
	public Comment(Long id, Calendar creationDate, String locationIP, Person creator, Country locatedIn) {
		this.id = id;
		this.creationDate = creationDate;
		this.locationIP = locationIP;
		this.creator = creator;
		this.locatedIn = locatedIn;
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

	public Post getReplyOfPost() {
		return replyOfPost;
	}

	public void setReplyOfPost(Post replyOfPost) {
		this.replyOfPost = replyOfPost;
	}

	public Comment getReplyOfComment() {
		return replyOfComment;
	}

	public void setReplyOfComment(Comment replyOfComment) {
		this.replyOfComment = replyOfComment;
	}
}
