package br.edu.ifpb.dac.springbootdto.presentation.dto;

import java.util.List;
import java.util.stream.Collectors;
import br.edu.ifpb.dac.springbootdto.model.entity.Department;
import br.edu.ifpb.dac.springbootdto.model.entity.Employee;

public class EmployeeDTO {
	private Long id;
	private String name;
	private String lastName;
	private String office;
	private Long departmentCode;
	 
	public EmployeeDTO() {

	}

	public EmployeeDTO(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
		this.lastName = employee.getLastName();
		this.office = employee.getOffice();
		this.departmentCode = employee.getDepartment().getCode();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public Long getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(Long departmentCode) {
		this.departmentCode = departmentCode;
	}

	public static List<EmployeeDTO> converter(List<Employee> employees) {
		return employees.stream().map(EmployeeDTO::new).collect(Collectors.toList());
	}

}
