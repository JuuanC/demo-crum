/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dto.AccountUpdateDTO;
import model.Account;
import service.AccountService;

/**
 *
 * @author Juuan
 */
@RequestScoped
@Path("account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountController {
	@Inject
	private AccountService accountService;
	
	
	@POST
	@Path("/save")
	public boolean saveAccount(Account account) {
		return accountService.save(account);
	}
	
	
	@DELETE
	@RolesAllowed("Admin")
	@Path("/delete/{id}")
	public boolean deleteAccount(@PathParam("id") Long id) {
		return accountService.delete(id);
	}
	
	
	@PUT
	@RolesAllowed("Admin")
	@Path("/update")
	public int updateAccount(AccountUpdateDTO account) {
		return accountService.update(account);
	}
	
	@GET
	@RolesAllowed("Admin")
	@Path("/getAll")
	public List<Account> getAll(){
		return accountService.getAll();
	}
	
	@GET
	@RolesAllowed("Admin")
	@Path("/getById/{id}")
	public Account getById(@PathParam("id") Long id) {
		return accountService.getById(id);
	}
	
	
}
