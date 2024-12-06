package com.example.demo.dto;

import lombok.Data;

@Data
public class SelfIntroduction {
	private Integer selfNo;          // 자기소개서 고유번호 (Primary Key)
    private Integer resumeNo;        // 이력서 번호 (Foreign Key)
    private String motivation;       // 지원 동기
    private String career;           // 경력 사항
    private String personality;      // 성격 및 보유 역량
    private String aspiration;       // 입사 후 포부
}
