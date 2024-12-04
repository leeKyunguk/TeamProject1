package com.example.demo.dto;

import lombok.Data;

@Data
public class UserProfiles {
   
    private String usersId;
    private String password;
    private String email;
    public enum Role {
        구직자
    }
    private Role role;
    private String address;
    
    private int userNo;
    private String userName;
    private String nickname;
    private String resiNumber;
    
    public enum Gender {
        M,
        F
    }
    private Gender gender;
    private String techStack;
    private int uSalary;
    private String phone;
    
}