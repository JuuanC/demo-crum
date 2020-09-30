/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

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
	@SequenceGenerator(name="accountSequence", sequenceName = "accountSequence")
	@JsonProperty(value = "id_account")
	private Long id_account;
	@Column
	@JsonProperty(value = "name")
	private String name;
	@Column
	@JsonProperty(value = "rfc")
	private String rfc;
	@Column
	@JsonProperty(value = "username")
	private String username;
	@Column
	@JsonProperty(value = "password")
	private String password;
	

}
