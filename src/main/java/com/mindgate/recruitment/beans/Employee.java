package com.mindgate.recruitment.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
	@Id
	@Column(name = "emp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empId;
	private String name;
	
	@Column(name = "role_id")
	private Integer roleId;
	
	@Column(name = "phone_no")
	private String phoneNo;
	
	private String email;
	private String status;

	// Skill_ids can be null, but better to have atleast one skill
	@Column(name = "skill1_id")
	private Integer skill1Id;
	
	@Column(name = "skill2_id")
	private Integer skill2Id;
	
	@Column(name = "skill3_id")
	private Integer skill3Id;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
}
