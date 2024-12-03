package com.example.demo.dto;


import lombok.Data;

@Data
public class Company {
	
	// 사용자 ID(외래키)
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
	
	// 기업 번호
    private int comNo;

    
    
    // 기업 이름
    private String comName;

    // 사업자 등록 번호
    private String businessNo;

    // 대표자명
    private String ceoName;

    // 기업 형태
    private String comType;

    // 업종
    private String comSector;

    // 사원 수
    private int employeeCount;

    // 매출액
    private int sales;

    // 기업 연락처
    private String tel;
    
    //기업 상세설명
    private String comDescription;
}
