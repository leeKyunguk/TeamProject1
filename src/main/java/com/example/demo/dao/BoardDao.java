package com.example.demo.dao;

import com.example.demo.dto.Board;
import com.example.demo.dto.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardDao {

    // 게시글 목록 조회 (페이지네이션)
    List<Board> getBoardList(/*@Param("offset") int offset, @Param("limit") int limit*/);

    // 게시글 상세 조회
    Board getBoardDetail(@Param("boardNo") int boardNo);

    // 댓글 목록 조회
    List<Comment> getCommentsByBoardNo(@Param("boardNo") int boardNo);

    // 게시글 등록
    void insertBoard(@Param("userNo") int userNo, 
                     @Param("comNo") int comNo, 
                     @Param("boardTitle") String boardTitle, 
                     @Param("boardContent") String boardContent);

    // 댓글 등록
    void insertComment(@Param("boardNo") int boardNo, 
                       @Param("userNo") int userNo, 
                       @Param("comNo") int comNo,  
                       @Param("commentContent") String commentContent);

    // 게시글 삭제
    void deleteBoard(@Param("boardNo") int boardNo);

    // 댓글 삭제
    void deleteComment(@Param("commentNo") int commentNo);

    // 게시글 총 개수 조회
    int getBoardCount();
}
