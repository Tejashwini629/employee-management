package com.wecodee.EmployeeManagement.entity;

import java.util.Date;

public class EmployeeReqDto {
	private int id;
	private Date fromDate;
	private Date toDate;
	private String department;

	public EmployeeReqDto(int id, Date fromDate, Date toDate, String department) {
		super();
		this.id = id;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.department = department;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getFromDate() {
		return fromDate;
	}


	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}


	public Date getToDate() {
		return toDate;
	}


	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}

	
	
}
