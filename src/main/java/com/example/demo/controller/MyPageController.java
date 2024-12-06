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
public class MyPageController {
   
   @Autowired
   IJobPosting ijp;
   
   @RequestMapping("/appliedJobList")
   public String appliedJobList() {
      
      return "appliedJobList";
   }
   
   @RequestMapping("/bookMarkedCompany")
   public String bookMarkedCompany(HttpSession session, Model model) {
      UserProfiles userProfiles = (UserProfiles) session.getAttribute("userProfiles");
      if (userProfiles != null && Role.valueOf(userProfiles.getRole().toString()) == Role.JOB_SEEKER) {
         model.addAttribute("userProfiles", userProfiles);
         return "bookMarkedCompany";
      } else {
         return "redirect:/login";
      }
   }
   
//   @RequestMapping("/bookMarkedCompany1")
//   public @ResponseBody String bookMarkedCompany1(@RequestParam("comName") int comName, @RequestParam("userNo") int userNo, HttpSession session) {
//       try {
//           // 북마크가 있는지 확인
//           boolean isBookmarked = ijp.isBookmarked(comName, userNo);
//
//           if (isBookmarked) {
//               ijp.deleteBookmark(comName, userNo);  // 북마크 삭제
//               return "북마크가 취소되었습니다.";
//           } else {
//               ijp.addBookmark(comName, userNo);  // 북마크 추가
//               return "북마크 되었습니다.";
//           }
//       } catch (Exception e) {
//           e.printStackTrace();
//           return "북마크 처리 중 오류가 발생했습니다.";
//       }
//   }
   
   
	/*
	 * @PostMapping("/addBookmark") public String
	 * addBookmark(@RequestParam("userNo") int userNo, @RequestParam("comNo") int
	 * comNo) { // 북마크 객체 생성 UscBookMark uscBookMark = new UscBookMark();
	 * uscBookMark.setUserNo(userNo); uscBookMark.setComNo(comNo);
	 * uscBookMark.setUcSubscribed(UscBookMark.UcSubscribed.BOOKMARK); // BOOKMARK로
	 * 설정
	 * 
	 * // 북마크 추가 ijp.isBookmarked(userNo, comNo);
	 * 
	 * return "redirect:/somePage"; // 리다이렉트 처리 }
	 */
   
   @RequestMapping("/scrapedNotice")
   public String scrapedNotice() {
      return "scrapedNotice";
   }
   
   @RequestMapping("/modifyResume")
   public String modifyResume() {
      return "modifyResume";
   }

   @RequestMapping("/withdraw")   // 개인, 기업 동시처리
   public String withdraw() {
      return "withdraw";
   }
   
   @RequestMapping("/bookMarkedJobSeeker")
   public String bookMarkedJobSeeker() {
      return "bookMarkedJobSeeker";
   }
   
}
