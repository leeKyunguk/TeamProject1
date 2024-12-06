package com.example.demo.controller;

import com.example.demo.dto.Board;
import com.example.demo.dto.Comment;
import com.example.demo.dto.Pagination;
import com.example.demo.service.BoardService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시글 목록 페이지
    @GetMapping("/communityPosts")
    public String getBoardList(Model model) {
        model.addAttribute("boardList", boardService.getBoardList());
        return "communityPosts"; // communityPosts.html
    }

    // 게시글 상세 페이지
    @GetMapping("/detail/{boardNo}")
    public String getBoardDetail(@PathVariable("boardNo") int boardNo, Model model) {
        model.addAttribute("board", boardService.getBoardDetail(boardNo));
        model.addAttribute("comment", new Comment());
        return "communityDetail"; // communityDetail.html
    }

    // 게시글 저장
    @PostMapping("/save")
    public String saveBoard(@ModelAttribute Board board) {
        boardService.insertBoard(board);
        return "redirect:/list"; // 목록 페이지로 리다이렉트
    }

    // 댓글 저장
    @PostMapping("/comment/save")
    public String saveComment(@ModelAttribute Comment comment) {
        boardService.insertComment(comment);
        return "redirect:/detail/" + comment.getBoardNo(); // 상세 페이지로 리다이렉트
    }

    // 게시글 삭제
    @GetMapping("/delete/{boardNo}")
    public String deleteBoard(@PathVariable("boardNo") int boardNo) {
        boardService.deleteBoard(boardNo);
        return "redirect:/list"; // 목록 페이지로 리다이렉트
    }

    // 댓글 삭제
    @GetMapping("/comment/delete/{commentNo}")
    public String deleteComment(@PathVariable("commentNo") int commentNo, @RequestParam("boardNo") int boardNo) {
        boardService.deleteComment(commentNo);
        return "redirect:/detail/" + boardNo; // 상세 페이지로 리다이렉트
    }
}

