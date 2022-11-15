package br.edu.ifpb.dac.springbootdto.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.dac.springbootdto.business.service.impl.ConverterService;
import br.edu.ifpb.dac.springbootdto.business.service.DepartmentService;
import br.edu.ifpb.dac.springbootdto.business.service.EmployeeService;
import br.edu.ifpb.dac.springbootdto.model.entity.Department;
import br.edu.ifpb.dac.springbootdto.model.entity.Employee;
import br.edu.ifpb.dac.springbootdto.presentation.dto.EmployeeDTO;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

	@Autowired
	private ConverterService converterService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping
	public ResponseEntity save(@RequestBody EmployeeDTO dto) {

		try {
			
			if (dto.getDepartmentCode() == null) {
				throw new IllegalStateException("departmentCode cannot be null");
			}	
			
			Long departmentCode = dto.getDepartmentCode();
			Department department = departmentService.findById(departmentCode);
			
			if(department == null) {
				throw new IllegalStateException(String.format("Cound not find any department with id=%1", departmentCode));
			}
			
			Employee entity = converterService.dtoToEmployee(dto);
			entity.setDepartment(department);
			entity = employeeService.save(entity);
			dto = converterService.employeeToDto(entity);
			
			return new ResponseEntity(dto, HttpStatus.CREATED);
			
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody EmployeeDTO dto) {
		try {
			dto.setId(id);
			Long departmentCode = dto.getDepartmentCode();
			Department department = departmentService.findById(departmentCode);

			if (department == null) {
				throw new IllegalStateException(String.format("Cound not find any departament with id=%1", id));
			}

			Employee entity = converterService.dtoToEmployee(dto);
			entity.setDepartment(department);
			entity = employeeService.update(entity);
			dto = converterService.employeeToDto(entity);

			return ResponseEntity.ok(dto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		try {
			employeeService.delete(id);

			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity findByFilter(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "office", required = false) String office,
			@RequestParam(value = "departmentCod", required = false) Long departmentCode

	) {

		try {

			Employee filter = new Employee();
			filter.setId(id);
			filter.getName();
			filter.getLastName();
			filter.getOffice();

			Department department = departmentService.findById(departmentCode);

			if (department == null) {
				throw new IllegalStateException(String.format("Cound not find any department whit id=%1", departmentCode));
			}

			filter.setDepartment(department);

			List<Employee> entities = employeeService.find(filter);
			List<EmployeeDTO> dtos = converterService.employeeToDto(entities);

			return ResponseEntity.ok(dtos);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/all")
	public List<Employee> findAll() throws Exception {

		List<Employee> list = employeeService.findAll();

		if (list.isEmpty()) {
			throw new Exception("List is empty!");

		} else {
			return employeeService.findAll();
		}
	}

}
