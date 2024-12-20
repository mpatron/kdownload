package org.jobjects;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import io.quarkus.logging.Log;

import java.util.Arrays;
import java.util.List;

import io.quarkiverse.kerberos.KerberosPrincipal;

import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;

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
