package org.jobjects;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

@ConfigMapping(prefix = "kerberos-client")
public interface KerberosClientConfig {

	@WithName("user-principal-name")
	public String  userPrincipalName();

	@WithName("user-principal-password")
	public String  userPrincipalPassword();

    @WithName("user-principal-realm")
	public String  userPrincipalRealm();

	@WithName("service-principal-name")
	public String  servicePrincipalName();
}
