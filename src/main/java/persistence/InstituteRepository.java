package persistence;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import model.Institution;

@ApplicationScoped
public class InstituteRepository implements PanacheRepository<Institution> {
	
}
