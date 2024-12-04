package com.example.team.dto;


import lombok.Data;

@Data
public class UserProfiles {
	
	 // 사용자 ID
    private String usersId;

    // 비밀번호
    private String password;

    // 사용자 이메일
    private String email;

    public enum Role {
        JOB_SEEKER,  // 구직자
        COMPANY;     // 기업

        // toString 메서드 수정
        @Override
        public String toString() {
            switch (this) {
                case JOB_SEEKER:
                    return "구직자";  // JOB_SEEKER -> 구직자 반환
                case COMPANY:
                    return "기업";    // COMPANY -> 기업 반환
                default:
                    return super.toString();  // 기본적으로 enum의 이름 반환
            }
        }
    }
    // 사용자 역할 (구직자/기업)
    private Role role;

    // 주소 (기업은 기업 주소)
    private String address;
	
    // 회원번호
    private int userNo;
    
    // 구직자 이름
    private String userName;

    // 별칭 (Unique)
    private String nickname;

    // 주민번호
    private String resiNumber;

    
    public enum Gender {
        M,  // 남성
        F; // 여성
       
        @Override
        public String toString() {
            return name(); // "MALE" 또는 "FEMALE" 반환
        }
    }
    
    private Gender gender;  // Gender 타입으로 필드 선언

    // 기술 스택
    private String techStack;

    // 희망 연봉
    private int uSalary;

    // 구직자 연락처
    private String phone;
	
	
}
