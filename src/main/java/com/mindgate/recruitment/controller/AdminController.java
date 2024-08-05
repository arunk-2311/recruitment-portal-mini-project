package com.mindgate.recruitment.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mindgate.recruitment.beans.*;
import com.mindgate.recruitment.service.*;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(path="/api/admin")
public class AdminController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CandidateService candidateServiceImpl;
	
	@PostMapping(path="/create",consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createProfile(@RequestBody Employee employee){
		Employee ad = employeeService.create(employee);
		return ResponseEntity.status(201).body(ad);
	}
	
	@GetMapping(path = "/getAllCandidates")
	public ResponseEntity<List<Candidate>> getAllCandidates(){
		List<Candidate> candidates = candidateServiceImpl.getAllCandidate();
		List<Candidate> selectedCandidates = candidates.stream()
					.filter(candidate -> "y".equals(candidate.getConfirmationStatus()))
		            .collect(Collectors.toList());
		return ResponseEntity.status(200).body(selectedCandidates);
	}
	
	@DeleteMapping("/delete/{candidateId}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable int candidateId) {
        candidateServiceImpl.deleteCandidate(candidateId);
        return ResponseEntity.noContent().build();
    }
	
//	@PostMapping("/convert/{id}")
//    public ResponseEntity<Employee> convertCandidateToEmployee(@PathVariable int candidateId) {
//        return candidateService.getCandidateById(candidateId)
//            .map(candidate -> {
//                Employee employee = new Employee();
//                employee.setName(candidate.getName());
//                employee.setRoleId(candidate.getRoleId());
//                employee.setPhoneNo(candidate.getPhoneNo());
//                employee.setEmail(candidate.getEmail());                
//                employee.setSkill1Id(candidate.getSkill1Id());
//                employee.setSkill2Id(candidate.getSkill2Id());
//                employee.setSkill3Id(candidate.getSkill3Id());
//                Employee savedEmployee = employeeService.create(employee);
//                candidateService.deleteCandidate(candidateId);
//                return ResponseEntity.ok(savedEmployee);
//            })
//            .orElse(ResponseEntity.notFound().build());
//    }
	
}
