package com.bank.sure.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RecipientRequest {
	
    @NotBlank(message="Please provide not blank  name")
    @NotNull(message="Please provide your  name")
    @Size(min=1, max=15, message="Your name '${validatedValue}' must be between {min} and {max} chars long")
	private String name;
    
    @NotNull
    private Long accountNumber;
	
}