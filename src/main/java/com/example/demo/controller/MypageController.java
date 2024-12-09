package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.IJobPosting;
import com.example.demo.dto.Company;
import com.example.demo.dto.Company.Role;
import com.example.demo.dto.UserProfiles;

import jakarta.servlet.http.HttpSession;

@Controller
public class MypageController {
	
	@Autowired
	IJobPosting ijp;
	
	@PostMapping("/bookMarkedCompany")
	public String bookMarkedCompany(HttpSession session, Model model) {
		UserProfiles userProfiles = (UserProfiles) session.getAttribute("userProfiles");
		if (userProfiles != null && Role.valueOf(userProfiles.getRole().toString()) == Role.JOB_SEEKER) {
			model.addAttribute("userProfiles", userProfiles);
			return "bookMarkedCompany";
		} else {
			return "redirect:/login";
		}
	}
	
	@PostMapping("/bookMarkedCompany1")
	public @ResponseBody String bookMarkedCompany1(@RequestParam("comNo") int comNo, @RequestParam("userNo") int userNo) {
	    try {
	        // 북마크 상태 확인
	        int bookmarkCount = ijp.checkBookmarkStatus(comNo, userNo);

	        if (bookmarkCount > 0) {
	            // 북마크 삭제
	            ijp.deleteBookmark(comNo, userNo);
	            return "북마크가 취소되었습니다.";
	        } else {
	            // 북마크 추가
	            ijp.addBookmark(comNo, userNo);
	            return "북마크 되었습니다.";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "북마크 처리 중 오류가 발생했습니다.";
	    }
	}
	
	/*
	 * @PostMapping("/addBookmark") public String addBookmark(@RequestParam("comNo")
	 * int comNo, @RequestParam("userNo") int userNo, @RequestParam("comName")
	 * String comName) { // 북마크 객체 생성 UscBookMark uscBookMark = new UscBookMark();
	 * uscBookMark.setComNo(comNo); // comNo로 수정 uscBookMark.setUserNo(userNo);
	 * uscBookMark.setUcSubscribed(UscBookMark.UcSubscribed.BOOKMARK); // BOOKMARK로
	 * 설정
	 * 
	 * // 북마크 추가 ijp.addBookmark(comNo, userNo); // 북마크 추가 메소드 호출
	 * 
	 * return "redirect:/viewJobsPostings"; // 리다이렉트 처리 }
	 */
	
	@RequestMapping("/scrapedNotice")
	public String scrapedNotice() {
		return "scrapedNotice";
	}
	
	@RequestMapping("/modifyResume")
	public String modifyResume() {
		return "modifyResume";
	}
	
	
	@RequestMapping("/modifyPrivacy")
	   public String userDetail(HttpSession session, Model model) {
		UserProfiles userProfiles = (UserProfiles) session.getAttribute("userProfiles");
		Company company = (Company) session.getAttribute("company");
	    // 유저 프로필이 존재하는 경우
		if (userProfiles != null && Role.valueOf(userProfiles.getRole().toString()) == Role.JOB_SEEKER) {
	        model.addAttribute("userProfiles", userProfiles);
	        return "modifyPrivacy"; // 구직자 상세 페이지로 이동
	    }
	    // 회사 정보가 존재하는 경우
	    if (company != null && company.getRole() == Role.COMPANY) {
	        model.addAttribute("company", company);
	        return "modifyCom"; // 기업 상세 페이지로 이동
	    } else {
	           return "redirect:/login";
	       }
	   }

	@RequestMapping("/withdraw")	// 개인, 기업 동시처리
	public String withdraw() {
		return "withdraw";
	}
	
	@RequestMapping("/bookMarkedJobSeeker")
	public String bookMarkedJobSeeker() {
		return "bookMarkedJobSeeker";
	}
	
	
}
