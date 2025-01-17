package org.jobjects;

import java.util.Map;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

@ConfigMapping(prefix = "quarkus")
public interface KerberosConfig {
	@WithName("kerberos")
	public Map<String, String>  kerberos();
}
