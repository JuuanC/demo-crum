/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import model.Account;

/**
 *
 * @author Juuan
 */

@ApplicationScoped
public class AccountRepository implements PanacheRepository<Account>{
	@Inject
	EntityManager em;
	public Account findByUsername(String username) {
		return em.createQuery("FROM account WHERE username = :username", Account.class)
			.setParameter("username", username).getSingleResult();
		}

}
