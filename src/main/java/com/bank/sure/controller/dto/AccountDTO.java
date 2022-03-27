package com.bank.sure.controller.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AccountDTO {
	
	private Long accountNumber;
	private BigDecimal accountBalance;
	

}