package br.edu.ifpb.dac.springbootdto.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.springbootdto.business.service.AuthenticationService;
import br.edu.ifpb.dac.springbootdto.business.service.EmployeeService;
import br.edu.ifpb.dac.springbootdto.business.service.TokenService;
import br.edu.ifpb.dac.springbootdto.model.entity.Employee;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

		@Autowired
	    private EmployeeService employeeService;
	    @Autowired
	    private TokenService tokenService;
	    @Autowired
	    private AuthenticationManager authManager;

	    @Override
	    public String login(String username, String password) {
	        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	        Employee employee = employeeService.findByEmail(username);
	        return tokenService.generate(employee);
	    }

	    @Override
	    public Employee getLoggedUser() {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        return (Employee) auth.getPrincipal();
	    }
}
