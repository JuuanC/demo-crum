package persistence;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import model.Hospital;

@ApplicationScoped
public class HospitalRepository implements PanacheRepository<Hospital> {
	
	@Inject
	private EntityManager em;
	public List<Hospital> findByAlikeName(String nameSection) {
		return em.createQuery("FROM hospital WHERE nameHospital LIKE :nameHospital", Hospital.class)
				.setParameter("nameHospital", "%"+nameSection+"%").getResultList();
	}
}
