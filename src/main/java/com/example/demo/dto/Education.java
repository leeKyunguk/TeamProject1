package com.example.demo.dto;

import lombok.Data;

@Data
public class Education {
	private Integer eduNo;           // 학력 고유번호 (Primary Key)
    private Integer resumeNo;        // 이력서 번호 (Foreign Key)
    private String schoolName;       // 학교명
    private String degree;           // 학위 (e.g., 학사, 석사, 박사)
    private String major;            // 전공
    private String startDate;        // 입학일
    private String endDate;          // 졸업일
    private String graduationStatus; // 졸업 상태 (졸업, 중퇴, 재학 등)
}
