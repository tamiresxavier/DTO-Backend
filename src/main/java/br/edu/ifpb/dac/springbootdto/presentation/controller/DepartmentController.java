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
import br.edu.ifpb.dac.springbootdto.model.entity.Department;
import br.edu.ifpb.dac.springbootdto.presentation.dto.DepartmentDTO;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
	
	@Autowired
	private ConverterService converterService;
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping
	public ResponseEntity save(@RequestBody DepartmentDTO dto) {
		try {
			Department entity = converterService.dtoToDepartment(dto);
			entity = departmentService.save(entity);
			dto = converterService.departmentToDto(entity);
			
			return new ResponseEntity(dto, HttpStatus.CREATED);
			
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{code}")
	public ResponseEntity update(@PathVariable("code") Long code, @RequestBody DepartmentDTO dto) {
		try {
			dto.setCode(code);
			Department entity = converterService.dtoToDepartment(dto);
			entity = departmentService.update(entity);
			dto = converterService.departmentToDto(entity);
			
			return ResponseEntity.ok(dto);
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("{code}")
	public ResponseEntity delete(@PathVariable("code") Long code) {
		try {
			departmentService.delete(code);
			
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity find (
				@RequestParam(value = "code", required = false) Long code,
				@RequestParam(value = "name", required = false) String name
			) {
		
		try {
			
			Department filter = new Department();
			filter.setCode(code);
			filter.setName(name);
			
			List<Department> entities = departmentService.find(filter);
			List<DepartmentDTO> dtos = converterService.departmentToDto(entities);
			
			return ResponseEntity.ok(dtos);
			
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
