package com.example.demo.dao;

import com.example.demo.dto.Board;
import com.example.demo.dto.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardDao {

    
    List<Board> getBoardList(@Param("offset") int offset, @Param("limit") int limit);

    
    Board getBoardDetail(@Param("boardNo") int boardNo);

    
    Comment getCommentDetail(@Param("commentNo") int commentNo);

    
    List<Comment> getCommentsByBoardNo(@Param("boardNo") int boardNo);

   
    void insertBoard(Board board);

    
    void insertComment(Comment comment);

    
    void deleteBoard(@Param("boardNo") int boardNo);

  
    void deleteComment(@Param("commentNo") int commentNo);

   
    int getBoardCount();
}
