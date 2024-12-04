package com.example.demo.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class JobPosting {
	
	public enum PostGradu {
	    H {
	        @Override
	        public String toString() {
	            return "고등학교 졸업";
	        }
	    },
	    C {
	        @Override
	        public String toString() {
	            return "전문대학 졸업";
	        }
	    },
	    U {
	        @Override
	        public String toString() {
	            return "4년제 졸업";
	        }
	    }
	}

	public enum Status {
	    OPEN {
	        @Override
	        public String toString() {
	            return "모집중";
	        }
	    },
	    CLOSE {
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
}
