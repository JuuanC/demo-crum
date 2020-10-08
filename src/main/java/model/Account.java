/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entidad del sistema encargada de recopilar la información basica de las cuentas de los usuarios del sistema, tales como:
 * <ul>
 * 	<li><code>id_account</code></li>
 * 	<li><code>name</code></li>
 * 	<li><code>rfc</code></li>
 * 	<li><code>username</code></li>
 * 	<li><code>password</code></li>
 * 	<li><code>roles</code></li>
 * </ul>
 * A su vez esta entidad esta mapeada a la base de datos como la tabla <code>account</code> relacionada de muchos 
 * a muchos con la tabla <code>roles</code>
 * 
 * @author Juan Carlos Dominguez
 * @author José Alberto Espinoza
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

	public Account() {
		
	}

	/**
	 * Constructor de la clase Account que inicializa todos los parametros de la misma
	 * @param id_account Id de la cuenta
	 * @param name	nombre del propietario de la cuenta
	 * @param rfc  rfc del propietario de la cuenta
	 * @param username nombre de usuario del propietario de la cuenta
	 * @param password contraseña de la cuenta
	 * @param roles un arreglo de roles asociados a la cuenta
	 */
	public Account(Long id_account, String name, String rfc, String username, String password, Set<Role> roles) {
		super();
		this.id_account = id_account;
		this.name = name;
		this.rfc = rfc;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	
	/**
	 * Sobrecarga al constructor, unicamente que este solo recibe los siguientes parametros a inicializar
	 * @param name	nombre del propietario de la cuenta
	 * @param rfc  rfc del propietario de la cuenta
	 * @param username nombre de usuario del propietario de la cuenta
	 * @param password contraseña de la cuenta
	 */
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
