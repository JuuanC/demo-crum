package persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import model.Role;
import security.enums.RoleEnum;

@ApplicationScoped
public class RoleRepository implements PanacheRepository<Role>{
	
	@Inject
	private EntityManager em;
	
	public Role findRoleByName(RoleEnum role) {
		return em.createQuery("FROM role WHERE role = :role", Role.class)
				.setParameter("role", role).getSingleResult();
		
	}
}
