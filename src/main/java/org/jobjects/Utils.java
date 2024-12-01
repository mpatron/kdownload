package org.jobjects;

import java.util.Arrays;
import java.util.List;

import io.quarkus.logging.Log;

public class Utils {
  public static void affiche() {
    /**
     * ========================================================================
     * Comment vérifier la conf ?????????
     */
    List<String> supplierNames = Arrays.asList("PATH", "QUARKUS_KERBEROS_DEBUG", "KERBEROS_CLIENT_LOGIN_CONTEXT_NAME",
        "KERBEROS_CLIENT_DEBUG", "KERBEROS_CLIENT_KEYTAB_PATH", "KERBEROS_CLIENT_USER_PRINCIPAL_NAME",
        "KERBEROS_CLIENT_USER_PRINCIPAL_REALM", "KERBEROS_CLIENT_SERVICE_PRINCIPAL_NAME",
        "KERBEROS_CLIENT_USER_PRINCIPAL_PASSWORD", "KERBEROS_CLIENT_USE_SPNEGO_OID");
    for (String string : supplierNames) {
      Log.info(String.format("====> %s = %s", string, System.getenv(string)));
    }
    supplierNames = Arrays.asList("quarkus.kerberos.debug", "quarkus.kerberos.keytab-path",
        "quarkus.kerberos.service-principal-name", "quarkus.kerberos.service-principal-realm",
        "quarkus.kerberos.devservices.realm", "quarkus.kerberos.enabled", "quarkus.kerberos.devservices.enabled",
        "kerberos-client.user-principal-realm");
    for (String string : supplierNames) {
      Log.info(String.format("====> %s = %s", string, System.getProperty(string)));
    }
    /**
     * ========================================================================
     */

  }
}
