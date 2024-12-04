package com.example.demo.controller;

import com.example.demo.dto.Board;
import com.example.demo.dto.Comment;
import com.example.demo.dto.Pagination;
import com.example.demo.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 메인 페이지
    @GetMapping("/")
    public String root() {
        return "main"; // main.html
    }

    // 게시글 목록 페이지
    @GetMapping("/list")
    public String getBoardList(
        @RequestParam(defaultValue = "1") Integer page, // 현재 페이지
        @RequestParam(defaultValue = "10") Integer size, // 페이지 당 게시글 수
        Model model
    ) {
        int totalItems = boardService.getBoardCount(); // 전체 게시글 수
        Pagination pagination = new Pagination(page, size, totalItems); // 페이지네이션 계산

        model.addAttribute("boardList", boardService.getBoardList(pagination.getCurrentPage(), pagination.getPageSize()));
        model.addAttribute("currentPage", pagination.getCurrentPage());
        model.addAttribute("totalPages", pagination.getTotalPages());
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
