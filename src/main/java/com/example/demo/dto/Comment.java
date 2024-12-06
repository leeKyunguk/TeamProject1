package com.example.demo.dto;

import lombok.Data;
import java.util.Date;

@Data
public class Comment {
    private int commentNo;         // 댓글 번호
    private int boardNo;           // 게시글 번호 (FK)
    private Integer userNo;        // 작성자 (구직자 FK) - Null 가능
    private Integer comNo;         // 작성자 (기업 FK) - Null 가능
    private String comment;        // 댓글 내용
    private Date commentRegDate;   // 댓글 등록일
    private String writerName;     // 작성자 이름 (nickname 또는 comName)

    // 작성자 이름을 설정하는 메서드
    public void setWriterName(String nickname, String comName) {
        if (userNo != null) {
            this.writerName = nickname; // userNo가 있을 경우 닉네임
        } else if (comNo != null) {
            this.writerName = comName; // comNo가 있을 경우 기업명
        } else {
            this.writerName = "알 수 없음"; // 작성자 정보가 없는 경우
        }
    }
}
