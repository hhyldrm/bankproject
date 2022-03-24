package com.bank.sure.controller.dto;

import lombok.Data;

@Data
public class RecipientDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private Long accountNumber;

}