package com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="job_s180250")
public class Job {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
@Column(nullable=false)
private String jobtitle;
private String jobdescription;
@Column(nullable=false)
private String skillsRequired;
private String location;
private String yrsofExp;
private String companyName;
private String salary;
private Date postedon;
public Date getPostedon() {
	return postedon;
}
public void setPostedon(Date postedon) {
	this.postedon = postedon;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getJobtitle() {
	return jobtitle;
}
public void setJobtitle(String jobtitle) {
	this.jobtitle = jobtitle;
}
public String getJobdescription() {
	return jobdescription;
}
public void setJobdescription(String jobdescription) {
	this.jobdescription = jobdescription;
}
public String getSkillsRequired() {
	return skillsRequired;
}
public void setSkillsRequired(String skillsRequired) {
	this.skillsRequired = skillsRequired;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public String getYrsofExp() {
	return yrsofExp;
}
public void setYrsofExp(String yrsofExp) {
	this.yrsofExp = yrsofExp;
}
public String getCompanyName() {
	return companyName;
}
public void setCompanyName(String companyName) {
	this.companyName = companyName;
}
public String getSalary() {
	return salary;
}
public void setSalary(String salary) {
	this.salary = salary;
}


}
