package br.edu.ifpb.dac.springbootdto.business.service;

import br.edu.ifpb.dac.springbootdto.model.entity.Role;

public interface RoleService {
           
	public enum AVAILABLE_ROLES { ADMIN ,USER } 
	
	 public Role findByName(String name);
	 public Role findDefault();
}