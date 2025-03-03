package org.jobjects.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.jobjects.KerberosConfig;

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

        return HealthCheckResponse.named(returnValue).up().withData("debug", config.kerberos().debug())
                .withData("use-spnego-oid", config.kerberos().useSpnegoOid())
                .withData("service-principal-name", config.kerberos().servicePrincipalName())
                .withData("service-principal-realm", config.kerberos().servicePrincipalRealm())
                .withData("keytab-path", System.getProperty("quarkus.kerberos.keytab-path"))
                .build();
    }
}