//package com.crud.springsecurity.controller;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.crud.springsecurity.dto.EmployeeDTO;
//
//@Controller
//@RequestMapping("/home")
//public class SecurityController {
//
//	@GetMapping("/start")
//	public String start() {
//		return "Hello Our Spring Security Session is Start !!! ";
//	}
//
//	@GetMapping("/end")
//	public String end() {
//		return "Hello Our Spring Security Session is End !!!";
//	}
//
//	@GetMapping("/register")
//	public ModelAndView getRegistration(@RequestBody EmployeeDTO employeeDTO) {
//		ModelAndView andView=new ModelAndView("Registration");
//		return andView;
//	}
//	
//	@GetMapping("/login")
//	public ModelAndView getLogin(@RequestBody EmployeeDTO employeeDTO) {
//		ModelAndView andView=new ModelAndView("");
//		return andView;
//	}
//	
//
//	@GetMapping("/user")
//	public ModelAndView user() {
//		ModelAndView mav = new ModelAndView("user_dashboard");
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	    mav.addObject("username", auth.getName());
//	    System.out.println(auth.getName());
//	    mav.addObject("roles", auth.getAuthorities());
//	    System.out.println(auth.getAuthorities());
//		return mav;
//	}
//
//	@GetMapping("/admin")
//	public ModelAndView admin() {
//		ModelAndView mav = new ModelAndView("admin_dashboard");
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	    mav.addObject("username", auth.getName());
//	    System.out.println(auth.getName());
//	    mav.addObject("roles", auth.getAuthorities());
//	    System.out.println(auth.getAuthorities());
//		return mav;
//	}
//
//	@GetMapping("/error")
//	public ModelAndView error() {
//		ModelAndView mav = new ModelAndView("error");
//		return mav;
//	}
//
//	
//	
//	
//	
//	
////	@GetMapping("/admin")
////	@PreAuthorize("hasRole('ROLE_ADMIN')")
////	public String adminDashboard(Authentication authentication) {
////		if (authentication != null) {
////	        String userName = authentication.getName();
////	        return "admin_dashboard" + userName;
////	    } else {
////	        return "Authentication object is null";
////	    }
////	}
////
////	@GetMapping("/user")
////	@PreAuthorize("hasRole('ROLE_ADMIN','ROLE_USER')")
////	public String userDashboard(Authentication authentication ) {
////		if (authentication != null) {
////	        String userName = authentication.getName();
////	        return "user_dashboard" + userName;
////	    } else {
////	        return "Authentication object is null";
////	    }
////	}
//
//}
