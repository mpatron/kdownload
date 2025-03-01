package org.jobjects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class IdentityKerberos {

    String principalName;
    String fullName;
    String realm;

    public IdentityKerberos() {
    }

    public IdentityKerberos(String principalName, String fullName, String realm) {
        this.principalName = principalName;
        this.fullName = fullName;
        this.realm = realm;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String toString() {
        return new ToStringBuilder(this).append("principalName", principalName).append("fullName", fullName)
                .append("realm", realm).toString();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        IdentityKerberos rhs = (IdentityKerberos) obj;
        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(principalName, rhs.principalName)
                .append(fullName, rhs.fullName)
                .append(realm, rhs.realm)
                .isEquals();
    }
}
