package com.example.demo.dto;


import lombok.Data;

@Data
public class UserProfiles {

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
    private int userNo;
    private String userName;
    private String nickname;
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
    private String phone;
    
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}