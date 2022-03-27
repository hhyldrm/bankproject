package com.bank.sure.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TransactionRequest {
	
	@NotNull
	private Double amount;
	
	@NotNull
	@NotBlank
	@Size(min=1,max=100,message="Please provide a comment")
	private String comment;

}