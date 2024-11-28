package org.jobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import io.quarkiverse.kerberos.test.utils.KerberosTestClient;
import io.restassured.RestAssured;

public class SpnegoAuthenticationTestCase {
  public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
  public static final String NEGOTIATE = "Negotiate";

  KerberosTestClient kerberosTestClient = new KerberosTestClient();

  @Test
  public void testSpnegoSuccess() throws Exception {

    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 8081;
    var header = RestAssured.get("/api/users/me").then().statusCode(401).extract().header(WWW_AUTHENTICATE);
    assertEquals(NEGOTIATE, header);

    var result = kerberosTestClient.get("/api/users/me", "bob", "bob");
    result.statusCode(200).body(Matchers.is("bob"));
  }
}
