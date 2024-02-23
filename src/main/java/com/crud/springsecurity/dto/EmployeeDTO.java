package com.crud.springsecurity.dto;

import com.crud.springsecurity.model.Employee;
import com.crud.springsecurity.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

	public EmployeeDTO(Employee save) {}
	private int eId;
	
	private String fullName;


	private String email;
	
	private String  password;
	
	private String phone;
	
	private UserRole userRole;
}
