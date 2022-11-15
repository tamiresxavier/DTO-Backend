package br.edu.ifpb.dac.springbootdto.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.springbootdto.business.service.impl.PasswordEncorderServiceImpl;
import br.edu.ifpb.dac.springbootdto.business.service.impl.RoleServiceImpl;
import br.edu.ifpb.dac.springbootdto.model.entity.Employee;
import br.edu.ifpb.dac.springbootdto.model.entity.Role;
import br.edu.ifpb.dac.springbootdto.model.repository.EmployeeRepository;

@Service
public class EmployeeService implements UserService {
	
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RoleServiceImpl roleServiceImpl;
	
	@Autowired
	private PasswordEncorderServiceImpl passwordEncorderServiceImpl;
	
	@Override
	public Employee save(Employee employee) {
		if(employee.getId() != null){
			throw new IllegalStateException("Onwer exist");
		}
		passwordEncorderServiceImpl.encryptPassword(employee);
		
		List<Role> roles = new ArrayList<>();
		roles.add(roleServiceImpl.findDefault());
		employee.setRoles(roles);;
		return employeeRepository.save(employee);
	}
	
	
	public void delete (Long id) {
		employeeRepository.deleteById(id);
	}
	
	public Employee update (Employee owner) {
		if(owner.getId() == null){
			throw new IllegalStateException("Id is empty");
		}
		passwordEncorderServiceImpl.encryptPassword(owner);
		return employeeRepository.save(owner);
	}
	
	public List<Employee> find(Employee filter) {
		Example<Employee> example = Example.of(filter, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return employeeRepository.findAll(example);
	}
	
	public List<Employee> findAll () {
		return (List<Employee>) employeeRepository.findAll();
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = findByEmail(username);
		if(employee == null){
			throw new UsernameNotFoundException(String.format("Could not find any use with usename %s", username));
		}
		
		return employee;
	}


	@Override
	public Employee findById(Long id) {
		Employee entity = employeeRepository.findById(id).get();
		return entity;
	}


	@Override
	public Employee findByEmail(String email) {
		List<Employee> user = employeeRepository.findByEmail(email);
		return (Employee) user.get(0);
	}


	@Override
	public Employee findByUseName(String name) {
		List<Employee> user = employeeRepository.findByName(name);
		return (Employee) user.get(0);
	}

}
