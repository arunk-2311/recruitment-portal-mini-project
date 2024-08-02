package com.mindgate.recruitment.service;

import java.util.List;

import com.mindgate.recruitment.beans.Skill;
import com.mindgate.recruitment.exceptions.SkillNotFoundException;

public interface SkillService {
	Skill findSkillById(int id) throws SkillNotFoundException;
	List<Skill> findAllSkills(); 
}
