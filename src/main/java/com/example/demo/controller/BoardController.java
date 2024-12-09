package com.example.demo.controller;

import com.example.demo.dto.Board;
import com.example.demo.dto.Comment;
import com.example.demo.dto.Company;
import com.example.demo.dto.Pagination;
import com.example.demo.dto.UserProfiles;
import com.example.demo.service.BoardService;

import jakarta.servlet.http.HttpSession;

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
    public String getBoardList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            Model model
        ) {
            int totalItems = boardService.getBoardCount();
            int totalPages = (int) Math.ceil((double) totalItems / size);
            
            //페이지는 무조건 1이어야기 때문
            if (page < 1) page = 1;
            if (page > totalPages) page = totalPages;

            int offset = (page - 1) * size;
            model.addAttribute("boardList", boardService.getBoardList(offset, size));
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("pageSize", size);

            return "communityPosts"; // 게시글 목록 페이지
        }
    
    // 게시글 작성 페이지로 이동
    @GetMapping("/form")
    public String showBoardForm(HttpSession session, Model model) {
    	//세션에서 로그인 정보를 가져옴
    	UserProfiles userProfiles = (UserProfiles) session.getAttribute("userProfiles");
        Company company = (Company) session.getAttribute("company");

        if (userProfiles == null && company == null) {
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }
        
        if(userProfiles != null) {
        	model.addAttribute("nickName",userProfiles.getNickname());
        	
        }else if(company !=null) {
        	model.addAttribute("comName",company.getComName());
        	
        }
	
        model.addAttribute("board", new Board()); // 새 게시글 객체 추가
        return "communityForm"; // 게시글 작성 페이지 반환
    }

    // 게시글 상세 페이지
    @GetMapping("/detail/{boardNo}")
    public String getBoardDetail(@PathVariable("boardNo") int boardNo, Model model) {
        model.addAttribute("board", boardService.getBoardDetail(boardNo));
        model.addAttribute("comment", new Comment());
        return "communityDetail"; // 게시글 상세 페이지
    }

    // 게시글 저장
    @PostMapping("/save")
    public String saveBoard(@ModelAttribute Board board, HttpSession session) {
        // 세션에서 로그인 정보를 가져옴
    	UserProfiles userProfiles = (UserProfiles) session.getAttribute("userProfiles");
        Company company = (Company) session.getAttribute("company");


        if (userProfiles != null) {
            board.setUserNo(userProfiles.getUserNo());
        } else if (company != null) {
            board.setComNo(company.getComNo());
        }

        boardService.insertBoard(board);
        return "redirect:/communityPosts";
    }

    // 댓글 저장
    @PostMapping("/comment/save")
    public String saveComment(@ModelAttribute Comment comment, HttpSession session) {
        // 세션에서 로그인 정보를 가져옴
        Integer userNo = (Integer) session.getAttribute("userNo");
        Integer comNo = (Integer) session.getAttribute("comNo");

        if (userNo != null) {
            comment.setUserNo(userNo);
        } else if (comNo != null) {
            comment.setComNo(comNo);
        } else {
            throw new IllegalStateException("댓글 작성 권한이 없습니다.");
        }

        boardService.insertComment(comment);
        return "redirect:/detail/" + comment.getBoardNo();
    }

    // 게시글 삭제
    @GetMapping("/delete/{boardNo}")
    public String deleteBoard(@PathVariable("boardNo") int boardNo, HttpSession session) {
        // 세션에서 로그인 정보를 가져옴
        Integer userNo = (Integer) session.getAttribute("userNo");
        Integer comNo = (Integer) session.getAttribute("comNo");

        boolean isDeleted = boardService.deleteBoard(boardNo, userNo, comNo);

        if (!isDeleted) {
            throw new IllegalStateException("게시글 삭제 권한이 없습니다.");
        }

        return "redirect:/communityPosts";
    }

    // 댓글 삭제
    @GetMapping("/comment/delete/{commentNo}")
    public String deleteComment(@PathVariable("commentNo") int commentNo, @RequestParam("boardNo") int boardNo, HttpSession session) {
        // 세션에서 로그인 정보를 가져옴
        Integer userNo = (Integer) session.getAttribute("userNo");
        Integer comNo = (Integer) session.getAttribute("comNo");

        boolean isDeleted = boardService.deleteComment(commentNo, userNo, comNo);

        if (!isDeleted) {
            throw new IllegalStateException("댓글 삭제 권한이 없습니다.");
        }

        return "redirect:/detail/" + boardNo;
    }
}

