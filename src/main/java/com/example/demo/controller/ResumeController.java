package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.Certification;
import com.example.demo.dto.Education;
import com.example.demo.dto.Experience;
import com.example.demo.dto.Military;
import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.SelfIntroduction;
import com.example.demo.service.ResumeService;

@Controller
@RequestMapping("/resume")
public class ResumeController {
	@Autowired
	ResumeService resumeservice;
	
	@GetMapping("/resumeList")
	public String root() {
		return "/resume/resumeList";
	}
	
	// 이력서 등록 페이지 이동
    @GetMapping("/register")
    public String registerForm(Model model) {
    	// 컨트롤러 코드 수정
    	ResumeDto resumeDto = new ResumeDto();

    	// 빈 리스트 설정 및 기본 항목 추가
    	List<Education> educationList = new ArrayList<>();
    	educationList.add(new Education()); // 기본 빈 객체 추가
    	resumeDto.setEducationList(educationList);

    	List<Experience> experienceList = new ArrayList<>();
    	experienceList.add(new Experience());
    	resumeDto.setExperienceList(experienceList);

    	List<Certification> certificationList = new ArrayList<>();
    	certificationList.add(new Certification());
    	resumeDto.setCertificationList(certificationList);

    	resumeDto.setMilitary(new Military());
    	resumeDto.setSelfIntroduction(new SelfIntroduction());

    	model.addAttribute("resume", resumeDto);
        return "/resume/resumeForm"; // 이력서 등록 및 수정 페이지로 이동
    }
    
    
 // 이력서 등록 처리
    @PostMapping("/register")
    public String register(@ModelAttribute ResumeDto resumeDto) {
        resumeservice.registerResume(resumeDto);
        return "redirect:/resume/resumeList"; // 등록 후 이력서 목록으로 리다이렉트
    }

	
}
