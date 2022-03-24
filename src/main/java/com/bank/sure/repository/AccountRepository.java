package com.bank.sure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.sure.domain.Account;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	Optional<Account> findByAccountNumber(Long accountNumber);
}
