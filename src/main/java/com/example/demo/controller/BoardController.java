package com.example.demo.controller;

import com.example.demo.dto.Board;
import com.example.demo.dto.Comment;
import com.example.demo.dto.Company;
import com.example.demo.dto.UserProfiles;
import com.example.demo.service.BoardService;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
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
    public String getBoardList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            Model model) {
        int totalItems = boardService.getBoardCount();
        int totalPages = (int) Math.ceil((double) totalItems / size);

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
        UserProfiles userProfiles = (UserProfiles) session.getAttribute("userProfiles");
        Company company = (Company) session.getAttribute("company");

        if (userProfiles == null && company == null) {
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        if (userProfiles != null) {
            model.addAttribute("nickName", userProfiles.getNickname());
        } else if (company != null) {
            model.addAttribute("comName", company.getComName());
        }

        model.addAttribute("board", new Board()); // 새 게시글 객체 추가
        return "communityForm"; // 게시글 작성 페이지 반환
    }

    // 게시글 저장
    @PostMapping("/save")
    public String saveBoard(@ModelAttribute Board board, HttpSession session) {
        UserProfiles userProfiles = (UserProfiles) session.getAttribute("userProfiles");
        Company company = (Company) session.getAttribute("company");

        if (userProfiles == null && company == null) {
            throw new IllegalStateException("로그인 정보가 없습니다.");
        }

        if (userProfiles != null) {
            board.setUserNo(userProfiles.getUserNo());
        } else if (company != null) {
            board.setComNo(company.getComNo());
        }

        boardService.insertBoard(board);
        return "redirect:/communityPosts";
    }

    // 게시글 상세 페이지
    @GetMapping("/detail/{boardNo}")
    public String getBoardDetail(@PathVariable("boardNo") int boardNo, Model model) {
        model.addAttribute("board", boardService.getBoardDetail(boardNo));
        return "communityDetail"; // 게시글 상세 페이지
    }

    // 게시글 삭제
    @GetMapping("/delete/{boardNo}")
    public String deleteBoard(@PathVariable("boardNo") int boardNo, HttpSession session) {
        UserProfiles userProfiles = (UserProfiles) session.getAttribute("userProfiles");
        Company company = (Company) session.getAttribute("company");

        if (userProfiles == null && company == null) {
            throw new IllegalStateException("로그인 정보가 없습니다.");
        }

        Board board = boardService.getBoardDetail(boardNo);

        if ((board.getUserNo() != null && (userProfiles == null || !board.getUserNo().equals(userProfiles.getUserNo()))) &&
            (board.getComNo() != null && (company == null || !board.getComNo().equals(company.getComNo())))) {
            throw new IllegalStateException("삭제 권한이 없습니다.");
        }

        boolean isDeleted = boardService.deleteBoard(boardNo,
                userProfiles != null ? userProfiles.getUserNo() : null,
                company != null ? company.getComNo() : null);

        if (!isDeleted) {
            throw new IllegalStateException("게시글 삭제에 실패했습니다.");
        }

        return "redirect:/communityPosts";
    }

    // 댓글 저장 (AJAX 요청)
    @PostMapping("/comment/save")
    @ResponseBody
    public ResponseEntity<String> saveComment(@RequestBody Comment comment, HttpSession session) {
        UserProfiles userProfiles = (UserProfiles) session.getAttribute("userProfiles");
        Company company = (Company) session.getAttribute("company");

        if (userProfiles == null && company == null) {
            return ResponseEntity.status(403).body("로그인 정보가 없습니다."); // 로그인이 필요한 상태
        }

        if (userProfiles != null) {
            comment.setUserNo(userProfiles.getUserNo());
        } else if (company != null) {
            comment.setComNo(company.getComNo());
        }

        boardService.insertComment(comment);
        System.out.println(comment);
        return ResponseEntity.ok("댓글이 성공적으로 등록되었습니다.");
    }

    // 댓글 삭제 (AJAX 요청)
    @DeleteMapping("/comment/delete/{commentNo}")
    @ResponseBody
    public ResponseEntity<String> deleteComment(@PathVariable("commentNo") int commentNo, HttpSession session) {
        UserProfiles userProfiles = (UserProfiles) session.getAttribute("userProfiles");
        Company company = (Company) session.getAttribute("company");

        if (userProfiles == null && company == null) {
            return ResponseEntity.status(403).body("로그인 정보가 없습니다."); // 로그인이 필요한 상태
        }

        boolean isDeleted = boardService.deleteComment(commentNo,
                userProfiles != null ? userProfiles.getUserNo() : null,
                company != null ? company.getComNo() : null);

        if (!isDeleted) {
            return ResponseEntity.status(403).body("댓글 삭제 권한이 없습니다.");
        }

        return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
    }
}
