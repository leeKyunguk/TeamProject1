package com.example.demo.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Board {
    private int boardNo;           // 게시글 번호
    private int userNo;            // 작성자 (구직자 FK)
    private int comNo;             // 작성자 (기업 FK)
    private String boardTitle;     // 게시글 제목
    private String boardContent;   // 게시글 내용
    private Date boardRegDate;     // 게시글 등록일
    private String writerName;     // 작성자 이름 (nickname 또는 comName)
    private String nickname;
    private String comName;

    private List<Comment> comments; // 댓글 리스트 (게시글과 연결된 댓글)
}
