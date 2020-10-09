import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.*;

import org.jboss.logging.Logger;

import io.vertx.core.http.HttpServerRequest;

 
public class HttpRequestLoggingFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(HttpRequestLoggingFilter.class);

    @Context
    UriInfo info;

    @Context
    HttpServerRequest request;

    @Override
    public void filter(ContainerRequestContext context) {
        SecurityContext sContext = context.getSecurityContext();
        final String method = context.getMethod();
        final String path = info.getPath();
        final String params = request.params().toString();

        Map<String, String> logForRequestMap = new HashMap<>();

        logForRequestMap.put("Method", method);
        logForRequestMap.put("Ruta", path);
        if(!params.isBlank() && !params.isEmpty()) logForRequestMap.put("Params", params);
        if(sContext.getUserPrincipal() != null) logForRequestMap.put("Request User", sContext.getUserPrincipal().getName());
        
        LOG.infof("\n[%s]  %s - \n params: %s", method, path, params);
    }
}