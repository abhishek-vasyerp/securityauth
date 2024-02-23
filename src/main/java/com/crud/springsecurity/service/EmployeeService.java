package com.crud.springsecurity.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.crud.springsecurity.dto.EmployeeDTO;
import com.crud.springsecurity.model.Employee;

public interface EmployeeService extends UserDetailsService {
	
	
	  void registerEmployee(EmployeeDTO employeeDTO);

	   Employee findUserByEmail(String email);

}
