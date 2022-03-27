package com.bank.sure.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.sure.controller.dto.AccountDTO;
import com.bank.sure.controller.dto.AdminTransactionDTO;
import com.bank.sure.controller.dto.TransactionDTO;
import com.bank.sure.controller.response.BankStatementResponse;
import com.bank.sure.controller.response.DashBoardInfoReponse;
import com.bank.sure.domain.Account;
import com.bank.sure.domain.Transaction;
import com.bank.sure.domain.enumeration.TransactionType;
import com.bank.sure.repository.AccountRepository;
import com.bank.sure.repository.TransactionRepository;

@Service
@Transactional
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	public void saveTransaction(Transaction transaction) {
		transactionRepository.save(transaction);
	}

	//startdate may be 03.26.2022 00.00.00 , enddatetime must be 03.27.2022.23.59.59
	public DashBoardInfoReponse calculateCustomerStatement(Long accountId, LocalDate startDate, LocalDate endDate) {
		
		LocalDateTime sDate=startDate.atStartOfDay();
		LocalDateTime eDate=endDate.atTime(LocalTime.MAX);
		
		List<Transaction> transactions = transactionRepository.getCustomerTransactions(accountId, sDate, eDate);
		
		List<Transaction> deposit = transactions.stream().filter(t->t.getType()==TransactionType.DEPOSIT).collect(Collectors.toList());
		List<Transaction> withdraw = transactions.stream().filter(t->t.getType()==TransactionType.WITHDRAW).collect(Collectors.toList());
		List<Transaction> transfer = transactions.stream().filter(t->t.getType()==TransactionType.TRANSFER).collect(Collectors.toList());
		
		
		double sumDeposit =deposit.stream().mapToDouble(t->t.getAmount()).sum();
		double sumwithDraw =withdraw.stream().mapToDouble(t->t.getAmount()).sum();
		double sumTransfer =transfer.stream().mapToDouble(t->t.getAmount()).sum();
		
		List<TransactionDTO> transactionsDTO= transactions.stream().map(this::converttoDTO).collect(Collectors.toList());
		
		Account account=accountRepository.getById(accountId);
		
		//AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);
		
		AccountDTO accountDTO=new AccountDTO();
		accountDTO.setAccountBalance(account.getAccountBalance());
		accountDTO.setAccountNumber(account.getAccountNumber());
		
		DashBoardInfoReponse dbiResponse=new DashBoardInfoReponse(transactionsDTO, sumDeposit, sumwithDraw, sumTransfer, accountDTO);
		
		return dbiResponse;
	}
	
	
	public BankStatementResponse calculateBankStatement(LocalDate startDate, LocalDate endDate) {
		LocalDateTime sDate=startDate.atStartOfDay();
		LocalDateTime eDate=endDate.atTime(LocalTime.MAX);
		
		List<Transaction> transactions = transactionRepository.getBankStatement(sDate, eDate);
		List<AdminTransactionDTO> transactionsDTO= transactions.stream().map(this::converttoAdminDTO).collect(Collectors.toList());
		
		
		Double totalBalance = accountRepository.getTotalBalance();
		
		BankStatementResponse response=new BankStatementResponse();
		response.setTotalBalance(totalBalance.doubleValue());
		response.setList(transactionsDTO);
		
		return response;
	
	}
	
	
	
	public TransactionDTO converttoDTO(Transaction transaction) {
		return modelMapper.map(transaction, TransactionDTO.class);
	}
	
	
	public AdminTransactionDTO converttoAdminDTO(Transaction transaction) {
		return modelMapper.map(transaction, AdminTransactionDTO.class);
	}
	
}