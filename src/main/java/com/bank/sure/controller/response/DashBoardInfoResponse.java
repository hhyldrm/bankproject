package com.bank.sure.controller.response;


import java.util.List;

import com.bank.sure.controller.dto.AccountDTO;
import com.bank.sure.controller.dto.TransactionDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashBoardInfoResponse {
	List<TransactionDTO> list;
	double totalDeposit;
	double totalWithDraw;
	double totalTransfer;
	
	AccountDTO accountDTO;

}
