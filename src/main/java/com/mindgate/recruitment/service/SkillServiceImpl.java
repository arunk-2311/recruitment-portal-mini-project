package com.mindgate.recruitment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.recruitment.beans.Skill;
import com.mindgate.recruitment.exceptions.SkillNotFoundException;
import com.mindgate.recruitment.repository.SkillRepository;

@Service
public class SkillServiceImpl implements SkillService{
	@Autowired
	private SkillRepository skillRepository;
	
	@Override
	public List<Skill> findAllSkills() {
		return skillRepository.findAll();
	}
	
	@Override
	public Skill findSkillById(int id) throws SkillNotFoundException {
		Optional<Skill> optional = skillRepository.findById(id); 
		return optional.orElseThrow(() -> new SkillNotFoundException("Skill not found!"));
	}
	
}
