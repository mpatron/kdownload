#!/bin/bash
QUARKUS_OPS=
QUARKUS_OPS="$QUARKUS_OPS -Dquarkus.kerberos.debug=true"
QUARKUS_OPS="$QUARKUS_OPS -Dquarkus.kerberos.keytab-path=/home/mickael/tmp/http.deborah.jobjects.org.keytab"
QUARKUS_OPS="$QUARKUS_OPS -Dquarkus.kerberos.service-principal-name='HTTP/deborah.jobjects.org'"
QUARKUS_OPS="$QUARKUS_OPS -Dquarkus.kerberos.service-principal-realm=JOBJECTS.ORG"
QUARKUS_OPS="$QUARKUS_OPS -Dquarkus.kerberos.enabled=true"
# QUARKUS_OPS=$QUARKUS_OPS -Dquarkus.kerberos.devservices.realm=JOBJECTS.ORG
# QUARKUS_OPS=$QUARKUS_OPS -Dquarkus.kerberos.devservices.enabled=false
echo /home/mickael/Documents/kerberos/kdownload/target/kdownload-1.0-SNAPSHOT-runner $QUARKUS_OPS
/home/mickael/Documents/kerberos/kdownload/target/kdownload-1.0-SNAPSHOT-runner $QUARKUS_OPS