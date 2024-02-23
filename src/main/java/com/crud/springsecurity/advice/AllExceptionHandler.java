package com.crud.springsecurity.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.crud.springsecurity.dto.ResponseDTO;
import com.crud.springsecurity.exception.EmployeeNotFoundException;

@ControllerAdvice
public class AllExceptionHandler {

	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseDTO employeeNotFoundException(EmployeeNotFoundException e)
	{
		return new ResponseDTO(404,"error",e.getMessage());
	}
}
