package com.bank.sure.exception.message;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
	
	private Date date;
	private String message;
	private String description;

}
