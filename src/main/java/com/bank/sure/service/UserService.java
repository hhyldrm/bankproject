package com.bank.sure.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.sure.controller.request.RegisterRequest;
import com.bank.sure.domain.Role;
import com.bank.sure.domain.User;
import com.bank.sure.domain.enumeration.UserRole;
import com.bank.sure.exception.ConflictException;
import com.bank.sure.exception.ResourceNotFoundException;
import com.bank.sure.exception.message.ExceptionMessage;
import com.bank.sure.repository.RoleRepository;
import com.bank.sure.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public void registerUser(RegisterRequest registerRequest) {	
		if(userRepository.existsByUserName(registerRequest.getUserName())) {
			throw new ConflictException(String.format(ExceptionMessage.USERNAME_ALREADY_EXIST_MESSAGE, registerRequest.getUserName()));
		}
		
		if(userRepository.existsByEmail(registerRequest.getEmail())) {
			throw new ConflictException(String.format(ExceptionMessage.EMAIL_ALREADY_EXIST_MESSAGE, registerRequest.getEmail()));
		}
		
		
		if(userRepository.existsBySsn(registerRequest.getSsn())) {
			throw new ConflictException(String.format(ExceptionMessage.SSN_ALREADY_EXIST_MESSAGE, registerRequest.getSsn()));
		}
		
		Set<Role> userRoles=new HashSet<>();
		
		//We add ROLE_CUSTOMER for every registered user by default
		Role role=roleRepository.findByName(UserRole.ROLE_CUSTOMER).
				orElseThrow(()->new ResourceNotFoundException(String.format(ExceptionMessage.ROLE_NOT_EXIST_MESSAGE, 
						UserRole.ROLE_CUSTOMER.name())));
		
		userRoles.add(role);
		
		
		User user=new User(
				registerRequest.getFirstName(),
				registerRequest.getLastName(),
				registerRequest.getSsn(),
				registerRequest.getUserName(),
				registerRequest.getEmail(),
				passwordEncoder.encode(registerRequest.getPassword()),
				registerRequest.getPhoneNumber(),
				registerRequest.getAddress(),
				registerRequest.getDateOfBirth(),
				userRoles
				);
		
		userRepository.save(user);
	}
	
	
	

}