package org.jobjects.health;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class ReadinessHealthCheckTest {
    @Test
    void testHelloEndpoint() {
        given().when().get("/q/health/ready").then().statusCode(200).body(containsString("Ready health check"));
    }

}