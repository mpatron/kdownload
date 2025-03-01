package org.jobjects;

import io.quarkiverse.kerberos.KerberosPrincipal;
import io.quarkus.logging.Log;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/api/users")
@Authenticated
public class UsersResource {

	@Inject
	SecurityIdentity identity;
	@Inject
	KerberosPrincipal kerberosPrincipal;

	@GET
	@Path("/me")
	public IdentityKerberos me() {
		IdentityKerberos returnValue = null;
		try {
			Utils.affiche();
			String principalName = identity.getPrincipal().getName();
			String fullName = kerberosPrincipal.getFullName();
			String realm = kerberosPrincipal.getRealm();
			returnValue = new IdentityKerberos(principalName, fullName, realm);
			Log.info("UsersResource => /api/users/me: " + returnValue);
		} catch (Throwable e) {
			Log.fatal(e.getMessage(), e);
		}
		Log.info(String.format("ReturnValue" + "=%s", returnValue));
		return returnValue;
	}
}
