package com.example.demo.dto;

import lombok.Data;

@Data
public class Resume {
	private Integer resumeNo;  // 이력서 번호 (Primary Key)
    private Integer userNo;    // 회원 번호 (Foreign Key)
    private String regDate;    // 이력서 등록일자
}
