package br.edu.ifpb.dac.springbootdto.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.springbootdto.model.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository <Department, Long> {
	
	Department findByName(String name);

}