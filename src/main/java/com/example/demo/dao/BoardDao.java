package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Board;
import com.example.demo.dto.Comment;

@Mapper
public interface BoardDao {

    // 게시글 목록 조회 (페이지네이션)
    /**
     * 게시글 목록을 페이징 처리하여 조회
     *
     * @param offset 시작 위치
     * @param limit  조회할 개수
     * @return 게시글 리스트
     */
    List<Board> getBoardList(@Param("offset") int offset, @Param("limit") int limit);

    // 게시글 상세 조회
    /**
     * 게시글 번호로 상세 정보 조회
     *
     * @param boardNo 게시글 번호
     * @return 게시글 객체
     */
    Board getBoardDetail(@Param("boardNo") int boardNo);

    // 댓글 상세 조회
    /**
     * 댓글 번호로 상세 정보 조회
     *
     * @param commentNo 댓글 번호
     * @return 댓글 객체
     */
    Comment getCommentDetail(@Param("commentNo") int commentNo);

    // 댓글 목록 조회
    /**
     * 특정 게시글 번호에 대한 댓글 목록 조회
     *
     * @param boardNo 게시글 번호
     * @return 댓글 리스트
     */
    List<Comment> getCommentsByBoardNo(@Param("boardNo") int boardNo);

    // 게시글 등록
    /**
     * 게시글 등록
     *
     * @param board 등록할 게시글 객체
     */
    void insertBoard(Board board);

    // 댓글 등록
    /**
     * 댓글 등록
     *
     * @param comment 등록할 댓글 객체
     */
    void insertComment(Comment comment);

    // 게시글 삭제
    /**
     * 게시글 삭제
     *
     * @param boardNo 삭제할 게시글 번호
     */
    void deleteBoard(@Param("boardNo") int boardNo);

    // 댓글 삭제
    /**
     * 댓글 삭제
     *
     * @param commentNo 삭제할 댓글 번호
     */
    void deleteComment(@Param("commentNo") int commentNo);

    // 게시글 총 개수 조회
    /**
     * 전체 게시글 수 조회
     *
     * @return 게시글 총 개수
     */
    int getBoardCount();
}
