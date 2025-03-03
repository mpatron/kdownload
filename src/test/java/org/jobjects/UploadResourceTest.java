package org.jobjects;

import static io.restassured.RestAssured.given;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import io.quarkiverse.kerberos.test.utils.KerberosTestClient;
import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class UploadResourceTest {

  @Inject
  KerberosConfig config;

  @Inject
  KerberosClientConfig configClient;

  public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
  public static final String NEGOTIATE = "Negotiate";

  @Test
  void testMultipart() throws Exception {
    Log.debug(String.format("quarkus.http.body.uploads-directory => %s", config.http().body().uploadsDirectory()));

    given().when().get("/api/users/me").then().assertThat().statusCode(401).and().header(WWW_AUTHENTICATE, NEGOTIATE);

    KerberosTestClient kerberosTestClient = new KerberosTestClient();
    var result = kerberosTestClient.get("/api/users/me", "bob", "bob");
    String chaine = result.extract().header(WWW_AUTHENTICATE);
    Log.info(String.format(WWW_AUTHENTICATE + "=%s", chaine));

    final byte[] bytes = IOUtils.toByteArray(getClass().getResourceAsStream("/application.properties"));

    given().header(WWW_AUTHENTICATE, chaine).multiPart("file", "myFile", bytes).expect().statusCode(200);
    Log.info(String.format("===== %s =====", chaine));
    String val = given().header(WWW_AUTHENTICATE, chaine).multiPart("file", "myFile", bytes).post().asString();
    Log.info(String.format("====> %s <=====", val));
  }
}
