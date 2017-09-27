package db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "workAt")
@IdClass(CompanyMembershipPK.class)
public class CompanyMembership {
	@Id 
	@ManyToOne(optional = false)
	@JoinColumn(name = "PID")
	private Person employee;
	
	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "company")
	private Company company;
	
	public CompanyMembership() {}
	
	public CompanyMembership(CompanyMembershipPK id, int workFrom) {
		this.employee = id.getEmployee();
		this.company = id.getCompany();
		this.workFrom = workFrom;
	}
	
	private int workFrom;

	public int getWorkFrom() {
		return workFrom;
	}

	public void setWorkFrom(int workFrom) {
		this.workFrom = workFrom;
	}

	public Person getEmployee() {
		return employee;
	}

	public Company getCompany() {
		return company;
	}
}
