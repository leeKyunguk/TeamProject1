package com.example.demo.dto;

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
	private String techStack;
	private int uSalary;
	private String title;
	private String comName;
	private String location;
	private String status;
	private String deadline;
	private String usersId;
}