package service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import model.Hospital;
import model.Institution;
import persistence.HospitalRepository;
import persistence.InstituteRepository;

@ApplicationScoped
public class HospitalService {
	@Inject
	private HospitalRepository hospitalRep;
	
	@Inject
	private InstituteRepository instituteRep;
	
	public List<Hospital> listHospitals() {
		return hospitalRep.listAll();
	}
	
	public List<Hospital> listByAlikeName(String nameSection) {
		return hospitalRep.findByAlikeName(nameSection);
	}
	
	public Hospital getById(Long id) {
		return hospitalRep.findById(id);
	}
	
	@Transactional
	public void save(Hospital hospital, Long id) {
		Institution ins = instituteRep.findById(id);
		hospital.setInstitution(ins);
		hospitalRep.persist(hospital);
	}
}
