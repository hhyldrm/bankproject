package com.bank.sure.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.sure.controller.dto.LoginDTO;
import com.bank.sure.controller.request.RegisterRequest;
import com.bank.sure.controller.response.LoginResponse;
import com.bank.sure.controller.response.Response;
import com.bank.sure.security.JwtUtils;
import com.bank.sure.service.UserService;

@RestController
@RequestMapping
public class UserJWTController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/register")
	public ResponseEntity<Response> registerUser(@Valid @RequestBody RegisterRequest registerRequest  ){
		userService.registerUser(registerRequest);
		Response response =new Response();
		response.setMessage("User Registered Successfully");
		response.setSuccess(true);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
		
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginDTO loginDTO){
		Authentication authentication = authenticationManager.authenticate
				(new UsernamePasswordAuthenticationToken(loginDTO.getUserName(),loginDTO.getPassword()));
		//SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtUtils.generateToken(authentication);
		LoginResponse response=new LoginResponse(token);
		return ResponseEntity.ok(response);
	}
	
	
	
}