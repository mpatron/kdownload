package org.jobjects;

import io.quarkiverse.kerberos.KerberosPrincipal;
import io.quarkus.logging.Log;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/api/users")
@Authenticated
public class UsersResource {

	@Inject
	SecurityIdentity identity;
	@Inject
	KerberosPrincipal kerberosPrincipal;

	@GET
	@Path("/me")
	@Produces("text/plain")
	public String me() {
		Utils.affiche();
		String returnValue = identity.getPrincipal().getName();
		Log.info("/api/users/me: " + returnValue);
		return returnValue;
	}
}
