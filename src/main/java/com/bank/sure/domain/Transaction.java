package com.bank.sure.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bank.sure.domain.enumeration.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name="tbl_transaction")
@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime date;
	
	@Column(length=200,nullable=false)
	private String description;
	
	private double amount;
	
	private BigDecimal availableBalance;
	
	private TransactionType type;
	
	
	@ManyToOne
	@JoinColumn (name="account_id")
	private Account account;


	public Transaction(LocalDateTime date, String description, double amount, BigDecimal availableBalance,
			TransactionType type, Account account) {
		this.date = date;
		this.description = description;
		this.amount = amount;
		this.availableBalance = availableBalance;
		this.type = type;
		this.account = account;
	}
}