package com.bank.sure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			
			super.configure(auth);
		}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		super.configure(http);
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
	
		super.configure(web);
	}

}
