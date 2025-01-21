package org.jobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class UtilsTest {
    @Test
    void testIsAccessDNSByName() throws Exception {
        assertEquals(false, Utils.isAccessDNSByName(null));
        assertEquals(false, Utils.isAccessDNSByName(""));
        assertEquals(false, Utils.isAccessDNSByName("   "));
        assertEquals(true, Utils.isAccessDNSByName("google.com"));
        assertEquals(false, Utils.isAccessDNSByName("inconnu.dans.lespace"));
    }

    @Test
    void testIsFqdnInServicePrincipalNameExist() {
        assertEquals(false, Utils.isFqdnInServicePrincipalNameExist(null));
        assertEquals(false, Utils.isFqdnInServicePrincipalNameExist(""));
        assertEquals(false, Utils.isFqdnInServicePrincipalNameExist("   "));
        assertEquals(true, Utils.isFqdnInServicePrincipalNameExist("HTTP/google.com"));
        assertEquals(false, Utils.isFqdnInServicePrincipalNameExist("HTTP/inconnu.dans.lespace"));
        assertEquals(false, Utils.isFqdnInServicePrincipalNameExist("inconnu.dans.lespace"));
    }

    @Test
    void testGetFqdnInServicePrincipal() {
        assertEquals(null, Utils.getFqdnInServicePrincipal(null));
        assertEquals(null, Utils.getFqdnInServicePrincipal(""));
        assertEquals(null, Utils.getFqdnInServicePrincipal("   "));
        assertEquals("google.com", Utils.getFqdnInServicePrincipal("HTTP/google.com"));
        assertEquals("inconnu.dans.lespace", Utils.getFqdnInServicePrincipal("HTTP/inconnu.dans.lespace"));
        assertEquals(null, Utils.getFqdnInServicePrincipal("inconnu.dans.lespace"));
    }
}
