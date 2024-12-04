package com.example.demo.controller;

<<<<<<< HEAD
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.ICompany;
import com.example.demo.dao.IJobPosting;
import com.example.demo.dto.JobPosting;

=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.IAdminDAO;
import com.example.demo.dao.IUsersDAO;
import com.example.demo.dto.Admin;
import com.example.demo.dto.Company;
import com.example.demo.dto.UserProfiles;
import com.example.demo.dto.UserProfiles.Gender;
import com.example.demo.dto.Users;
import com.example.demo.dto.Users.Role;

import jakarta.servlet.http.HttpServletRequest;
>>>>>>> 1e5976f2a04995f591b8d1818bad04e88c7095a0
import jakarta.servlet.http.HttpSession;

@Controller
public class MoveController {

	@Autowired
<<<<<<< HEAD
	IJobPosting ijp;
	@Autowired
	ICompany ic;
	
	@RequestMapping("/")
	public String main() {
		return "main";
	}
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

=======
	private IUsersDAO iusersdao;
	@Autowired
	private IAdminDAO iadmindao;
	
	@RequestMapping("/")
	public String root(HttpSession session, Model model) {
	    Role role = (Role) session.getAttribute("role");
	    if (role != null) {
	    	if (role == Role.구직자) {
	    		UserProfiles userProfiles = (UserProfiles) session.getAttribute("userProfiles");
	    		if (userProfiles != null) {
	    			model.addAttribute("userProfiles", userProfiles);
	    		}
	    	} else if (role == Role.기업) {
	    		Company company = (Company) session.getAttribute("company");
	    		if (company != null) {
	    			model.addAttribute("company", company);
	    		}
	    	}
	    }
	    return "/index";
	}
	
	@RequestMapping("/admin")
	public String adminpage() {
		return "admin";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam("usersId") String usersId, @RequestParam("password") String password, HttpServletRequest request, Model model) {
		Users result = iusersdao.userLogin(usersId, password);
		if (result != null) {
	        HttpSession session = request.getSession();
	        session.setAttribute("role", result.getRole());
	        if (result.getRole() == Role.구직자) {
	            UserProfiles userProfiles = iusersdao.userInfoLoad(usersId);
	            session.setAttribute("userProfiles", userProfiles);
	            model.addAttribute("userProfiles", userProfiles);
	        } else if (result.getRole() == Role.기업) {
	            Company company = iusersdao.comInfoLoad(usersId);
	            session.setAttribute("company", company);
	            model.addAttribute("company", company);
	        }
	        model.addAttribute("loginSuccess", "로그인 성공");
	        return "index";
	    } else {
	        model.addAttribute("loginFail", "아이디 또는 비밀번호가 잘못되었습니다.");
	        return "index";
	    }
	}
	
	@RequestMapping("/adminlogin")
	public String adminLogIn(@RequestParam("adminId") String adminId, @RequestParam("adminPw") String adminPw, HttpServletRequest request, Model model) {
		Admin result = iadmindao.adminLogin(adminId, adminPw);
		if(result != null) {
			HttpSession session = request.getSession();
			session.setAttribute("admin", result);
			String msg = "";
			model.addAttribute("loginSuccess", msg);
			return "index";
		} else {
			String msg = "";
			model.addAttribute("loginFail", msg);
			return "admin"; 
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		String msg= "";
		model.addAttribute("logoutmsg", msg);
		return "index";
	}
	
	@RequestMapping("/userDetail")
	public String userDetail(HttpSession session, Model model) {
		Role role = (Role) session.getAttribute("role");
		if (role != null) {
	        if (role == Role.구직자) {
	            UserProfiles userProfiles = (UserProfiles) session.getAttribute("userProfiles");
	            if (userProfiles != null) {
	                model.addAttribute("userProfiles", userProfiles);
	        	    return "userDetail";
	            }
	        } else if (role == Role.기업) {
	            Company company = (Company) session.getAttribute("company");
	            if (company != null) {
	                model.addAttribute("company", company);
	        	    return "companyDetail";
	            }
	        }
	    } else {
	        return "redirect:/login";
	    }
		return "/";
	}
	
	@RequestMapping("/modify")
	public String modify(HttpSession session, Model model) {
		Role role = (Role) session.getAttribute("role");
		if (role != null) {
	        if (role == Role.구직자) {
	            UserProfiles userProfiles = (UserProfiles) session.getAttribute("userProfiles");
	            if (userProfiles != null) {
	                model.addAttribute("userProfiles", userProfiles);
	        	    return "modifyUser";
	            }
	        } else if (role == Role.기업) {
	            Company company = (Company) session.getAttribute("company");
	            if (company != null) {
	                model.addAttribute("company", company);
	        	    return "modifyCompany";
	            }
	        }
	    } else {
	        return "redirect:/login";
	    }
		return "/";
	}
	
	@RequestMapping("/UserModify")
    public String modifyUser(@ModelAttribute UserProfiles userProfiles, HttpSession session, Model model) {        
        userProfiles.setUsersId(userProfiles.getUsersId());
        userProfiles.setPassword(userProfiles.getPassword());
        userProfiles.setUserName(userProfiles.getUserName());
        userProfiles.setNickname(userProfiles.getNickname());
        userProfiles.setResiNumber(userProfiles.getResiNumber());
        userProfiles.setRole(userProfiles.getRole());
        userProfiles.setPhone(userProfiles.getPhone());
        userProfiles.setEmail(userProfiles.getEmail());
        userProfiles.setAddress(userProfiles.getAddress());
        userProfiles.setGender(userProfiles.getGender());
        userProfiles.setTechStack(userProfiles.getTechStack());
        userProfiles.setUSalary(userProfiles.getUSalary());
        int rowsAffected = iusersdao.updateUserProfile(userProfiles);
        if (rowsAffected > 0) {
            UserProfiles updatedUserProfile = iusersdao.userInfoLoad(userProfiles.getUsersId());
            session.setAttribute("userProfiles", updatedUserProfile);
            model.addAttribute("userProfiles", updatedUserProfile);
        }
        
        return "/userDetail";
	}
	
	@RequestMapping("/CompanyModify")
    public String modifyCompany(@ModelAttribute Company company, HttpSession session, Model model) {        
        company.setUsersId(company.getUsersId());
        company.setPassword(company.getPassword());
        company.setComName(company.getComName());
        company.setCeoName(company.getCeoName());
        company.setBusinessNo(company.getBusinessNo());
        company.setTel(company.getTel());
        company.setEmail(company.getEmail());
        company.setAddress(company.getAddress());
        company.setComType(company.getComType());
        company.setComSector(company.getComSector());
        company.setEmployeeCount(company.getEmployeeCount());
        company.setSales(company.getSales());
        System.out.println(iusersdao.comInfoLoad(company.getUsersId()));
        int rowsAffected = iusersdao.updateCompany(company);
        if (rowsAffected > 0) {
            Company updatedCompany = iusersdao.comInfoLoad(company.getUsersId());
            session.setAttribute("company", updatedCompany);
            model.addAttribute("company", updatedCompany);
        }
        return "/companyDetail";
	}
>>>>>>> 1e5976f2a04995f591b8d1818bad04e88c7095a0
}