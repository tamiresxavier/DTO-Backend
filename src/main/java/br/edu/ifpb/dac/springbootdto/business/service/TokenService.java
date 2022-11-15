package br.edu.ifpb.dac.springbootdto.business.service;

import javax.servlet.http.HttpServletRequest;

import br.edu.ifpb.dac.springbootdto.model.entity.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

public interface TokenService {
	
	String generate(Employee employee);

    Claims getClaims(String token) throws ExpiredJwtException;

    boolean isValid(String token);

    String getUsername(String token);

    long getUserId(String token);

    String get(HttpServletRequest request);
		
}