package com.example.demo.controller;

import com.example.demo.service.SignUpService;
import com.example.demo.dto.Company;
import com.example.demo.dto.UserProfiles;
import com.example.demo.dto.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    // 메인 페이지 요청
    @GetMapping("/")
    public String root() {
    	return "index1";
    }
    // 회원가입 페이지 요청
    @GetMapping("/signUp")
    public String signupPage() {
        return "signUp";  // signup.html 페이지 반환
    }

    // 구직자 회원가입 폼 페이지 요청
    @GetMapping("/jobSeekerForm")
    public String jobSeekerForm(Model model) {
        model.addAttribute("userProfiles", new UserProfiles());  // 구직자 프로필 DTO 객체를 모델에 추가
        return "jobSeekerForm";  // jobSeekerForm.html 타임리프 템플릿 반환
    }

    // 기업 회원가입 폼 페이지 요청
    @GetMapping("/companyForm")
    public String companyForm(Model model) {
        model.addAttribute("company", new Company());  // 기업 DTO 객체를 모델에 추가
        return "companyForm";  // companyForm.html 타임리프 템플릿 반환
    }

    // 구직자 회원가입 데이터 처리
    @PostMapping("/registerJobSeeker")
    public String registerJobSeeker(@ModelAttribute UserProfiles userProfiles) {
        signUpService.registerJobSeeker(userProfiles);
        return "redirect:/successPage";  // 회원가입 성공 후 성공 페이지로 리다이렉트
    }

    // 기업 회원가입 데이터 처리
    @PostMapping("/registerCompany")
    public String registerCompany(@ModelAttribute Company company) {
        signUpService.registerCompany(company);
        return "redirect:/successPage";  // 회원가입 성공 후 성공 페이지로 리다이렉트
    }

    // 구직자 중복 확인 처리
    @PostMapping("/checkDuplicates")
    @ResponseBody
    public Map<String, Object> checkDuplicates(@RequestBody Map<String, String> requestData) {
        boolean isDuplicate = false;
        StringBuilder message = new StringBuilder();

        // 사용자 ID 중복 확인
        if (signUpService.isUserIdDuplicate(requestData.get("usersId"))) {
            isDuplicate = true;
            message.append("사용자 ID가 중복되었습니다.\n");
        }

        // 이메일 중복 확인
        if (signUpService.isEmailDuplicate(requestData.get("email"))) {
            isDuplicate = true;
            message.append("이메일이 중복되었습니다.\n");
        }

        // 별칭 중복 확인
        if (signUpService.isNicknameDuplicate(requestData.get("nickname"))) {
            isDuplicate = true;
            message.append("별칭이 중복되었습니다.\n");
        }

        // 주민번호 중복 확인
        if (signUpService.isResiNumberDuplicate(requestData.get("resiNumber"))) {
            isDuplicate = true;
            message.append("주민번호가 중복되었습니다.\n");
        }

        // 연락처 중복 확인
        if (signUpService.isPhoneDuplicate(requestData.get("phone"))) {
            isDuplicate = true;
            message.append("연락처가 중복되었습니다.\n");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);
        response.put("message", isDuplicate ? message.toString().trim() : "중복된 데이터가 없습니다.");
        return response;
    }

    // 기업 중복 확인 처리
    @PostMapping("/checkCompanyDuplicates")
    @ResponseBody
    public Map<String, Object> checkCompanyDuplicates(@RequestBody Map<String, String> requestData) {
        boolean isDuplicate = false;
        StringBuilder message = new StringBuilder();

        // 사용자 ID 중복 확인
        if (signUpService.isUserIdDuplicate(requestData.get("usersId"))) {
            isDuplicate = true;
            message.append("사용자 ID가 중복되었습니다.\n");
        }

        // 이메일 중복 확인
        if (signUpService.isEmailDuplicate(requestData.get("email"))) {
            isDuplicate = true;
            message.append("이메일이 중복되었습니다.\n");
        }

        // 사업자 등록번호 중복 확인
        if (signUpService.isBusinessNoDuplicate(requestData.get("businessNo"))) {
            isDuplicate = true;
            message.append("사업자 등록번호가 중복되었습니다.\n");
        }

        // 기업 연락처 중복 확인
        if (signUpService.isTelDuplicate(requestData.get("tel"))) {
            isDuplicate = true;
            message.append("기업 연락처가 중복되었습니다.\n");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);
        response.put("message", isDuplicate ? message.toString().trim() : "중복된 데이터가 없습니다.");
        return response;
    }

    // 성공 페이지
    @GetMapping("/successPage")
    public String successPage() {
        return "success";  // success.html 페이지 반환
    }
}
