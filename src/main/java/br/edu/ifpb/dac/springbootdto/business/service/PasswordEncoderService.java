package br.edu.ifpb.dac.springbootdto.business.service;


import org.springframework.security.crypto.password.PasswordEncoder;

import br.edu.ifpb.dac.springbootdto.model.entity.Employee;

public interface PasswordEncoderService extends PasswordEncoder{
	
	void encryptPassword (Employee employee);

}