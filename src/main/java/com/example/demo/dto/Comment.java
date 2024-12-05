package com.example.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private int commentNo;         // 댓글 번호
    private int boardNo;           // 게시글 번호 (FK)
    private int userNo;            // 작성자 (구직자 FK)
    private int comNo;             // 작성자 (기업 FK)
    private String comment; // 댓글 내용
    private Date commentRegDate;   // 댓글 등록일
    private String writerName;     // 작성자 이름 (nickname 또는 comName)
}