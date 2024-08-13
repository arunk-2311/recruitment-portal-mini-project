package com.mindgate.recruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mindgate.recruitment.beans.Candidate;
import com.mindgate.recruitment.exceptions.CandidateNotFoundException;
import com.mindgate.recruitment.service.CandidateService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/candidates")
public class CandidateController {
	@Autowired
	public CandidateService candidateService;

	@GetMapping(path = "/")
	public ResponseEntity<List<Candidate>> fetchCandidates() {
		List<Candidate> list = candidateService.getAllCandidate();
		return ResponseEntity.status(200).body(list);
	}
		
	@GetMapping(path="/all/filter/name/{searchText}")
	public ResponseEntity<List<Candidate>> filterAllCandidatesBySearchText(@PathVariable("searchText") String searchText){
		return ResponseEntity.status(200).body(candidateService.filterAllCandidatesByName(searchText));
	}
	
	@GetMapping(path="/final/filter/skill/{skillId}")
	public ResponseEntity<List<Candidate>> filterInactiveEmployeesBySkillId(@PathVariable("skillId") int skillId) throws CandidateNotFoundException {
		return ResponseEntity.status(200).body(candidateService.filterFinalCandidatesSkillId(skillId));
	}
	
	@GetMapping(path="/final/filter/role/{roleId}")
	public ResponseEntity<List<Candidate>> filterInactiveEmployeesByRoleId(@PathVariable("roleId") int roleId) throws CandidateNotFoundException {
		return ResponseEntity.status(200).body(candidateService.filterFinalCandidatesByRoleId(roleId));
	}
	
	@GetMapping(path="/final/filter/{roleId}/{skillId}")
	public ResponseEntity<List<Candidate>> filterInactiveEmployeesByRoleIdAndSkillId(@PathVariable("roleId") int roleId, @PathVariable("skillId") int skillId) throws CandidateNotFoundException{
		return ResponseEntity.status(200).body(candidateService.filterFinalCandidatesByRoleIdAndSkillId(roleId, skillId));
	}
	
	@PutMapping(path="/meetLink/{id}")
	public void meetLink(@PathVariable("id") int id,@RequestBody Candidate candidate) {
		candidateService.meetLinkById(id, candidate.getInterviewMeetLink());
	}
	
	@PutMapping(path="/reset/{id}")
	public void meetLink(@PathVariable("id") int id) {
		candidateService.reset(id);
	}
}
