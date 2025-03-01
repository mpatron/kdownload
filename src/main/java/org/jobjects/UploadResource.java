package org.jobjects;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import io.quarkiverse.kerberos.KerberosPrincipal;
import io.quarkus.logging.Log;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

/**
 * A simple resource for creating a greeting.
 *
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
@Path("/api")
@Authenticated
public class UploadResource {

  @Inject
  KerberosConfig config;
  @Inject
  SecurityIdentity identity;
  @Inject
  KerberosPrincipal kerberosPrincipal;

  @POST
  @Path("upload")
  public void multipart(@RestForm String description, @RestForm("data") FileUpload file) {
    Utils.affiche();
    String principaleName = identity.getPrincipal().getName();
    String realmName = kerberosPrincipal.getRealm();
    String destFilePath = String.format(config.http().body().uploadsDirectory() + "/tgt-%s-%s.keytab", principaleName, realmName);
    Log.info(String.format("fileName=%s", destFilePath));
    File source = file.uploadedFile().toFile();
    File dest = new File(destFilePath);
    try {
      FileUtils.copyFile(source, dest);
    } catch (IOException e) {
      e.printStackTrace();
      Log.error(e.getLocalizedMessage(), e);
    }
  }
}
