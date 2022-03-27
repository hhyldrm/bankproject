package com.bank.sure.controller.response;

import java.util.List;

import com.bank.sure.controller.dto.AdminTransactionDTO;

import lombok.Data;

@Data
public class BankStatementResponse {
	
     private List<AdminTransactionDTO> list;
     private double totalBalance;
	

}