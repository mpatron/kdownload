#!/bin/bash

#Install podman
# podman kill $(podman ps -q)
# podman rm $(podman ps -a -q)
# podman rmi $(podman images -q)
# podman image prune --all --force
# podman container prune --force
# podman volume prune --force
# podman network prune --force
# podman system prune --all --force

QUARKUS_OPS=
QUARKUS_OPS="$QUARKUS_OPS -Dquarkus.kerberos.debug=true"
QUARKUS_OPS="$QUARKUS_OPS -Dquarkus.kerberos.keytab-path=/home/mickael/tmp/http.deborah.jobjects.org.keytab"
QUARKUS_OPS="$QUARKUS_OPS -Dquarkus.kerberos.service-principal-name=HTTP/deborah.jobjects.org"
QUARKUS_OPS="$QUARKUS_OPS -Dquarkus.kerberos.service-principal-realm=JOBJECTS.ORG"
QUARKUS_OPS="$QUARKUS_OPS -Dquarkus.kerberos.enabled=true"
# QUARKUS_OPS=$QUARKUS_OPS -Dquarkus.kerberos.devservices.realm=JOBJECTS.ORG
# QUARKUS_OPS=$QUARKUS_OPS -Dquarkus.kerberos.devservices.enabled=false
quarkus build --native
echo ~/Documents/kerberos/kdownload/target/kdownload-1.0-SNAPSHOT-runner $QUARKUS_OPS
~/Documents/kerberos/kdownload/target/kdownload-1.0-SNAPSHOT-runner $QUARKUS_OPS 
