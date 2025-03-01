package org.jobjects;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import io.quarkiverse.kerberos.test.utils.KerberosTestClient;
import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class UsersResourceTest {
    @Inject
    KerberosConfig config;

    @Inject
    KerberosClientConfig configClient;

    public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
    public static final String NEGOTIATE = "Negotiate";

    @Test
    void testMe() throws Exception {
        Log.debug("#############################");
        Log.info("#############################");
        Log.error("#############################");
        KerberosTestClient kerberosTestClient = new KerberosTestClient();
        var result = kerberosTestClient.get("/api/users/me", "bob", "bob");
        Log.debug(result);

        result.statusCode(200).contentType(ContentType.JSON)
                .body(Matchers.is(new IdentityKerberos("bob", "bob@EXAMPLE.COM", "EXAMPLE.COM")));
        String chaine = result.extract().header(WWW_AUTHENTICATE);
        Log.info(String.format(WWW_AUTHENTICATE + "=%s", chaine));
    }
}
