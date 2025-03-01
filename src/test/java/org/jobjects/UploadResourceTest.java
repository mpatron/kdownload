package org.jobjects;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
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
      Log.info(String.format("ENV ====> %s = %s", string, System.getenv(string)));
    }

    for (String string : config.kerberos().keySet()) {
      Log.info(String.format("KERBEROS ====> %s = %s", string, config.kerberos().get(string)));
    }

    Log.info(String.format("KERBEROS CLIENT====> %s = %s", "user-principal-name", configClient.userPrincipalName()));
    Log.info(
        String.format("KERBEROS CLIENT====> %s = %s", "user-principal-password", configClient.userPrincipalPassword()));
    Log.info(String.format("KERBEROS CLIENT====> %s = %s", "user-principal-realm", configClient.userPrincipalRealm()));
    Log.info(
        String.format("KERBEROS CLIENT====> %s = %s", "service-principal-name", configClient.servicePrincipalName()));

    /**
     * ========================================================================
     */
  }
}
