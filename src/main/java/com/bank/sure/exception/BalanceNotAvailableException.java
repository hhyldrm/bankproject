package com.bank.sure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class BalanceNotAvailableException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID= 1L;
	public BalanceNotAvailableException(String message) {
		super(message);
	}

}
