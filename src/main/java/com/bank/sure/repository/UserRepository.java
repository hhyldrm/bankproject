package com.bank.sure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.sure.domain.User;
import com.bank.sure.exception.ResourceNotFoundException;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUserNameAndEnabledTrue(String username) throws ResourceNotFoundException;
	
	
	Boolean existsByUserName(String userName);

	Boolean existsByEmail(String email);
	
	Boolean existsBySsn(String ssn);

}