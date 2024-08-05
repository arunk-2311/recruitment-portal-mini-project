package com.mindgate.recruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.recruitment.beans.Skill;
import com.mindgate.recruitment.exceptions.SkillNotFoundException;
import com.mindgate.recruitment.service.SkillService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/skill")
public class SkillController {

	@Autowired
	private SkillService skillService;

	@GetMapping("/")
	public ResponseEntity<Object> getAllSkills() {
		List<Skill> skillList = skillService.findAllSkills();
		return ResponseEntity.status(200).body(skillList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getSkillById(@PathVariable("id") int id) {
		try {
			Skill skill = skillService.findSkillById(id);
			return ResponseEntity.status(200).body(skill);
		} catch (SkillNotFoundException e) {
			return ResponseEntity.status(404).body(e.getMessage());
		}
	}

}
