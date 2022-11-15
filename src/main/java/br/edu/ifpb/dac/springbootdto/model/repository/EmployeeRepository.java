package br.edu.ifpb.dac.springbootdto.model.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.springbootdto.model.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long> {
	
	List<Employee> findByName(String name);

    List<Employee> findByEmail(String email);
	
}
