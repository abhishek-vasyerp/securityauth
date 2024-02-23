package com.crud.springsecurity.controller;

import java.net.http.HttpClient.Redirect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.crud.springsecurity.dto.AuthRequest;
import com.crud.springsecurity.model.AuthenticationResponse;
import com.crud.springsecurity.model.Employee;
import com.crud.springsecurity.service.AuthenticationService;
import com.crud.springsecurity.service.EmployeeService;
import com.crud.springsecurity.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private EmployeeService employeeService;

	/*
	 * @PostMapping("/register") public ResponseEntity<AuthenticationResponse>
	 * register(@RequestBody Employee request) { return new
	 * ResponseEntity<>(authenticationService.egister(request), HttpStatus.OK); }
	 * 
	 * @PostMapping("/login") public ResponseEntity<AuthenticationResponse>
	 * login(@RequestBody Employee employee) { return new
	 * ResponseEntity<>(authenticationService.authenticate(employee),
	 * HttpStatus.OK); }
	 */

	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView andView = new ModelAndView("home");
		return andView;
	}

	// handler method to handle user registration form request
	@GetMapping("/register")
	public ModelAndView showRegistrationForm() {
		ModelAndView andView = new ModelAndView("register");
		return andView;
	}

	// handler method to handle user registration form request
	@GetMapping("/signin")
	public ModelAndView signIn() {
		ModelAndView andView = new ModelAndView("signin");
		return andView;
	}

	// handler method to handle user registration
	@PostMapping("/register/save")
	public String registerEmployee(@ModelAttribute("employee") Employee employee) {
		// Perform registration logic using the AuthenticationService
		AuthenticationResponse response = authenticationService.egister(employee);
		if (response != null) {
			return "redirect:/signin";
		} else {
			return "register";
		}
	}

	@PostMapping("/generateToken")
	public ModelAndView authenticateAndGetToken(AuthRequest authRequest, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("dashboard");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			ModelAndView object = modelAndView.addObject("data",
					jwtService.generateToken(employeeService.findUserByEmail(authRequest.getUsername())));
			modelAndView.addObject("Role",
					employeeService.loadUserByUsername(authRequest.getUsername()).getAuthorities());
			response.setHeader("Authorization", object.toString());
			return modelAndView;
		} else {
			modelAndView.addObject("error", "Invalid username or password");
			return modelAndView;
		}
	}


	@GetMapping("/admin-page")
//	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView admins(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		ModelAndView andView = new ModelAndView("admin_dashboard");
		andView.addObject("token", token);
		return andView;
	}

	@GetMapping("/user-page")
//	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ModelAndView users(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		ModelAndView andView = new ModelAndView("user_dashboard");
		andView.addObject("token", token);
		return andView;
	}

}
