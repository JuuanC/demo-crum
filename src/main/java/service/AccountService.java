/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;


import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.quarkus.panache.common.Parameters;
import model.Account;
import persistence.AccountRepository;

/**
 *
 * @author Juuan
 */

@ApplicationScoped
public class AccountService {
	
	@Inject
	private AccountRepository accountRepository;
	
	public boolean save(Account account) {
		accountRepository.persist(account);
		return accountRepository.isPersistent(account);
	}
	
	public boolean delete(Long id) {
		return accountRepository.deleteById(id);
	}
	
	public int update(Account account) {
		return accountRepository.update("name=:name"+
										"and rfc = :rfc" +
										"and user = :user" +
										"and password = :password",
										Parameters.with("name", account.getName())
										.and("rfc", account.getRfc())
										.and("user", account.getUser())
										.and("password", account.getPassword()));
	}
	
	public List<Account> getAll() {
		return accountRepository.listAll();
	}
	
	public Account getById(Long id) {
		return accountRepository.findById(id);
	}

}
