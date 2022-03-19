package com.bank.sure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.sure.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
