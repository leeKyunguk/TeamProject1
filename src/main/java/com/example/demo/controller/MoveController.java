package com.example.demo.controller;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.ICompany;
import com.example.demo.dao.IJobPosting;
import com.example.demo.dto.Applicant;
import com.example.demo.dto.JobPosting;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import jakarta.servlet.http.HttpSession;

@Controller
public class MoveController {

	@Autowired
	IJobPosting ijp;
	@Autowired
	ICompany ic;

	// 채용공고 페이지 요청
	@GetMapping("/viewJobsPostings")
	public String viewJobsPostingsPage(Model model) {
		List<JobPosting> list = ijp.getList();

		model.addAttribute("list", list);
		
		return "viewJobsPostings";
	}

	@RequestMapping("/reglist")
	public String reglist(HttpSession session, Model model) {
	    String usersId = (String) session.getAttribute("usersId");
	    String comName = (String) session.getAttribute("comName");
	    
	    System.out.println(usersId);
	    System.out.println(comName);

	    model.addAttribute("comName", comName);
	    model.addAttribute("usersId", usersId);
	    session.setAttribute("usersId", usersId);
	    session.setAttribute("comName", comName);
	    

	    return "reglist";
	}

	@RequestMapping("/regist")
	public String regist(@RequestParam("postNo") int postNo, @RequestParam("comName") String comName,
			@RequestParam("managerName") String managerName, @RequestParam("usersId") String usersId,
			@RequestParam("postExperience") String postExperience, @RequestParam("workType") String workType,
			@RequestParam("postGradu") String postGradu, @RequestParam("title") String title,
			@RequestParam("postDescription") String postDecription, @RequestParam("postTechStack") String postTechStack,
			@RequestParam("location") String loacation, @RequestParam("postSalary") int postSalary,
			@RequestParam("regdate") String regdate, @RequestParam("deadline") String deadline,
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
		return "redirect:/viewJobsPostings";
	}

	private JobPosting.PostGradu convertPostGradu(String postGradu) {
		switch (postGradu) {
		case "고등학교졸업":
			return JobPosting.PostGradu.고등학교졸업;
		case "전문대학졸":
			return JobPosting.PostGradu.전문대학졸;
		case "대졸":
			return JobPosting.PostGradu.대졸;
		default:
			throw new IllegalArgumentException("Unknown graduation level: " + postGradu);
		}
	}

	private JobPosting.Status convertStatus(String status) {
		switch (status) {
		case "모집중":
			return JobPosting.Status.모집중;
		case "마감":
			return JobPosting.Status.마감;
		default:
			throw new IllegalArgumentException("Unknown graduation level: " + status);
		}
	}

	@RequestMapping("/viewlist")
	public String viewlist() {
		return "viewlist";
		
	}
	
	@RequestMapping("/viewJobsPostings")
	public String list(Model model) {
		List<JobPosting> list = ijp.getList();

		model.addAttribute("list", list);

		return "viewJobsPostings";
	}

	@RequestMapping("/jobPosting/detail/{postNo}")
	public String detail(@PathVariable("postNo") int postNo, @RequestParam("userNo") int userNo, HttpSession session, Model model) {
		Applicant app = new Applicant();
		app.setUserNo(userNo);
		app.setPostNo(postNo);
		Applicant applicated = ijp.getApplicantByPostNoAndUserNo(app);
		Applicant applicant = (Applicant) session.getAttribute("applicant");
	    JobPosting jobPosting = ijp.getJobPostingByPostNo(postNo);
	    System.out.println(applicated);
	    model.addAttribute("jobPosting", jobPosting);
	    model.addAttribute("applicant", applicated);
	    return "detail";
	}
	
	@RequestMapping("/showApplicant")
	public String applicantlist(@RequestParam("postNo") int postNo, Model model) {
	    List<Applicant> applist = ijp.getapplicantlist(postNo);
	    model.addAttribute("applist", applist);
	    return "showApplicant";
	}

	@RequestMapping("/appliedJobList")
	public String getAppliedList(@RequestParam("userNo") int userNo, Model model) {
		Applicant app = new Applicant();
		app.setUserNo(userNo);
		List<Applicant> appliedList = ijp.appliedJobList(app);
		model.addAttribute("appliedList", appliedList);
		return "appliedJobList";
	}
	
	@RequestMapping("/applicate")
	public String applicatePost(@RequestParam("postNo") int postNo, @RequestParam("userNo") int userNo, HttpSession session, Model model) {
		Applicant applicant = new Applicant();
		applicant.setPostNo(postNo);
		applicant.setUserNo(userNo);
		ijp.applicatePost(applicant);

		session.setAttribute("applicant", applicant);
		
		List<JobPosting> list = ijp.getList();
		model.addAttribute("list", list);
		return "viewJobsPostings";
	}
	
	@RequestMapping("delApplicant")
	public String deleteApplicant(@RequestParam("postNo") int postNo, @RequestParam("userNo") int userNo, Model model) {
		Applicant applicant = new Applicant();
		applicant.setPostNo(postNo);
		applicant.setUserNo(userNo);
		ijp.delApplicant(applicant);
		
		List<JobPosting> list = ijp.getList();
		model.addAttribute("list", list);
		return "viewJobsPostings";
	}


	@RequestMapping("/update")
	public String update(@RequestParam("postNo") int postNo, @RequestParam("comName") String comName,
			@RequestParam("managerName") String managerName, @RequestParam("usersId") String usersId,
			@RequestParam("postExperience") String postExperience, @RequestParam("workType") String workType,
			@RequestParam("postGradu") String postGradu, @RequestParam("title") String title,
			@RequestParam("postDescription") String postDescription,
			@RequestParam("postTechStack") String postTechStack, @RequestParam("location") String location,
			@RequestParam("postSalary") int postSalary, @RequestParam("regdate") String regdate,
			@RequestParam("deadline") String deadline, @RequestParam("status") String status) {

		int comNo = ic.getComNoByComName(comName);
		// JobPosting 객체 생성 및 값 설정
		JobPosting jobPosting = new JobPosting();
		jobPosting.setPostNo(postNo);
		jobPosting.setComNo(comNo);
		jobPosting.setManagerName(managerName);
		jobPosting.setUsersId(usersId);
		jobPosting.setPostExperience(postExperience);
		jobPosting.setWorkType(workType);
		jobPosting.setPostGradu(convertPostGradu(postGradu));
		jobPosting.setTitle(title);
		jobPosting.setPostDescription(postDescription);
		jobPosting.setPostTechStack(postTechStack);
		jobPosting.setLocation(location);
		jobPosting.setPostSalary(postSalary);
		jobPosting.setRegdate(LocalDate.parse(regdate));
		jobPosting.setDeadline(LocalDate.parse(deadline));
		jobPosting.setStatus(convertStatus(status));

		ijp.updateJobPosting(jobPosting);

		return "redirect:/viewJobsPostings";
	}

    @RequestMapping("/delete")
    public String delete(@RequestParam("postNo") int postNo) {
        ijp.deleteJobPosting(postNo);
        System.out.println(postNo+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return "redirect:/viewJobsPostings";
    }

}