package br.edu.ifpb.dac.springbootdto.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.springbootdto.model.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long> {
	
}
