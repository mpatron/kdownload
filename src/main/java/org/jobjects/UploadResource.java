package org.jobjects;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import io.quarkiverse.kerberos.KerberosPrincipal;
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
public class UploadResource {

  private static final Logger LOG = Logger.getLogger(UploadResource.class.getName());

  @Inject
  SecurityIdentity identity;
  @Inject
  KerberosPrincipal kerberosPrincipal;

  @POST
  @Path("upload")
  public void multipart(@RestForm String description, @RestForm("data") FileUpload file) {
    String principaleName = identity.getPrincipal().getName();
    String destFilePath = String.format("/tmp/%s.keytab", principaleName);
    LOG.log(Level.INFO, String.format("fileName=%s", destFilePath));
    File source = file.uploadedFile().toFile();
    File dest = new File(destFilePath);
    try {
      FileUtils.copyFile(source, dest);
    } catch (IOException e) {
      e.printStackTrace();
      LOG.log(Level.SEVERE, e.getLocalizedMessage(), e);
    }
  }
}
