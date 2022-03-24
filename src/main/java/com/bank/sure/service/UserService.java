package com.bank.sure.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.sure.controller.request.RegisterRequest;
import com.bank.sure.controller.request.UserUpdateRequest;
import com.bank.sure.domain.Role;
import com.bank.sure.domain.User;
import com.bank.sure.domain.enumeration.UserRole;
import com.bank.sure.exception.ConflictException;
import com.bank.sure.exception.ResourceNotFoundException;
import com.bank.sure.exception.message.ExceptionMessage;
import com.bank.sure.repository.RoleRepository;
import com.bank.sure.repository.UserRepository;
import com.bank.sure.security.SecurityUtils;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AccountService accountService;
	
	
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
		
		User userSaved = userRepository.findById(user.getId()).
			orElseThrow(()->new ResourceNotFoundException(String.format(ExceptionMessage.USER_NOT_FOUND_MESSAGE, user.getId())));
		
		accountService.createAccount(userSaved);
	}
	
	public User findOneWithAuthoritiesByUserName() {
		String currentUserLogin=SecurityUtils.getCurrentUserLogin().
			orElseThrow(()->new ResourceNotFoundException(ExceptionMessage.CURRENTUSER_NOT_FOUND_MESSAGE));
		
		User user=userRepository.findOneWithAuthoritiesByUserName(currentUserLogin).
		orElseThrow(()->new ResourceNotFoundException(String.format(ExceptionMessage.USER_NOT_FOUND_MESSAGE,currentUserLogin)));
		
		return user;
	}
	
	
	public User findById(Long id) {
		return userRepository.findById(id)
			.orElseThrow(()-> new ResourceNotFoundException(String.format(ExceptionMessage.USERID_NOT_FOUND_MESSAGE, id)));
	}
	
	public void updateUser(Long id, UserUpdateRequest request) {
		boolean emailExist=userRepository.existsByEmail(request.getEmail());
		User foundUser=findById(id);
		
		if(emailExist && !foundUser.getEmail().equals(request.getEmail())) {
			throw new ConflictException(String.format(ExceptionMessage.EMAIL_ALREADY_EXIST_MESSAGE, request.getEmail()));
		}
		
		Boolean ssnExist = userRepository.existsBySsn(request.getSsn());
		
		if(ssnExist&& !foundUser.getSsn().equals(request.getSsn())) {
			throw new ConflictException(String.format(ExceptionMessage.SSN_ALREADY_EXIST_MESSAGE, request.getSsn()));
		}
		
		
		foundUser.setFirstName(request.getFirstName());
		foundUser.setLastName(request.getLastName());
		foundUser.setEmail(request.getEmail());
		foundUser.setDateOfBirth(request.getDateOfBirth());
		foundUser.setPhoneNumber(request.getPhoneNumber());
		foundUser.setSsn(request.getSsn());
		foundUser.setAddress(request.getAddress());
		foundUser.setEnabled(request.getEnabled());
		
		Set<Role> roles=addRoles(request.getRoles());
		foundUser.setRoles(roles);
		
		userRepository.save(foundUser);
		
	}
	
	//it is used to convert all string roles into the Role type
	private Set<Role> addRoles(Set<String> userRoles){
		Set<Role> roles=new HashSet<>();
		
		if(userRoles==null) {
			Role role=roleRepository.findByName(UserRole.ROLE_CUSTOMER)
						.orElseThrow(()->new ResourceNotFoundException
								(String.format(ExceptionMessage.ROLE_NOT_EXIST_MESSAGE,UserRole.ROLE_CUSTOMER.name())));
						
			roles.add(role);			
		}else {
			
			userRoles.forEach(role->{
				switch (role) {
				case "Admin":
					Role adminRole=roleRepository.findByName(UserRole.ROLE_ADMIN)
					.orElseThrow(()->new ResourceNotFoundException
							(String.format(ExceptionMessage.ROLE_NOT_EXIST_MESSAGE,UserRole.ROLE_ADMIN.name())));
					
					roles.add(adminRole);
					
					break;

				default:
					Role customerRole=roleRepository.findByName(UserRole.ROLE_CUSTOMER)
					.orElseThrow(()->new ResourceNotFoundException
							(String.format(ExceptionMessage.ROLE_NOT_EXIST_MESSAGE,UserRole.ROLE_CUSTOMER.name())));
					
					roles.add(customerRole);
				}
				
			});
			
		}
		return roles;
		
	}
	
	
	

}