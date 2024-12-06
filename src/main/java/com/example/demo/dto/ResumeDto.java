package com.example.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class ResumeDto {
	private Integer resumeNo; // 이력서 번호 (PK)
	private String usersId;   // 회원 ID (FK)
    private String regDate;    // 이력서 등록 일자

    // 학력사항 목록 (List로 변경)
    private List<Education> educationList;  

    // 경력사항 목록 (List로 변경)
    private List<Experience> experienceList;

    // 자격증 목록 (List로 변경)
    private List<Certification> certificationList;

    // 병역 사항 (단일 객체로 유지)
    private Military military;  

    // 자기소개서 (단일 객체로 유지)
    private SelfIntroduction selfIntroduction;
}
