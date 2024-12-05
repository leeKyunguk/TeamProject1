package com.example.demo.dto;

import com.example.demo.dto.UserProfiles.Role;

import lombok.Data;

@Data
public class Company {

    private int comNo;
    private String usersId;
    private String comName;
    private String businessNo;
    private String ceoName;
    private String comType;
    private String comSector;
    private int employeeCount;
    private int sales;
    private String tel;
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
    private String comDescription;
}
