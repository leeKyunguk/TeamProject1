package com.example.demo.dto;

import com.example.demo.dto.UserProfiles.Gender;

import lombok.Data;

@Data
public class Applicant {

	private int appNo;
	private int postNo;
	private int userNo;
	private String userName;
	private String resiNumber;
    public enum Gender {
        M,
        F;
        @Override
        public String toString() {
            return name();
        }
    }
    private Gender gender;
	private String teckStack;
	private int uSalary;
	
}