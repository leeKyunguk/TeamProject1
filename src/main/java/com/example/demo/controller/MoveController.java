package com.example.demo.controller;


import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.ICompany;
import com.example.demo.dao.IJobPosting;
import com.example.demo.dto.JobPosting;

import jakarta.servlet.http.HttpSession;

@Controller
public class MoveController {

	@Autowired
	IJobPosting ijp;
	@Autowired
	ICompany ic;
	
	@RequestMapping("/reglist")
	public String reglist(HttpSession session, Model model) {
		String comName = (String) session.getAttribute("comName");
		String managerName = (String) session.getAttribute("managerName");
		
		model.addAttribute("comName", comName);
		model.addAttribute("managerName", managerName);
		return "reglist";
	}
	@RequestMapping("/regist")
	public String regist(
			@RequestParam("postNo") int postNo,
			@RequestParam("comName") String comName,
			@RequestParam("managerName") String managerName,
			@RequestParam("usersId") String usersId,
	        @RequestParam("postExperience") String postExperience,
	        @RequestParam("workType") String workType,
	        @RequestParam("postGradu") String postGradu,
	        @RequestParam("title") String title,
	        @RequestParam("postDescription") String postDecription,
	        @RequestParam("postTechStack") String postTechStack,
	        @RequestParam("location") String loacation,
	        @RequestParam("postSalary") int postSalary,
	        @RequestParam("regdate") String regdate, 
	        @RequestParam("deadline") String deadline,
	        @RequestParam("status") String status) {
		
		int comNo = ic.getComNoByComName(comName);
		
		JobPosting jp = new JobPosting();
		jp.setPostNo(postNo);
		jp.setComNo(comNo);
		jp.setComName(comName);
		jp.setManagerName(managerName);
		jp.setUsersId(usersId);
		jp.setPostExperience(postExperience);
		jp.setWorkType(workType);
		jp.setPostGradu(convertPostGradu(postGradu));
		jp.setTitle(title);
		jp.setPostDescription(postDecription);
		jp.setPostTechStack(postTechStack);
		jp.setLocation(loacation);
		jp.setPostSalary(postSalary);
		jp.setRegdate(LocalDate.parse(regdate));
		jp.setDeadline(LocalDate.parse(deadline));
		jp.setStatus(convertStatus(status));
		
		ijp.reglist(jp);
		return "redirect:/viewlist"; 
	}
	private JobPosting.PostGradu convertPostGradu(String postGradu) {
	    switch (postGradu) {
	        case "고등학교 졸업":
	            return JobPosting.PostGradu.H;
	        case "전문대학 졸업":
	            return JobPosting.PostGradu.C;
	        case "4년제 졸업":
	            return JobPosting.PostGradu.U;
	        default:
	            throw new IllegalArgumentException("Unknown graduation level: " + postGradu);
	    }
	}
	private JobPosting.Status convertStatus(String status) {
	    switch (status) {
	        case "모집중":
	            return JobPosting.Status.OPEN;
	        case "마감":
	            return JobPosting.Status.CLOSE;
	        default:
	            throw new IllegalArgumentException("Unknown graduation level: " + status);
	    }
	}
	
	@RequestMapping("/viewlist")
	public String list(Model model) {
		List<JobPosting> list = ijp.getList();
		
		model.addAttribute("list", list);
		
		return "viewJobsPostings";
	}
	
	@RequestMapping("/jobPosting/detail/{postNo}")
	public String detail(@PathVariable("postNo") int postNo, Model model) {
	    JobPosting jobPosting = ijp.getJobPostingByPostNo(postNo);
	    model.addAttribute("jobPosting", jobPosting);
	    
	    System.out.println(jobPosting);
	    return "detail";
	}
	
	@RequestMapping("/update")
	public String update(@RequestParam("postNo") int postNo,
	                     @RequestParam("comName") String comName,
	                     @RequestParam("managerName") String managerName,
	                     @RequestParam("usersId") String usersId,
	                     @RequestParam("postExperience") String postExperience,
	                     @RequestParam("workType") String workType,
	                     @RequestParam("postGradu") String postGradu,
	                     @RequestParam("title") String title,
	                     @RequestParam("postDescription") String postDescription,
	                     @RequestParam("postTechStack") String postTechStack,
	                     @RequestParam("location") String location,
	                     @RequestParam("postSalary") int postSalary,
	                     @RequestParam("regdate") String regdate,
	                     @RequestParam("deadline") String deadline,
	                     @RequestParam("status") String status) {

		int comNo = ic.getComNoByComName(comName);
	    // JobPosting 객체 생성 및 값 설정
	    JobPosting jobPosting = new JobPosting();
	    jobPosting.setPostNo(postNo);
	    jobPosting.setComNo(comNo);
	    jobPosting.setManagerName(managerName);
	    jobPosting.setUsersId(usersId);
	    jobPosting.setPostExperience(postExperience);
	    jobPosting.setWorkType(workType);
	    jobPosting.setPostGradu(convertPostGradu(postGradu)); // enum으로 변환
	    jobPosting.setTitle(title);
	    jobPosting.setPostDescription(postDescription);
	    jobPosting.setPostTechStack(postTechStack);
	    jobPosting.setLocation(location);
	    jobPosting.setPostSalary(postSalary);
	    jobPosting.setRegdate(LocalDate.parse(regdate));
	    jobPosting.setDeadline(LocalDate.parse(deadline));
	    jobPosting.setStatus(convertStatus(status)); // enum으로 변환

	    ijp.updateJobPosting(jobPosting);

	    return "redirect:/viewlist";
	}

    @RequestMapping("/delete")
    public String delete(@RequestParam("postNo") int postNo) {
        ijp.deleteJobPosting(postNo);
        return "redirect:/viewlist";
    }
    
    @RequestMapping("/bookMarkedCompany")
    public @ResponseBody String bookMarkedCompany(@RequestParam("comName") String comName, @RequestParam("userNo") int userNo) {
    	 try {
    	        boolean isBookmarked = ijp.isBookmarked(comName, userNo);

    	        if (isBookmarked) {
    	            ijp.deleteBookmark(comName, userNo);
    	            return "북마크가 취소되었습니다.";
    	        } else {
    	            ijp.addBookmark(comName, userNo);
    	            return "북마크 되었습니다.";
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	        return "북마크 처리 중 오류가 발생했습니다.";
    	    }
    }
}