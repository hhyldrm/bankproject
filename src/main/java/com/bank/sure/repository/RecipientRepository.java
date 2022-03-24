package com.bank.sure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.sure.domain.Recipient;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient, Long> {

}