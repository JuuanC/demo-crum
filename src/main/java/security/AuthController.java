package security;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;

import model.Account;
import model.Role;
import security.DTO.AuthRequest;
import security.DTO.AuthResponse;
import security.DTO.NewUser;
import security.enums.RoleEnum;
import service.AccountService;
import service.RoleService;


@Path("/auth")
public class AuthController {
	@Inject
	PBKDF2Encoder passwordEncoder;
	@Inject
	RoleService roleService;
	@Inject
	AccountService accountService;
	
	private static final Logger log = Logger.getLogger(AuthController.class);

	@ConfigProperty(name = "demo-crum.quarkusjwt.jwt.duration") public Long duration;
	@ConfigProperty(name = "mp.jwt.verify.issuer") public String issuer;
	
	@PermitAll
	@GET @Path("") @Produces(MediaType.APPLICATION_JSON)
	public Response prueba() {
		return Response.ok("Prueba de permit all").build();
	}
	
	@PermitAll
	@POST @Path("/register")
	public Response register(@RequestBody NewUser nuevoUsuario) {
		Account cuenta = new Account(nuevoUsuario.getName(), nuevoUsuario.getRfc(), nuevoUsuario.getUsername(), passwordEncoder.encode(nuevoUsuario.getPassword()));
		Set<Role> roles = new HashSet<>();
		log.info("Hasta aqui llegue " + RoleEnum.User);
		Role role = roleService.getRoleByName(RoleEnum.User);
		
		log.info("Hasta aqui llegue 2 " + role.getId());
		roles.add(role);
		log.info(nuevoUsuario.getRoles());
		if(nuevoUsuario.getRoles().contains("Admin")) {
			Role roleAdmin = roleService.getRoleByName(RoleEnum.Admin);
			log.info("Hasta aqui llegue 3 " + roleAdmin.getRole().toString());
			roles.add(roleAdmin);
		}
		log.info(roles.toString());
		cuenta.setRoles(roles);
		accountService.save(cuenta);
		return Response.ok().build();
	}

	@PermitAll
	@POST @Path("/login") @Produces(MediaType.APPLICATION_JSON)
	public Response login(@RequestBody AuthRequest authRequest) {
		Account u = accountService.getByUsername(authRequest.username);
		if (u != null && u.password.equals(passwordEncoder.encode(authRequest.password))) {
			try {
				return Response.ok(new AuthResponse(TokenUtils.generateToken(u.username, u.roles, duration, issuer))).build();
			} catch (Exception e) {
				return Response.status(Status.UNAUTHORIZED).build();
			}
		} else {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}
}
