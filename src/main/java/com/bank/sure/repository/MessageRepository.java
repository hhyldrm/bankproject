package com.bank.sure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.sure.domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
