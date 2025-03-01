#!/bin/bash

# docker_entrypoint_start-quarkus.sh

KD_KEYTAB_FILE="${KEYTAB_FILE:-/home/mickael/tmp/http.deborah.jobjects.org.keytab}"
KD_SERVICE_PRINCIPAL_NAME="${SERVICE_PRINCIPAL_NAME:-HTTP/deborah.jobjects.org}"
KD_SERVICE_PRINCIPAL_REALM="${SERVICE_PRINCIPAL_REALM:-JOBJECTS.ORG}"
KD_UPLOAD_DIR="${UPLOAD_DIR:-/tmp}"

echo "**********************************"
echo "-> Log: Root path: '$(pwd)'"
echo "-> Log: USER: '$USER'"
echo "-> Log: ID: '$(id))'"
echo "-> Log: Check env variables:"
echo "     - KD_KEYTAB_FILE:             '$KD_KEYTAB_FILE'"
echo "     - KD_SERVICE_PRINCIPAL_NAME:  '$KD_SERVICE_PRINCIPAL_NAME'"
echo "     - KD_SERVICE_PRINCIPAL_REALM: '$KD_SERVICE_PRINCIPAL_REALM'"
echo "     - KD_UPLOAD_DIR:              '$KD_UPLOAD_DIR'"

QUARKUS_OPS=
QUARKUS_OPS="$QUARKUS_OPS -Dquarkus.kerberos.keytab-path=$KD_KEYTAB_FILE"
QUARKUS_OPS="$QUARKUS_OPS -Dquarkus.kerberos.service-principal-name=$KD_SERVICE_PRINCIPAL_NAME"
QUARKUS_OPS="$QUARKUS_OPS -Dquarkus.kerberos.service-principal-realm=$KD_SERVICE_PRINCIPAL_REALM"
QUARKUS_OPS="$QUARKUS_OPS -Dquarkus.http.body.uploads-directory=$KD_UPLOAD_DIR"
QUARKUS_OPS="$QUARKUS_OPS -Dquarkus.kerberos.debug=true"
QUARKUS_OPS="$QUARKUS_OPS -Dquarkus.kerberos.enabled=true"
QUARKUS_OPS="$QUARKUS_OPS -Dquarkus.http.host=0.0.0.0"
QUARKUS_OPS="$QUARKUS_OPS -Dquarkus.http.port=8088"
QUARKUS_OPS="$QUARKUS_OPS -Dquarkus.http.access-log.enabled=true"

echo "**********************************"
echo "-> Log: Execute java native command "
echo " './application $QUARKUS_OPS $*'"
echo "**********************************"
/work/application $QUARKUS_OPS $*
