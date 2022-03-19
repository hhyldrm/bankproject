package com.bank.sure.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.bank.sure.domain.User;
import com.bank.sure.exception.ResourceNotFoundException;
import com.bank.sure.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUserNameAndEnabledTrue(username).orElseThrow(()->new ResourceNotFoundException("User Not Found: "+username));
		
		return UserDetailsImpl.build(user);
	}

}
