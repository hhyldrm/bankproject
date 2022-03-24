package com.bank.sure.controller.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdateRequest {
	
	   @NotBlank(message="Please provide not blank first name")
	    @NotNull(message="Please provide your first name")
	    @Size(min=1, max=15, message="Your first name '${validatedValue}' must be between {min} and {max} chars long")
		private String firstName;
		

	    @NotBlank(message="Please provide not blank last name")
	    @NotNull(message="Please provide your last name")
	    @Size(min=1, max=15, message="Your last name '${validatedValue}' must be between {min} and {max} chars long")
		private String lastName;
		
		
	    @Pattern(regexp = "^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$")
		private String ssn;


	    @Email(message="Please provide a valid email")
	    @Size(min=5, max=100, message="Your email'${validatedValue}' must be between {min} and {max} chars long")
		private String email;
		
		
	    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
	    	     message = "Please provide valid phone number")
		private String phoneNumber;
		
	    @NotBlank(message="Please provide not blank address")
	    @NotNull(message="Please provide your address")
	    @Size(min=5, max=200, message="Your address'${validatedValue}' must be between {min} and {max} chars long")
		private String address;
		
	    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
		private String dateOfBirth;
		
		
		private Set<String> roles;
		
		
		private Boolean enabled;

}