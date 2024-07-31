package com.mindgate.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindgate.recruitment.beans.Role;



public interface RoleRepository extends JpaRepository<Role, Integer> {
}