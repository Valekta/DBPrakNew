package db.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "studyAt")
@IdClass(UniversityMembershipPK.class)
public class UniversityMembership {
	@Id 
	@ManyToOne(optional = false)
	@JoinColumn(name = "PID")
	private Person student;
	
	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "university")
	private University university;
	
	private int classYear;
	
	public UniversityMembership() {}
	
	public UniversityMembership(UniversityMembershipPK id, int classYear) {
		this.student = id.getStudent();
		this.university = id.getUniversity();
		this.classYear = classYear;
	}

	public int getClassYear() {
		return classYear;
	}

	public void setClassYear(int classYear) {
		this.classYear = classYear;
	}

	public Person getStudent() {
		return student;
	}

	public University getUniversity() {
		return university;
	}
}
