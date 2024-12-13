package org.jobjects;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

@ConfigMapping(prefix = "quarkus.kerberos.devservices")
public interface KerberosConfig {
	@WithDefault("toto")
	public String realm();
}
