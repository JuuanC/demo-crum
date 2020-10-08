package dto;

import java.util.Set;

public class AccountUpdateDTO {

	public Long id_account;
	public String name;
	public String rfc;
	public String username;
	public String password;
	public Set<String> roles;
	


	public AccountUpdateDTO() {
		
	}
	public AccountUpdateDTO(Long id_account, String name, String rfc, String username, String password, Set<String> roles) {
		super();
		this.id_account = id_account;
		this.name = name;
		this.rfc = rfc;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	

	public Long getId_account() {
		return id_account;
	}


	public void setId_account(Long id_account) {
		this.id_account = id_account;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRfc() {
		return rfc;
	}


	public void setRfc(String rfc) {
		this.rfc = rfc;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Set<String> getRoles() {
		return roles;
	}


	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
}
