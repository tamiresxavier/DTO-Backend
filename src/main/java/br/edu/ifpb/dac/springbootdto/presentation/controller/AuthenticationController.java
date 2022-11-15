package br.edu.ifpb.dac.springbootdto.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import br.edu.ifpb.dac.springbootdto.business.service.AuthenticationService;
import br.edu.ifpb.dac.springbootdto.business.service.EmployeeService;
import br.edu.ifpb.dac.springbootdto.business.service.TokenService;
import br.edu.ifpb.dac.springbootdto.business.service.impl.ConverterService;
import br.edu.ifpb.dac.springbootdto.model.entity.Employee;
import br.edu.ifpb.dac.springbootdto.presentation.dto.EmployeeDTO;
import br.edu.ifpb.dac.springbootdto.presentation.dto.LoginDTO;
import br.edu.ifpb.dac.springbootdto.presentation.dto.TokenDTO;

@RestController
@RequestMapping("/api")
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
public class AuthenticationController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ConverterService converterService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO dto){
        try {
            String token = authService.login(dto.getUsername(), dto.getPassword());
            Employee entity = employeeService.findByEmail(dto.getUsername());
            EmployeeDTO userDTO = converterService.employeeToDto(entity);

            TokenDTO tokenDTO = new TokenDTO(token,userDTO);
            
            return new ResponseEntity(tokenDTO, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/isTokenValid")
    public ResponseEntity isTokenValid(@RequestBody TokenDTO dto){
        try {
            boolean isTokenValid = tokenService.isValid(dto.getToken());
            
            return new ResponseEntity(isTokenValid, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}