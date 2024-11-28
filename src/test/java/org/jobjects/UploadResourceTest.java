package org.jobjects;

import io.quarkiverse.kerberos.test.utils.KerberosTestClient;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Level;
import java.util.logging.Logger;

@QuarkusTest
public class UploadResourceTest {
  private static final Logger LOG = Logger.getLogger(UploadResource.class.getName());

  public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
  public static final String NEGOTIATE = "Negotiate";

  @Test
  void testMultipart() throws Exception {

    KerberosTestClient kerberosTestClient = new KerberosTestClient();
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 8081;
    var header = RestAssured.get("/api/users/me").then().statusCode(401).extract().header(WWW_AUTHENTICATE);
    assertEquals(NEGOTIATE, header);

    var result = kerberosTestClient.get("/api/users/me", "bob", "bob");
    result.statusCode(200).body(Matchers.is("bob"));

    String chaine = result.extract().header(WWW_AUTHENTICATE);
    //String chaine=result.header(WWW_AUTHENTICATE, startsWith(NEGOTIATE)).toString();
    LOG.log(Level.INFO, String.format(WWW_AUTHENTICATE+"=%s", chaine));

    final byte[] bytes = IOUtils.toByteArray(getClass().getResourceAsStream("/application.properties"));
    
    given().header(WWW_AUTHENTICATE, chaine).multiPart("file", "myFile", bytes).expect().statusCode(200);
    LOG.log(Level.INFO, String.format("===== %s =====", chaine));
    String val = given().header(WWW_AUTHENTICATE, chaine).multiPart("file", "myFile", bytes).post().asString();
    LOG.log(Level.INFO, String.format("====> %s <=====", val));
    //given().header(WWW_AUTHENTICATE, chaine).multiPart("file", "myFile", bytes).expect().statusCode(200).body(is(new String(bytes))).when().post("/api/upload");
  }
}
