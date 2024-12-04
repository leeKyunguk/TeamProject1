package com.example.demo.dto;

import lombok.Data;

@Data
public class Users {
   
    private String usersId;
    private String password;
    private String email;
    public enum Role {
        구직자,
        기업
    }
    private Role role;
    private String address;
}