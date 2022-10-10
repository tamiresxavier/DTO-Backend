package br.edu.ifpb.dac.springbootdto.presentation.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifpb.dac.springbootdto.model.entity.Department;

public class DepartmentDTO {
	
	private Long code;
	private String name;
	
	public DepartmentDTO() {
	}

	public DepartmentDTO(Department department) {
		this.code = department.getCode();
		this.name = department.getName();
	}

	public Long getCode() {
		return code;
	}
	
	public void setCode(Long code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public static List<DepartmentDTO> converter(List<Department> departments) {
		return departments.stream().map(DepartmentDTO::new).collect(Collectors.toList());
	}

}
