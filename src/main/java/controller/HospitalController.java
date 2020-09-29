package controller;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Hospital;
import service.HospitalService;

@ApplicationScoped
@Path(value="/hospitales")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HospitalController {
	@Inject 
	private HospitalService hospitalServ;
	
	@POST
	@Path("/data/{institutionId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveHospital(@PathParam("institutionId") Long id, Hospital hospital) {
		hospitalServ.save(hospital, id);
		return Response.ok().build();
	}
	
	@GET
	@Path("/data")
	@Produces(MediaType.APPLICATION_JSON)
	public Response all() {
		List<Hospital> listHospital = hospitalServ.listHospitals();
		return Response.ok(listHospital).build();
	}
	@GET
	@Path("/data/{nameSection}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchByAlikeName(@PathParam("nameSection") String nameSection) {
		List<Hospital> listHospital = hospitalServ.listByAlikeName(nameSection);
		return Response.ok(listHospital).build();
	}
	
	@GET
	@Path("/data/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") Long id) {
		Hospital hospital = hospitalServ.getById(id);
		return Response.ok(hospital).build();
	}
}
