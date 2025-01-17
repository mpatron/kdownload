package org.jobjects;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import io.quarkiverse.kerberos.test.utils.KerberosTestClient;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class SpnegoAuthenticationTestCase {
  public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
  public static final String NEGOTIATE = "Negotiate";

  KerberosTestClient kerberosTestClient = new KerberosTestClient();

  @Test
  public void testSpnegoSuccess() throws Exception {
    given().when().get("/api/users/me").then().assertThat().statusCode(401).and().header(WWW_AUTHENTICATE, NEGOTIATE);

    var result = kerberosTestClient.get("/api/users/me", "bob", "bob");
    result.statusCode(200).body(Matchers.is("bob"));
  }
}
