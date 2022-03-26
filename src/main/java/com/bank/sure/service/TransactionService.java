package com.bank.sure.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.sure.controller.response.DashBoardInfoResponse;
import com.bank.sure.domain.Transaction;
import com.bank.sure.repository.TransactionRepository;

@Service
@Transactional
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public void saveTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
		
	}

	public DashBoardInfoResponse calculateCustomerStatment(Long accountId, LocalDate startDate, LocalDate endDate) {
		
		LocalDateTime sDate=startDate.atStartOfDay();
		LocalDateTime eDate=endDate.atTime(LocalTime.MAX);
		
		transactionRepository.getCustomerTransaction
	}
}
