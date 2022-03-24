package com.bank.sure.controller.dto;

import java.util.HashSet;
import java.util.Set;

import com.bank.sure.domain.Role;
import com.bank.sure.domain.enumeration.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private Long id;
	

	private String firstName;
	

	private String lastName;
	
	
	private String ssn;


	private String userName;
	

	private String email;
	

	private String phoneNumber;
	

	private String address;
	

	private Boolean enabled;

	private String dateOfBirth;
	
	
	private Set<Role> roles;
	
	public Set<String> getRoles(){
		
		Set<String> roleStr=new HashSet<>();
		
		Role[] role=roles.toArray(new Role[roles.size()]);
		
		for(int i=0; i<roles.size(); i++) {
			if(role[i].getName().equals(UserRole.ROLE_ADMIN))
				roleStr.add("Admin");
			else 
				roleStr.add("Customer");
		}
		
		return roleStr;
	} 

	
	/*
	 * public User(String firstName, String lastName, String ssn, String username,
	 * String email, String password, String phoneNumber, String address, String
	 * dateOfBirth,Set<Role> roles){
	 * 
	 * this.firstName=firstName; this.lastName=lastName; this.ssn=ssn;
	 * this.userName=username; this.email=email; this.password=password;
	 * this.phoneNumber=phoneNumber; this.address=address;
	 * this.dateOfBirth=dateOfBirth; this.roles=roles; }
	 */
			
	
	
	

}