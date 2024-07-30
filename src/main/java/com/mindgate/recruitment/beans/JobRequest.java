package com.mindgate.recruitment.beans;



import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_request")
public class JobRequest {
	@Id
	@Column(name = "request_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int requestId;
	
	//The automatic CreatedDate generated is coming NULL currently
	@Column(name = "created_date")
	@CreatedDate
	private Date createdDate;
	
	//This is the role ID for Job Request
	@Column(name = "role_id")
	private int roleId;
	
	private String description;
	
	@Column(name = "skill1_id")
	private Integer skill1Id;
	
	@Column(name = "skill2_id")
	private Integer skill2Id;
	
	@Column(name = "skill3_id")
	private Integer skill3Id;
	
	@Column(name = "no_of_vacancies")
	private int vacancies;
	private int filled;
	private int pending;
	@Column(name = "tl_id")
	private int tlId;
	@Column(name = "job_req_lvl")
	private int jrLevel;
	
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getSkill1Id() {
		return skill1Id;
	}
	public void setSkill1Id(Integer skill1Id) {
		this.skill1Id = skill1Id;
	}
	public Integer getSkill2Id() {
		return skill2Id;
	}
	public void setSkill2Id(Integer skill2Id) {
		this.skill2Id = skill2Id;
	}
	public Integer getSkill3Id() {
		return skill3Id;
	}
	public void setSkill3Id(Integer skill3Id) {
		this.skill3Id = skill3Id;
	}
	public int getVacancies() {
		return vacancies;
	}
	public void setVacancies(int vacancies) {
		this.vacancies = vacancies;
	}
	public int getFilled() {
		return filled;
	}
	public void setFilled(int filled) {
		this.filled = filled;
	}
	public int getPending() {
		return pending;
	}
	public void setPending(int pending) {
		this.pending = pending;
	}
	public int getTlId() {
		return tlId;
	}
	public void setTlId(int tlId) {
		this.tlId = tlId;
	}
	public int getJrLevel() {
		return jrLevel;
	}
	public void setJrLevel(int jrLevel) {
		this.jrLevel = jrLevel;
	}
	

	
}