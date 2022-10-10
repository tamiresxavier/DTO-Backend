package br.edu.ifpb.dac.springbootdto.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.springbootdto.model.entity.Department;
import br.edu.ifpb.dac.springbootdto.model.repository.DepartmentRepository;


@Service
public class DepartmentService {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	public Department save(Department department) {
		return departmentRepository.save(department);
	}

	public Department update(Department department) {
		return departmentRepository.save(department);
	}

	public void delete(Long code) {
		departmentRepository.deleteById(code);
	}

	public Iterable<Department> findAll() {
		return departmentRepository.findAll();
	}
	
	public Department findById(Long code) {
		return departmentRepository.findById(code).get();
	}

	public Department findByName(String name) {
		return departmentRepository.findByName(name);
	}

	public List<Department> find(Department filter) {
		Example example = Example.of(filter, ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return departmentRepository.findAll(example);
	}

}
