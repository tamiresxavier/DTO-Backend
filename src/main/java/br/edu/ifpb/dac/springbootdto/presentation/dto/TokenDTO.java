package br.edu.ifpb.dac.springbootdto.presentation.dto;

public class TokenDTO {
	
    private String token;
    private EmployeeDTO user;
    
    public TokenDTO(String token, EmployeeDTO userDTO) {
        this.token = token;
        this.user = userDTO;
    }
    public TokenDTO() {
    }
    
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public EmployeeDTO getUser() {
		return user;
	}
	public void setUser(EmployeeDTO user) {
		this.user = user;
	}
    
}