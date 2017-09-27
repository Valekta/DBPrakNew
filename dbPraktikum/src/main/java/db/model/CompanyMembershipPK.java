package db.model;

import java.io.Serializable;

public class CompanyMembershipPK implements Serializable {
	private Person employee;
	private Company company;
	
	public CompanyMembershipPK() {}
	
	public CompanyMembershipPK(Person employee, Company company) {
		this.employee = employee;
		this.company = company;
	}
	
	@Override
	public int hashCode() {
		return 0;
	}
	
	@Override
	public boolean equals(Object o) {
		return false;
	}

	public Person getEmployee() {
		return employee;
	}

	public void setEmployee(Person employee) {
		this.employee = employee;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
