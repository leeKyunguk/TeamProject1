package com.example.demo.dto;

import com.example.demo.dto.UserProfiles.Role;

import lombok.Data;

@Data
public class Users {

	private String usersId;
	private String password;
	private String email;
    private String Role;
	private String address;
}