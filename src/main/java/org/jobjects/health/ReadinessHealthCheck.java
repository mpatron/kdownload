package org.jobjects.health;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;
import org.jobjects.KerberosConfig;
import org.jobjects.Utils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@Readiness
@ApplicationScoped
public class ReadinessHealthCheck implements HealthCheck {

	@Inject
	KerberosConfig config;

	@Override
	public HealthCheckResponse call() {
		
		HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Ready health check");

		if (StringUtils.isNotBlank(config.kerberos().get("service-principal-name"))) {
			String servicePrincipalName = config.kerberos().get("service-principal-name");
			if (Utils.isFqdnInServicePrincipalNameExist(servicePrincipalName)) {
				responseBuilder.withData("keytab-path", config.kerberos().get("keytab-path"))
						.withData("service-principal-name", config.kerberos().get("service-principal-name"))
						.withData("service-principal-realm", config.kerberos().get("service-principal-realm"));
				responseBuilder.withData(
						Utils.getFqdnInServicePrincipal(config.kerberos().get("service-principal-name")), "DNS exist");
				responseBuilder.withData("JCE uses unlimited policy",
					Utils.isJCEusesUnlimitedPolicy());
				responseBuilder.up();
			}
		} else {
			responseBuilder.down();
		}
		return responseBuilder.build();

	}
}