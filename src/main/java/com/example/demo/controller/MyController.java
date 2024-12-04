package com.example.demo.controller;

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
import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {

	@Autowired
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
}