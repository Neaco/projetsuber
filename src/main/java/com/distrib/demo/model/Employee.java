package com.distrib.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the employees database table.
 * 
 */
@Entity
@Table(name="employees")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	private long employeeId;
	private BigDecimal commissionPct;
	private String email;
	private String firstName;
	private String lastName;
	private BigDecimal managerId;
	private String phoneNumber;
	private BigDecimal salary;
	@JsonIgnoreProperties({"employees","jobHistories"})
	private Department department;
	@JsonIgnoreProperties("employees")
	private Job job;
	@JsonIgnoreProperties("employee")
	private Set<JobHistory> jobHistories;

	public Employee() {
	}


	@Id
	@Column(name="EMPLOYEE_ID", unique=true, nullable=false, precision=10)
	public long getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}


	@Column(name="COMMISSION_PCT", precision=10, scale=2)
	public BigDecimal getCommissionPct() {
		return this.commissionPct;
	}

	public void setCommissionPct(BigDecimal commissionPct) {
		this.commissionPct = commissionPct;
	}


	@Column(name="EMAIL", nullable=false, length=25)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Column(name="FIRST_NAME", length=20)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	@Column(name="LAST_NAME", nullable=false, length=25)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	@Column(name="MANAGER_ID", precision=10)
	public BigDecimal getManagerId() {
		return this.managerId;
	}

	public void setManagerId(BigDecimal managerId) {
		this.managerId = managerId;
	}


	@Column(name="PHONE_NUMBER", length=20)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	@Column(name="SALARY", precision=10, scale=2)
	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}


	//bi-directional many-to-one association to Department
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DEPARTMENT_ID")
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}


	//bi-directional many-to-one association to Job
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="JOB_ID", nullable=false)
	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}


	//bi-directional many-to-one association to JobHistory
	@OneToMany(mappedBy="employee")
	public Set<JobHistory> getJobHistories() {
		return this.jobHistories;
	}

	public void setJobHistories(Set<JobHistory> jobHistories) {
		this.jobHistories = jobHistories;
	}

	public JobHistory addJobHistory(JobHistory jobHistory) {
		getJobHistories().add(jobHistory);
		jobHistory.setEmployee(this);

		return jobHistory;
	}

	public JobHistory removeJobHistory(JobHistory jobHistory) {
		getJobHistories().remove(jobHistory);
		jobHistory.setEmployee(null);

		return jobHistory;
	}

}