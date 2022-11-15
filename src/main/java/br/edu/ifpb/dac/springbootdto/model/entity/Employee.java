package br.edu.ifpb.dac.springbootdto.model.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Employee implements Serializable, UserDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String office;
    private String email;
    private String password;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;
    
    @ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;

	public Employee() {
	}

	public Employee(Long id, String name, String lastName, String office, String email, String password,
			Department department, List<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.office = office;
		this.email = email;
		this.password = password;
		this.department = department;
		this.roles = roles;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {

		return roles;
	}

	public String getUsername() {

		return null;
	}

	public boolean isAccountNonExpired() {
	
		return true;
	}

	public boolean isAccountNonLocked() {
		
		return true;
	}

	public boolean isCredentialsNonExpired() {
	
		return true;
	}

	public boolean isEnabled() {
		
		return true;
	}
	
}