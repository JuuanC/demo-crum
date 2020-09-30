package security;

import javax.annotation.security.PermitAll;
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
import security.DTO.AuthRequest;
import security.DTO.AuthResponse;


@Path("/auth")
public class AuthController {
	@Inject
	PBKDF2Encoder passwordEncoder;
	private static final Logger log = Logger.getLogger(AuthController.class);

	@ConfigProperty(name = "demo-crum.quarkusjwt.jwt.duration") public Long duration;
	@ConfigProperty(name = "mp.jwt.verify.issuer") public String issuer;
	
	@PermitAll
	@GET @Path("") @Produces(MediaType.APPLICATION_JSON)
	public Response prueba() {
		return Response.ok("Prueba de permit all").build();
	}

	@PermitAll
	@POST @Path("/login") @Produces(MediaType.APPLICATION_JSON)
	public Response login(@RequestBody AuthRequest authRequest) {
		Account u = Account.findByUsername(authRequest.username);
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
