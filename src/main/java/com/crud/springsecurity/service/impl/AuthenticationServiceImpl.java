package com.crud.springsecurity.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.springsecurity.exception.EmployeeNotFoundException;
import com.crud.springsecurity.model.AuthenticationResponse;
import com.crud.springsecurity.model.Employee;
import com.crud.springsecurity.model.UserRole;
import com.crud.springsecurity.repository.EmployeeRepository;
import com.crud.springsecurity.repository.UserRoleRepository;
import com.crud.springsecurity.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public AuthenticationResponse egister(Employee request) {
		Employee employee = new Employee();
		employee.setFullName(request.getFullName());
		employee.setEmail(request.getEmail());
		employee.setPassword(passwordEncoder.encode(request.getPassword()));
		employee.setPhone(request.getPhone());
		System.out.println(request.getUserRole());
		UserRole role = userRoleRepository.findByName(request.getUserRole().getName());
		if (role != null) {
			employee.setUserRole(role);
			Employee save = employeeRepository.save(employee);
			String token = jwtService.generateToken(save);
			return new AuthenticationResponse(token);
		} else {
			System.err.println("User role not found for name: " + request.getUserRole().getName());
			throw new EmployeeNotFoundException("userrole not found"); // or throw an exception
		}
	}

	@Override
	public Employee findUserByEmail(String email) {
		return employeeRepository.findByEmail(email)
				.orElseThrow(() -> new EmployeeNotFoundException("This Employee username does not exist: " + email));
	}

	@Override
	public AuthenticationResponse authenticate(Employee request) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (EmployeeNotFoundException e) {
			throw new EmployeeNotFoundException("Authentication failed. Invalid username or password.");
		}

		// If authentication is successful, proceed to generate token
		Employee employee = this.employeeRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with email: "));

		String token = jwtService.generateToken(employee);
		return new AuthenticationResponse(token);
	}
}
