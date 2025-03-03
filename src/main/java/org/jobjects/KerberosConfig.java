package org.jobjects;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

/**
 * application.properties
 */
@ConfigMapping(prefix = "quarkus")
public interface KerberosConfig {

	Kerberos kerberos();

	interface Kerberos {
		/**
		 * quarkus.kerberos.debug
		 * @return
		 */
		@WithDefault("true")
		boolean debug();

		/**
		 * quarkus.kerberos.use-spnego-oid
		 * @return
		 */
		@WithDefault("true")
		boolean useSpnegoOid();

		/**
		 * quarkus.kerberos.service-principal-name
		 * @return
		 */
		@WithDefault("HTTP/localhost")
		String servicePrincipalName();

		/**
		 * quarkus.kerberos.service-principal-realm
		 * @return
		 */
		@WithDefault("EXAMPLE.COM")
		String servicePrincipalRealm();

		/**
		 * quarkus.kerberos.keytab-path
		 * @return
		 */
		/*
		@WithDefault("/tmp/service.keytab")
		String keytabPath();
		 */
	}

	Http http();

	interface Http {
		Body body();

		interface Body {

			/**
			 * quarkus.http.body.uploads-directory 
			 * @return
			 */
			String uploadsDirectory();
		}
	}
}
