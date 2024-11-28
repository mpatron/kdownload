package org.jobjects;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.util.logging.Logger;

import io.quarkiverse.kerberos.KerberosPrincipal;

import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;

@Path("/api/users")
@Authenticated
public class UsersResource {

  private static final Logger LOG = Logger.getLogger(UsersResource.class.getName());

  @Inject
  SecurityIdentity identity;
  @Inject
  KerberosPrincipal kerberosPrincipal;

  @GET
  @Path("/me")
  @Produces("text/plain")
  public String me() {
    String returnValue = identity.getPrincipal().getName();
    LOG.info("/api/users/me: " + returnValue);
    return returnValue;
  }
}
