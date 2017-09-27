package db.model;

import java.io.Serializable;

public class UniversityMembershipPK implements Serializable {
	private Person student;
	private University university;
	
	public UniversityMembershipPK() {}
	
	public UniversityMembershipPK(Person student, University university) {
		this.student = student;
		this.university = university;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}
	
	@Override
	public boolean equals(Object o) {
		return false;
	}

	public Person getStudent() {
		return student;
	}

	public void setStudent(Person student) {
		this.student = student;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}
}
