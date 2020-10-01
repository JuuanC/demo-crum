package service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import model.Role;
import persistence.RoleRepository;
import security.enums.RoleEnum;

@ApplicationScoped
public class RoleService {
	@Inject
	RoleRepository roleRepository;
	
	@Transactional
	public void save(Role role) {
		roleRepository.persist(role);
	}
	
	public Role getRoleById(Long id) {
		return roleRepository.findById(id);
	}
	public Role getRoleByName(RoleEnum role) {
		return roleRepository.findRoleByName(role);
	}
}
