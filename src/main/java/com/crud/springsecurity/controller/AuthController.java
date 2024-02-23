package com.crud.springsecurity.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.crud.springsecurity.dto.EmployeeDTO;
//import com.crud.springsecurity.service.EmployeeService;
//
//@Controller


//@RestController
//public class AuthController {
//
//	@GetMapping("/users")
//	  public String h()
//	  {
//		  return "I am Users";
//	  }
//	@Autowired
//	private EmployeeService employeeService;
//
//	// handler method to handle home page request
//	@GetMapping("/")
//	public ModelAndView home() {
//		ModelAndView andView=new ModelAndView("home");
//		return andView;
//	}
//
//	// handler method to handle login request
//	@GetMapping("/login")
//	public String login() {
//		return "login";
//	}
//
//	// handler method to handle user registration form request
//	@GetMapping("/register")
//	public ModelAndView showRegistrationForm() {
//		ModelAndView andView = new ModelAndView("register");
//		return andView;
//	}
//
//	// handler method to handle user registration 
//	@PostMapping("/register/save")
//	public String showRegistrationForm(@ModelAttribute EmployeeDTO employeeDTO) {
//		employeeService.registerEmployee(employeeDTO);
//		return "redirect:/login";
//	}
//
//	
//	// handle method to handle user
//	@GetMapping("/user")
//	public ModelAndView user() {
//		ModelAndView mav = new ModelAndView("user_dashboard");
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		mav.addObject("username", auth.getName());
//		System.out.println(auth.getName());
//		mav.addObject("roles", auth.getAuthorities());
//		System.out.println(auth.getAuthorities());
//		return mav;
//	}
//	
//	
//	//handle methods to handle admin
//	@GetMapping("/admin")
//	public ModelAndView admin() {
//		ModelAndView mav = new ModelAndView("admin_dashboard");
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		mav.addObject("username", auth.getName());
//		System.out.println(auth.getName());
//		mav.addObject("roles", auth.getAuthorities());
//		System.out.println(auth.getAuthorities());
//		return mav;
//	}
//
//	@GetMapping("/error")
//	public ModelAndView error() {
//		ModelAndView mav = new ModelAndView("error");
//		return mav;
//	}
//
//}
