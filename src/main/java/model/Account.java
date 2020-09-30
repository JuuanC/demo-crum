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
import lombok.Data;
import security.enums.RoleEnum;

/**
 *
 * @author Juuan
 */
@Data
@Entity
@Table(name = "account")
public class Account{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountSequence")
	@SequenceGenerator(name="accountSequence", sequenceName = "accountSequence", allocationSize = 1, initialValue = 1)
	private Long id_account;
	@Column
	private String name;
	@Column
	private String rfc;
	@Column
	public String username;
	@Column
	public String password;
	
	@Column
	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
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

}
