package com.bank.sure.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.sure.domain.Transaction;

import antlr.collections.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	@Query()
	List<Transaction>getCustomerTransactions(@Param("id") Long accountId, @Param(value="sDate") LocalDateTime starDate,
			@Param(value="eDate") LocalDateTime endDate);

}
