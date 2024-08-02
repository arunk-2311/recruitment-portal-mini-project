package com.mindgate.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindgate.recruitment.beans.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer>{}
