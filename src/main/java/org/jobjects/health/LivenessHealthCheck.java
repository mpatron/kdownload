package org.jobjects.health;

import org.eclipse.microprofile.health.Liveness;
import org.jobjects.KerberosConfig;

import io.quarkus.logging.Log;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@Liveness
@ApplicationScoped
public class LivenessHealthCheck implements HealthCheck {

    @Inject
    KerberosConfig config;

    @Override
    public HealthCheckResponse call() {

        String returnValue = "Startup health check";
        for (String string : config.kerberos().keySet()) {
            Log.info(String.format("KERBEROS ====> %s = %s", string, config.kerberos().get(string)));
        }

        return HealthCheckResponse.named(returnValue).up().withData("debug", config.kerberos().get("debug"))
                .withData("use-spnego-oid", config.kerberos().get("se-spnego-oid"))
                .withData("keytab-path", config.kerberos().get("keytab-path"))
                .withData("service-principal-name", config.kerberos().get("service-principal-name"))
                .withData("service-principal-realm", config.kerberos().get("service-principal-realm")).build();
    }

}