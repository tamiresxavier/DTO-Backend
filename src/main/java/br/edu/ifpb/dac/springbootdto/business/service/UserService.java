package br.edu.ifpb.dac.springbootdto.business.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import br.edu.ifpb.dac.springbootdto.model.entity.Employee;

public interface UserService extends UserDetailsService {
	
		public Employee save(Employee employee);
		
	    public Employee  update(Employee employee);

	    public void delete(Long id);

	    public Employee findById(Long userId);

	    public Employee findByEmail(String email);

	    public Employee findByUseName(String name);

	    public List<Employee> findAll();

	    public List<Employee> find(Employee filter);
	    
}