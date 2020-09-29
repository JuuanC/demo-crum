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
	@SequenceGenerator(name="accountSequence", sequenceName = "accountSequence", allocationSize = 1, initialValue = 1)
	private Long id_account;
	@Column
	private String name;
	@Column
	private String rfc;
	@Column
	private String username;
	@Column
	private String password;

}
