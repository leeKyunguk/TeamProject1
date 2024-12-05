package com.example.demo.dto;

import com.example.demo.dto.UserProfiles.Role;

import lombok.Data;

@Data
public class Users {

	private String usersId;
	private String password;
	private String email;
	public enum Role {
        JOB_SEEKER,
        COMPANY;

        @Override
        public String toString() {
            switch (this) {
                case JOB_SEEKER:
                    return "구직자";
                case COMPANY:
                    return "기업";
                default:
                    return super.toString();
            }
        }
    }
    private Role role;
	private String address;

}