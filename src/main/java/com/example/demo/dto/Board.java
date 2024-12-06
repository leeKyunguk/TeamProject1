package com.example.demo.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class Board {
    private int boardNo;           // 게시글 번호
    private Integer userNo;        // 작성자 (구직자 FK) - Null 가능
    private Integer comNo;         // 작성자 (기업 FK) - Null 가능
    private String boardTitle;     // 게시글 제목
    private String boardContent;   // 게시글 내용
    private Date boardRegDate;     // 게시글 등록일
    private String writerName;     // 작성자 이름 (nickname 또는 comName)

    private List<Comment> comments; // 댓글 리스트 (게시글과 연결된 댓글)

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
