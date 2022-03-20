package com.bank.sure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.sure.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
