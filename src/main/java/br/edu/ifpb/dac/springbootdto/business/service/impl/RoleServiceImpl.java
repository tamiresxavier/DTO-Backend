package br.edu.ifpb.dac.springbootdto.business.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.springbootdto.business.service.RoleService;
import br.edu.ifpb.dac.springbootdto.model.entity.Role;
import br.edu.ifpb.dac.springbootdto.model.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository repository;
	

	public void createDefaultValues() {
		for (AVAILABLE_ROLES availableRole : AVAILABLE_ROLES.values()) {
		            Role role = findByName(availableRole.name());
		            if (role == null) {
		                role = new Role();
		                role.setName(availableRole.name());
		                repository.save(role);
		            }
		      }
	}


	@Override
	public Role findByName(String name) {
		 if (name == null) {
	            throw new IllegalStateException("Name Cannot be null");
	        }
	        Optional<Role> optional = repository.findByName(name);
	        return optional.isPresent()? optional.get():null;
	}


	@Override
	public Role findDefault() {
		 return findByName(AVAILABLE_ROLES.USER.name());
	}
}