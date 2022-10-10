package br.edu.ifpb.dac.springbootdto.business.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.springbootdto.model.entity.Employee;
import br.edu.ifpb.dac.springbootdto.model.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Employee update(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Employee update(Long id) {
		Employee employeeSave = employeeRepository.getById(id);
		if(id == null) {
			throw new IllegalStateException("Id cannot be null");
		}		
		return employeeRepository.save(employeeSave);
	}
	
	public void delete(Long id) {
		
		Optional<Employee> employee = findById(id);
		
		if(employee == null) {
			throw new IllegalStateException(String.format("Could not find a entity with id=%1", id));
		}
		
		employeeRepository.deleteById(id);
	}
	
	public Optional<Employee> findById(Long id) {
		if(id == null) {
			throw new IllegalStateException("Id cannot be null");
		}
		
		return employeeRepository.findById(id);
	}
	
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public List<Employee> find(Employee filter) {
		Example example = Example.of(filter, ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return employeeRepository.findAll(example);
	}

}
