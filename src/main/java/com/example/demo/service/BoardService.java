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

    public List<Board> getBoardList(int currentPage, int pageSize) {
        int offset = (currentPage - 1) * pageSize;
        return boardDao.getBoardList(offset, pageSize);
    }

    public Board getBoardDetail(int boardNo) {
        Board board = boardDao.getBoardDetail(boardNo);
        if (board != null) {
            board.setComments(boardDao.getCommentsByBoardNo(boardNo));
        }
        return board;
    }

    public int getBoardCount() {
        return boardDao.getBoardCount();
    }

    public void insertBoard(Board board) {
        boardDao.insertBoard(board);
    }

    public void insertComment(Comment comment) {
        boardDao.insertComment(comment);
    }

    public void deleteBoard(int boardNo) {
        boardDao.deleteBoard(boardNo);
    }

    public void deleteComment(int commentNo) {
        boardDao.deleteComment(commentNo);
    }
}
