package com.bank.sure.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.sure.controller.dto.UserDTO;
import com.bank.sure.domain.User;
import com.bank.sure.exception.ResourceNotFoundException;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUserNameAndEnabledTrue(String username) throws ResourceNotFoundException;
	
	@Query("SELECT new com.bank.sure.controller.dto.UserDTO(u.id,u) From User u")
	Page<UserDTO> findUsersPage(Pageable pageable); 
	
	Boolean existsByUserName(String userName);

	Boolean existsByEmail(String email);
	
	Boolean existsBySsn(String ssn);
	
	
	Optional<User> findOneWithAuthoritiesByUserName(String userName);
	
	
	

}