package com.crud.springsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDTO {

	public ResponseDTO(int i, String string, String message) {}

	private int statusCode;

	private String error;

	private Object data;
}
