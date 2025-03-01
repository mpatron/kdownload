package org.jobjects;

import io.quarkiverse.kerberos.KerberosPrincipal;
import io.quarkus.logging.Log;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/users")
@Authenticated
public class UsersResource {

	@Inject
	SecurityIdentity identity;
	@Inject
	KerberosPrincipal kerberosPrincipal;

	@GET
	@Path("/me")
	@Produces(APPLICATION_JSON)
	public Response me() {
		IdentityKerberos identityKerberos = null;
		try {
			Utils.affiche();
			String principalName = identity.getPrincipal().getName();
			String fullName = kerberosPrincipal.getFullName();
			String realm = kerberosPrincipal.getRealm();
			identityKerberos = new IdentityKerberos(principalName, fullName, realm);
			Log.info("UsersResource => /api/users/me: " + identityKerberos);
		} catch (Throwable e) {
			Log.fatal(e.getMessage(), e);
		}
		Log.info(String.format("ReturnValue" + "=%s", identityKerberos));
		return Response.ok(identityKerberos).build();
	}
}
