package com.bank.sure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.sure.domain.Role;
import com.bank.sure.domain.enumeration.UserRole;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByName(UserRole name);
	
	
}