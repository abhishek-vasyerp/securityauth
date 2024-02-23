package com.crud.springsecurity.service;


import com.crud.springsecurity.model.AuthenticationResponse;
import com.crud.springsecurity.model.Employee;

public interface AuthenticationService{

	AuthenticationResponse egister(Employee employee);
	
//	EmployeeDTO register(EmployeeDTO employeeDTO);
	
	AuthenticationResponse authenticate(Employee employee);
//	void registerEmployee(EmployeeDTO employeeDTO);

//	Employee findUserByEmail(String email);

	public Employee findUserByEmail(String email) ;
}
