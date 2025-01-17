package org.jobjects;

import io.quarkiverse.kerberos.test.utils.KerberosTestClient;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import jakarta.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;


import io.quarkus.logging.Log;

@QuarkusTest
public class UploadResourceTest {
  @Inject
  KerberosConfig config;

  public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
  public static final String NEGOTIATE = "Negotiate";

  @Test
  void testMultipart() throws Exception {


    given().when().get("/api/users/me").then().assertThat().statusCode(401).and().header(WWW_AUTHENTICATE, NEGOTIATE);

    KerberosTestClient kerberosTestClient = new KerberosTestClient();
    var result = kerberosTestClient.get("/api/users/me", "bob", "bob");
    result.statusCode(200).body(Matchers.is("bob"));

    String chaine = result.extract().header(WWW_AUTHENTICATE);
    Log.info(String.format(WWW_AUTHENTICATE + "=%s", chaine));

    final byte[] bytes = IOUtils.toByteArray(getClass().getResourceAsStream("/application.properties"));

    given().header(WWW_AUTHENTICATE, chaine).multiPart("file", "myFile", bytes).expect().statusCode(200);
    Log.info(String.format("===== %s =====", chaine));
    String val = given().header(WWW_AUTHENTICATE, chaine).multiPart("file", "myFile", bytes).post().asString();
    Log.info(String.format("====> %s <=====", val));

    /**
     * ========================================================================
     * Comment vérifier la conf ?????????
     */
    List<String> supplierNames = Arrays.asList("PATH", "QUARKUS_KERBEROS_DEBUG", "KERBEROS_CLIENT_LOGIN_CONTEXT_NAME",
        "KERBEROS_CLIENT_DEBUG", "KERBEROS_CLIENT_KEYTAB_PATH", "KERBEROS_CLIENT_USER_PRINCIPAL_NAME",
        "KERBEROS_CLIENT_USER_PRINCIPAL_REALM", "KERBEROS_CLIENT_SERVICE_PRINCIPAL_NAME",
        "KERBEROS_CLIENT_USER_PRINCIPAL_PASSWORD", "KERBEROS_CLIENT_USE_SPNEGO_OID");
    for (String string : supplierNames) {
      Log.info(String.format("====> %s = %s", string, System.getenv(string)));
    }
    supplierNames = Arrays.asList("quarkus.kerberos.debug","quarkus.kerberos.keytab-path", "quarkus.kerberos.devservices.realm","kerberos-client.user-principal-realm");
    for (String string : supplierNames) {
      Log.info(String.format("====> %s = %s", string, System.getProperty(string)));
    }
    Log.info(String.format("====> %s", config.realm()));
    /**
     * ========================================================================
     */
  }
}
