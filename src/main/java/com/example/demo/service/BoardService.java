package com.example.demo.service;

import com.example.demo.dao.BoardDao;
import com.example.demo.dto.Board;
import com.example.demo.dto.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardDao boardDao;

    public BoardService(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    // 게시판 목록 페이지네이션
    public List<Board> getBoardList(int offset, int pageSize) {
        return boardDao.getBoardList(offset, pageSize);
    }

    // 게시글 상세 조회
    public Board getBoardDetail(int boardNo) {
        // 게시글 정보 조회
        Board board = boardDao.getBoardDetail(boardNo);
        if (board != null) {
            // 댓글 목록 추가
            board.setComments(boardDao.getCommentsByBoardNo(boardNo));
        }
        return board;
    }

    // 전체 게시글 수 조회
    public int getBoardCount() {
        return boardDao.getBoardCount();
    }

    // 게시글 등록
    public void insertBoard(Board board) {
        boardDao.insertBoard(board);
    }

    // 게시글 삭제 (본인 확인)
    public boolean deleteBoard(int boardNo, Integer loggedInUserNo, Integer loggedInComNo) {
        // 게시글 정보 가져오기
        Board board = boardDao.getBoardDetail(boardNo);
        if (board == null) {
            return false; // 게시글이 존재하지 않음
        }

        // 작성자 확인
        boolean isOwner = (loggedInUserNo != null && board.getUserNo() != null && board.getUserNo().equals(loggedInUserNo)) ||
                          (loggedInComNo != null && board.getComNo() != null && board.getComNo().equals(loggedInComNo));

        if (isOwner) {
            boardDao.deleteBoard(boardNo);
            return true; // 삭제 성공
        }
        return false; // 작성자가 아님
    }

    // 댓글 등록
    public void insertComment(Comment comment) {
        boardDao.insertComment(comment);
    }

    // 댓글 삭제 (본인 확인)
    public boolean deleteComment(int commentNo, Integer loggedInUserNo, Integer loggedInComNo) {
        // 댓글 정보 가져오기
        Comment comment = boardDao.getCommentDetail(commentNo);
        if (comment == null) {
            return false; // 댓글이 존재하지 않음
        }

        // 작성자 확인
        boolean isOwner = (loggedInUserNo != null && comment.getUserNo() != null && comment.getUserNo().equals(loggedInUserNo)) ||
                          (loggedInComNo != null && comment.getComNo() != null && comment.getComNo().equals(loggedInComNo));

        if (isOwner) {
            boardDao.deleteComment(commentNo);
            return true; // 삭제 성공
        }
        return false; // 작성자가 아님
    }

    // 댓글 목록 조회
    public List<Comment> getCommentsByBoardNo(int boardNo) {
        return boardDao.getCommentsByBoardNo(boardNo);
    }

    // 특정 댓글 상세 조회
    public Comment getCommentDetail(int commentNo) {
        return boardDao.getCommentDetail(commentNo);
    }
}
