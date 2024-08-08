package com.mindgate.recruitment.beans;

import jakarta.persistence.*;

@Entity
@Table(name="candidate")
public class Candidate {
	
	@Id
	@Column(name = "candidate_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int candidateId;
	private String name;
	private String email;
	
	@Column(name = "phone_no")
	private String phoneNo;
	
	@Column(name = "role_id")
	private Integer roleId;
	
	@Column(name = "skill1_id")
	private Integer skill1Id;
	
	@Column(name = "skill2_id")
	private Integer skill2Id;
	
	@Column(name = "skill3_id")
	private Integer skill3Id;
	
	@Column(name = "selected_for_interview")
	private String selectedForInterview;
	
	@Column(name = "assessment_status")
	private String assessmentStatus;
	
	@Column(name = "confirmation_status")
	private String confirmationStatus;

//	@Column(name = "request_id")
//	private int requestId;
	
	@Column(name = "interview_meet_link")
	private String interviewMeetLink;
	
	@Column(name="final_selection")
	private String finalSelection;

	public String getFinalSelection() {
		return finalSelection;
	}

	public void setFinelSelection(String finalSelection) {
		this.finalSelection = finalSelection;
	}

	public int getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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

	public String getSelectedForInterview() {
		return selectedForInterview;
	}

	public void setSelectedForInterview(String selectedForInterview) {
		this.selectedForInterview = selectedForInterview;
	}

	public String getAssessmentStatus() {
		return assessmentStatus;
	}

	public void setAssessmentStatus(String assessmentStatus) {
		this.assessmentStatus = assessmentStatus;
	}

	public String getConfirmationStatus() {
		return confirmationStatus;
	}

	public void setConfirmationStatus(String confirmationStatus) {
		this.confirmationStatus = confirmationStatus;
	}

//	public int getRequestId() {
//		return requestId;
//	}
//
//	public void setRequestId(int requestId) {
//		this.requestId = requestId;
//	}

	public String getInterviewMeetLink() {
		return interviewMeetLink;
	}

	public void setInterviewMeetLink(String interviewMeetLink) {
		this.interviewMeetLink = interviewMeetLink;
	}
	
}
