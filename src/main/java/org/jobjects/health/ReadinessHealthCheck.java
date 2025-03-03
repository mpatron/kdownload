package org.jobjects.health;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;
import org.jobjects.KerberosConfig;
import org.jobjects.Utils;

import io.quarkus.logging.Log;
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
		try {
			boolean allValidity = Boolean.TRUE;

			if (StringUtils.isNotBlank(config.kerberos().servicePrincipalName())) {
				responseBuilder.withData("service-principal-name", config.kerberos().servicePrincipalName());
			} else {
				allValidity = Boolean.FALSE;
				responseBuilder.withData("service-principal-name", "empty");
			}
			if (Utils.isFqdnInServicePrincipalNameExist(config.kerberos().servicePrincipalName())) {
				responseBuilder.withData(
						Utils.getFqdnInServicePrincipal(config.kerberos().servicePrincipalName()),
						"fqdn in principal exist in DNS");
			} else {
				allValidity = Boolean.FALSE;
				responseBuilder.withData(
						Utils.getFqdnInServicePrincipal(config.kerberos().servicePrincipalName()),
						"!!fqdn in principal does NOT exist in DNS !!");
			}
			responseBuilder.withData("service-principal-realm", config.kerberos().servicePrincipalRealm());

			responseBuilder.withData("keytab-path", System.getProperty("quarkus.kerberos.keytab-path"));
			responseBuilder.withData("keytab-path-is-exist", Utils.isFileExist(System.getProperty("quarkus.kerberos.keytab-path")));

			responseBuilder.withData("JCE uses unlimited policy", Utils.isJCEusesUnlimitedPolicy());
			if (Utils.isFileExist(config.http().body().uploadsDirectory())) {
				responseBuilder.withData("uploads-directory",
						config.http().body().uploadsDirectory());
			} else {
				allValidity = Boolean.FALSE;
				responseBuilder.withData("uploads-directory",
						String.format("path not found '%s'", config.http().body().uploadsDirectory()));
			}

			if (allValidity) {
				responseBuilder.up();
			} else {
				responseBuilder.down();
			}

		} catch (Throwable e) {
			e.printStackTrace(System.err);
			Log.fatal(e.getMessage(), e);
			responseBuilder.down();
		}
		Log.fatal("coucou");
		return responseBuilder.build();

	}
}
