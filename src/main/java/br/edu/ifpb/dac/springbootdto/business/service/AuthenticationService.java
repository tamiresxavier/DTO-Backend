package br.edu.ifpb.dac.springbootdto.business.service;

import br.edu.ifpb.dac.springbootdto.model.entity.Employee;

public interface AuthenticationService {

	public String login(String email, String password);
	
	public Employee getLoggedUser();
}