package com.crud.springsecurity.service;

import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import com.crud.springsecurity.model.Employee;

import io.jsonwebtoken.Claims;

public interface JwtService {

	String extractUsername(String token);

	boolean isValid(String token, UserDetails user);

	<T> T extractClaim(String token, Function<Claims, T> resolver);

	String generateToken(Employee employee);
}
