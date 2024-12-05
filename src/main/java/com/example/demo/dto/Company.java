package com.example.demo.dto;

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
                    return "JOB_SEEKER";
                case COMPANY:
                    return "COMPANY";
                default:
                    return super.toString();
            }
        }
    }
    private Role role;
    private String address;
    private String comDescription;
    
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
}
