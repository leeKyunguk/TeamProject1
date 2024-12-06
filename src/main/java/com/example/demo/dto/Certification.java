package com.example.demo.dto;

import lombok.Data;

@Data
public class Certification {
	private Integer cerNo;               // 자격증 고유번호 (Primary Key)
    private Integer resumeNo;            // 이력서 번호 (Foreign Key)
    private String cerName;              // 자격증명
    private String issuingAuthority;     // 발급기관
    private String cerNumber;            // 자격증 번호
    private String issuedDate;           // 발급 일자
}
