package com.example.demo.dto;

import lombok.Data;

@Data
public class Experience {
	private Integer expNo;           // 경력 고유번호 (Primary Key)
    private Integer resumeNo;        // 이력서 번호 (Foreign Key)
    private String companyName;      // 직장명
    private String jobName;          // 직무
    private String jobPosition;      // 직책
    private String startDate;        // 근무 시작일
    private String endDate;          // 근무 종료일
    private String totalDuration;    // 총 근무 기간
}
