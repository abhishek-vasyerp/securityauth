package com.crud.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.springsecurity.dto.EmployeeDTO;
import com.crud.springsecurity.exception.EmployeeNotFoundException;
import com.crud.springsecurity.model.Employee;
import com.crud.springsecurity.model.UserRole;
import com.crud.springsecurity.repository.EmployeeRepository;
import com.crud.springsecurity.repository.UserRoleRepository;

@Service
public class EmployeeServiceimpl implements EmployeeService, UserDetailsService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public EmployeeServiceimpl(EmployeeRepository employeeRepository, UserRoleRepository userRoleRepository,
			PasswordEncoder passwordEncoder) {
		this.employeeRepository = employeeRepository;
		this.userRoleRepository = userRoleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void registerEmployee(EmployeeDTO employeeDTO) {

		Employee employee = new Employee();
		employee.setFullName(employeeDTO.getFullName());
		employee.setEmail(employeeDTO.getEmail());
		employee.setPhone(employeeDTO.getPhone());
		employee.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));

		System.out.println(employeeDTO.getUserRole());

		UserRole role = userRoleRepository.findByName(employeeDTO.getUserRole().getName());
		if (role != null) {
			employee.setUserRole(role);
			Employee save = employeeRepository.save(employee);
		}
	}

	@Override
	public Employee findUserByEmail(String email) {
		return employeeRepository.findByEmail(email)
				.orElseThrow(() -> new EmployeeNotFoundException("This Employee username does not exist: " + email));
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Employee employee = employeeRepository.findByEmail(email)
				.orElseThrow(() -> new EmployeeNotFoundException("This Employee username does not exist: " + email));
		return employee;
	}

}
