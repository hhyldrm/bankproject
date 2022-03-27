package com.bank.sure.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.sure.controller.dto.UserDTO;
import com.bank.sure.controller.request.UserUpdateRequest;
import com.bank.sure.controller.response.Response;
import com.bank.sure.controller.response.UserInfoResponse;
import com.bank.sure.domain.User;
import com.bank.sure.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	private UserDTO convertToDTO(User user) {
		UserDTO userDTO=modelMapper.map(user, UserDTO.class);
		return userDTO;
	}
	
	@GetMapping("/userInfo")

	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	public ResponseEntity<UserInfoResponse> getUserInfo(){
		User user = userService.findOneWithAuthoritiesByUserName();
		UserDTO userDTO=convertToDTO(user);
		UserInfoResponse response=new UserInfoResponse(userDTO);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
		
		
		try {
			User user=userService.findById(id);
		UserDTO userDTO=convertToDTO(user);
		return ResponseEntity.ok(userDTO);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	
		return ResponseEntity.ok(new UserDTO());
		
	}
	
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Page<UserDTO>> getAllUsers(Pageable pageable){
		Page<UserDTO> userPage = userService.getUsers(pageable);
		return new ResponseEntity<>(userPage,HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Response> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest updateRequest){
		User user=userService.findById(id);
		userService.updateUser(user.getId(), updateRequest);	
		
		Response response=new Response();
		response.setMessage("User successfully updated");
		response.setSuccess(true);
		return ResponseEntity.ok(response);
	}


}	