package com.example.demo.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class JobPosting {
	
	public enum PostGradu {
		고등학교졸업 {
	        @Override
	        public String toString() {
	            return "고등학교졸업";
	        }
	    },
		전문대학졸 {
	        @Override
	        public String toString() {
	            return "전문대학졸";
	            }
	    },
		대졸 {
	        @Override
	        public String toString() {
	            return "대졸";
	        }
	    }
	}

	public enum Status {
		모집중 {
	        @Override
	        public String toString() {
	            return "모집중";
	        }
	    },
		마감 {
	        @Override
	        public String toString() {
	            return "마감";
	        }
	    }
	}
	
	private String comName;
	private Integer postNo; // 공고번호
	private int comNo; // 회사 ID
	private String managerName; // 채용담당자
	private String usersId;
    private String postExperience; // 회사가 원하는 경력(년수)
    private String workType; // 정규/계약직 여부
    private PostGradu postGradu; // 학력
    private String title; // 공고제목
    private String postDescription; // 공고내용
    private String postTechStack; // 요구기술스택
    private String location; // 근무지역
    private int postSalary; // 연봉
    private LocalDate regdate; // 등록일
    private LocalDate deadline; // 마감일
    private Status status;       // 공고 상태
    private String applicated;
}
