/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import security.enums.RoleEnum;

/**
 *
 * @author Juuan
 */

@Entity(name = "account")
@Table(name = "account")
public class Account{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountSequence")
	@SequenceGenerator(name="accountSequence", sequenceName = "accountSequence", allocationSize = 1, initialValue = 1)
	@JsonProperty(value = "id_account")
	public Long id_account;
	@Column
	@JsonProperty(value = "name")
	public String name;
	@Column
	@JsonProperty(value = "rfc")
	public String rfc;
	@Column
	@JsonProperty(value = "username")
	public String username;
	@Column
	@JsonProperty(value = "password")
	public String password;
	
	@Column
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
	public Set<Role> roles;
	
	
	
	public static Account findByUsername(String username) {
		Long id = (long) 1;
		
		
		String userUsername = "user";

		//generated from password encoder
		String userPassword = "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=";

		String adminUsername = "admin";

		//generated from password encoder
		String adminPassword = "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=";
		
		if (username.equals(userUsername)) {
			Set<Role> roles = new HashSet<>();
			roles.add(new Role((long)1, RoleEnum.User));
			return new Account(id, "", "", userUsername, userPassword, roles);
		} else if (username.equals(adminUsername)) {
			Set<Role> roles = new HashSet<>();
			roles.add(new Role((long)1, RoleEnum.Admin));
			return new Account(id, "", "", adminUsername, adminPassword, roles);
		} else {
			return null;
		}
	}


	public Account() {
		
	}
	public Account(Long id_account, String name, String rfc, String username, String password, Set<Role> roles) {
		super();
		this.id_account = id_account;
		this.name = name;
		this.rfc = rfc;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	
	public Account(String name, String rfc, String username, String password) {
		super();
		this.name = name;
		this.rfc = rfc;
		this.username = username;
		this.password = password;
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


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	


}
