package com.example.demo.dto;

import lombok.Data;

@Data
public class Military {
	private Integer milNo;           // 병역사항 번호 (Primary Key)
    private Integer resumeNo;        // 이력서 번호 (Foreign Key)
    private String militaryClass;    // 군별 (예: 육군, 해군 등)
    private String militaryPeriod;   // 복무 기간
}
