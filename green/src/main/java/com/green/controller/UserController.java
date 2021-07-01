package com.green.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.green.oauth2.domain.SessionUser;

@RestController("/user")
=======
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.green.oauth2.domain.Role;
import com.green.oauth2.domain.SessionUser;
import com.green.security.domain.SecurityMemberVO;
import com.green.security.service.SecurityMemberService;
import com.green.service.PurchaseService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/user")
@Log4j
>>>>>>> 83885cc34a97bd644d770a917854ed94ed2d2895
public class UserController {
	
	@Autowired
	private HttpSession session;
	
<<<<<<< HEAD
	@GetMapping
	@ResponseBody
	public SessionUser getSessionUser() {
		System.out.println(session.getAttribute("user"));
		return (SessionUser)session.getAttribute("user");
	}

=======
	@Autowired
	private SecurityMemberService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping
	@ResponseBody
	public SessionUser getSessionUser() {
		log.info("UserController");
		System.out.println(session.getAttribute("user"));
		return (SessionUser)session.getAttribute("user");
	}
	
	@GetMapping("/check/{email}")
	@ResponseBody
	public Boolean checkIfExists(@PathVariable("email") String email) {
		return service.getMember(email) != null ? true: false;
	}
	
	@GetMapping("/login")
	public void getLoginPage() {}
	
	@GetMapping("/signup")
	public void getSignUpPage() {}
	
	@PostMapping("/signup")
	public String getSignUp(SecurityMemberVO memberVO) {
		
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		memberVO.setRole(Role.MEMBER);
		
		return "redirect:/";
	}
	
	@GetMapping("/info")
	public void getUserInfoPage() {}
	
	@PostMapping("/info")
	@ResponseBody
	public Boolean getUserIngo(@RequestParam("name") String name, @RequestParam("password") String password) {
		
		SessionUser sessionUser = (SessionUser) session.getAttribute("user");
		String userid = sessionUser.getUserid();
		if(name != null) {
			return service.updateName(name, userid);
		} 
		if (password != null) {
			
			String encodedPwd = passwordEncoder.encode(password);
			return service.updatePassword(encodedPwd, userid);
		}
		
		return false;
	}
>>>>>>> 83885cc34a97bd644d770a917854ed94ed2d2895
}
