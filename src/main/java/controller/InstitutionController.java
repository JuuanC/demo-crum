package controller;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Institution;
import service.InstitutionService;

@RequestScoped
@Path("/instituciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InstitutionController {
	@Inject
	private InstitutionService institutionServ;
	
	@POST
	@Path("/data")
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveInstitution(Institution ins) {
		institutionServ.save(ins);
		return Response.ok().build();
	}
	
	@GET
	@Path("/data")
	@Produces(MediaType.APPLICATION_JSON)
	public Response all() {
		List<Institution> listInstitution = institutionServ.listInstitutions();
		return Response.ok(listInstitution).build();
	}
	
	@GET
	@Path("/data/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") Long id) {
		Institution ins = institutionServ.getById(id);
		return Response.ok(ins).build();
	}
	

}
