package com.crud.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.springsecurity.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{

	UserRole findByName(String name);

}
