package br.edu.ifpb.dac.springbootdto.business.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.springbootdto.business.service.PasswordEncoderService;
import br.edu.ifpb.dac.springbootdto.model.entity.Employee;

@Service
public class PasswordEncorderServiceImpl extends BCryptPasswordEncoder  implements PasswordEncoderService{

	@Override
	public void encryptPassword(Employee employee) {
		if(employee.getPassword() != null) {
			String encryptedPassword = encode(employee.getPassword());
			employee.setPassword(encryptedPassword);
		}
	}

}