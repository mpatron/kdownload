package org.jobjects;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

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

  public static boolean isAccessDNSByName(String fqdn) {
    boolean returnValue = Boolean.FALSE;
    if(StringUtils.isNotBlank(fqdn)) {
      try {
        InetAddress address = InetAddress.getByName(StringUtils.trim(fqdn));
        Log.info("host address: " + address.getHostAddress());
        returnValue = Boolean.TRUE;
      } catch (UnknownHostException e) {
        Log.info(String.format("Cannot find in DNS : '%s' with java error : %s", fqdn, e));
      }
      }
    return returnValue;
  }

  public static boolean isFqdnInServicePrincipalNameExist(String servicePrincipalName) {
    boolean returnValue = Boolean.FALSE;
    if(StringUtils.isNotBlank(servicePrincipalName)) {
      String[] chaines= StringUtils.split(servicePrincipalName, "/");
      if (chaines.length>1) {
        returnValue= isAccessDNSByName(chaines[1]);
      }
    }
    return returnValue;
  }

  public static String getFqdnInServicePrincipal(String servicePrincipalName) {
    String returnValue = null;
    if(StringUtils.isNotBlank(servicePrincipalName)) {
      String[] chaines= StringUtils.split(servicePrincipalName, "/");
      if (chaines.length>1) {
        returnValue= chaines[1];
      }
    }
    return returnValue;
  }

}
