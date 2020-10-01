/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;


import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import dto.AccountUpdateDTO;
import io.quarkus.panache.common.Parameters;
import model.Account;
import persistence.AccountRepository;
import security.PBKDF2Encoder;

/**
 *
 * @author Juuan
 */

@ApplicationScoped
public class AccountService {
	
	@Inject
	PBKDF2Encoder passwordEncoder;
	
	@Inject
	private AccountRepository accountRepository;
	
	
	@Transactional
	public boolean save(Account account) {
		accountRepository.persist(account);
		return accountRepository.isPersistent(account);
	}
	
	@Transactional
	public boolean delete(Long id) {
		return accountRepository.deleteById(id);
	}
	
	@Transactional
	public int update(AccountUpdateDTO account) {
		String newPassword = account.getPassword();
		account.setPassword(passwordEncoder.encode(newPassword));
		
		return accountRepository.update("name=:name "+
										", rfc = :rfc " +
										", username = :username " +
										", password = :password WHERE id=:id",
										Parameters.with("name", account.getName())
										.and("rfc", account.getRfc())
										.and("username", account.getUsername())
										.and("password", account.getPassword())
										.and("id", account.getId_account())
										);
		
	}
	
	public List<Account> getAll() {
		return accountRepository.listAll();
	}
	
	public Account getById(Long id) {
		return accountRepository.findById(id);
	}
	
	public Account getByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

}
