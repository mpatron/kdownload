package org.jobjects.health;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
class ReadinessHealthCheckTest {
    @Test
    void testHelloEndpoint() {
        given()
                .contentType(ContentType.JSON)
                .when().get("/q/health/ready")
                .then()
                .statusCode(200)
                .body("status", equalTo("UP"))
                .body("checks[0].name", equalTo("Ready health check"))
                .body("checks[0].data.service-principal-name", equalTo("HTTP/localhost"))
                .body("checks[0].data.service-principal-realm", equalTo("EXAMPLE.COM"));
    }
}