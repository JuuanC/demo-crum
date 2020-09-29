package service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import model.Institution;
import persistence.InstituteRepository;

@ApplicationScoped
public class InstitutionService {
	@Inject
	private InstituteRepository instituteRep;
	
	public List<Institution> listInstitutions() {
		
		return instituteRep.listAll();
	}
	
	@Transactional
	public void save(Institution ins) {
		instituteRep.persist(ins);
		
	}
	
	public Institution getById(Long id) {
		return instituteRep.findById(id);
	}
	
	
}
