package com.timesheet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Timentry")
public class TimeEntry {
	/**
	 * @Entity Specifies that the class is an entity
	 * @Table is used for table creation
	 * @Column is use for column creation in a table
	 * @JsonFormat is used for specifying the input format in Json
	 *
	 */

	public TimeEntry() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "empid")
	private String empid;
	@Column(name = "timehours")
	private long timehours;
	@Column(name = "date") // column name
	@Type(type = "date") // Date type without timestamp
	@JsonFormat(pattern = "ddMMyyyy") // Date format to be entered in json
	private Date date;
	@Column(name = "projectid")
	private String projectid;

	public long getTimehours() {
		return timehours;
	}

	public void setTimehours(long timehours) {
		this.timehours = timehours;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

}
