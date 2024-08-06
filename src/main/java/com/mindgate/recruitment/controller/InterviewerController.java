package com.mindgate.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mindgate.recruitment.beans.Candidate;
import com.mindgate.recruitment.service.CandidateService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/candidates")
public class InterviewerController {

    @Autowired
    private CandidateService candidateService;

    @PutMapping("/{candidateId}/assessment-status")
    public ResponseEntity<String> updateAssessmentStatus(@PathVariable int candidateId,
                                                         @RequestParam String assessmentStatus) {

        // Validate the assessmentStatus input
        if (!"y".equalsIgnoreCase(assessmentStatus) && !"n".equalsIgnoreCase(assessmentStatus)) {
            return ResponseEntity.badRequest().body("Invalid assessment status. It must be 'y' or 'n'.");
        }

        // Fetch the candidate from the repository
        return candidateService.getCandidateById(candidateId).map(candidate -> {
            candidate.setAssessmentStatus(assessmentStatus);
            candidateService.saveCandidate(candidate);
            return ResponseEntity.ok("Assessment status updated successfully.");
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Candidate>> getCompletedCandidates() {
        List<Candidate> allCandidates = candidateService.getAllCandidate();
        List<Candidate> completedCandidates = allCandidates.stream()
                .filter(candidate -> candidate.getAssessmentStatus() != null)
                .collect(Collectors.toList());
        return ResponseEntity.ok(completedCandidates);
    }
    
    @GetMapping("/not-completed")
    public ResponseEntity<List<Candidate>> getNotCompletedCandidates() {
        List<Candidate> allCandidates = candidateService.getAllCandidate();
        List<Candidate> notCompletedCandidates = allCandidates.stream()
                .filter(candidate -> candidate.getAssessmentStatus() == null)
                .collect(Collectors.toList());
        return ResponseEntity.ok(notCompletedCandidates);
    }
}

