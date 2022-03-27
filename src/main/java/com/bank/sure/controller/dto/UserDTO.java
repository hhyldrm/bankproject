package com.bank.sure.controller.dto;

import java.util.HashSet;
import java.util.Set;

import com.bank.sure.domain.Role;
import com.bank.sure.domain.User;
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

	public Set<String> getRoles() {

		Set<String> roleStr = new HashSet<>();

		Role[] role = roles.toArray(new Role[roles.size()]);

		for (int i = 0; i < roles.size(); i++) {
			if (role[i].getName().equals(UserRole.ROLE_ADMIN))
				roleStr.add("Admin");
			else
				roleStr.add("Customer");
		}

		return roleStr;
	}

	public UserDTO(Long id, User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.ssn = user.getSsn();
		this.userName = user.getUserName();
		this.email = user.getEmail();
		this.phoneNumber = user.getPhoneNumber();
		this.address = user.getAddress();
		this.enabled = user.getEnabled();
		this.dateOfBirth = user.getDateOfBirth();
		this.roles = user.getRoles();
	}

}