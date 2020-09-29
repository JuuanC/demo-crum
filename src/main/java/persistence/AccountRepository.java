/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence;

import javax.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import model.Account;

/**
 *
 * @author Juuan
 */

@ApplicationScoped
public class AccountRepository implements PanacheRepository<Account>{
	

}
