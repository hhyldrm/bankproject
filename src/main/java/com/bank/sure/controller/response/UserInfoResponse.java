package com.bank.sure.controller.response;

import com.bank.sure.controller.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoResponse {
 private UserDTO user;
}