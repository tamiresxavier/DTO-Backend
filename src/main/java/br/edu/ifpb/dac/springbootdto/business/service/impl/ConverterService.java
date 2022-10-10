package br.edu.ifpb.dac.springbootdto.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ifpb.dac.springbootdto.business.service.DepartmentService;
import br.edu.ifpb.dac.springbootdto.model.entity.Department;
import br.edu.ifpb.dac.springbootdto.model.entity.Employee;
import br.edu.ifpb.dac.springbootdto.presentation.dto.DepartmentDTO;
import br.edu.ifpb.dac.springbootdto.presentation.dto.EmployeeDTO;

@Service
public class ConverterService {
	
	@Autowired
	private DepartmentService departmentService;

	public List<EmployeeDTO> employeeToDto(List<Employee> employees) {
		List<EmployeeDTO> employeesDto = new ArrayList<>();

		for (Employee employee : employees) {
			EmployeeDTO dto = employeeToDto(employee);
			employeesDto.add(dto);
		}
		return employeesDto;
	}

	public EmployeeDTO employeeToDto(Employee employee) {

		EmployeeDTO dto = new EmployeeDTO(employee);
		
		dto.setId(employee.getId());
		dto.setName(employee.getName());
		dto.setOffice(employee.getOffice());
		dto.setDepartmentCode(employee.getDepartment().getCode());
		
		return dto;
	}

	public Employee dtoToEmployee(EmployeeDTO dto) {
		
		//Optional<Category> category = categoryService.findById(dto.getCategoryId()); 

		Employee employee = new Employee();
		
		employee.setId(dto.getId());
		employee.setName(dto.getLastName());
		employee.setOffice(dto.getOffice());
		employee.setDepartment((departmentService.findById(dto.getDepartmentCode())));
		
		return employee;
	}

	public List<DepartmentDTO> departmentToDto(List<Department> departments) {
		List<DepartmentDTO> departmentsDto = new ArrayList<>();

		for (Department department : departments) {
			DepartmentDTO dto = departmentToDto(department);
			departmentsDto.add(dto);
		}
		return departmentsDto;
	}

	public DepartmentDTO departmentToDto(Department department) {
		DepartmentDTO dto = new DepartmentDTO();
		
		dto.setCode(department.getCode());
		dto.setName(department.getName());

		return dto;
	}

	public Department dtoToDepartment(DepartmentDTO dto) {

		Department department = new Department();

		department.setCode(dto.getCode());
		department.setName(dto.getName());

		return department;
	}

}
