FROM registry.access.redhat.com/ubi8/ubi-minimal:8.10

RUN microdnf --nodocs install yum \
    && yum --nodocs -q update -y \
    && yum --nodocs -q install -y install krb5-workstation \
    && yum clean all

WORKDIR /work/

RUN chown 1001 /work \
    && chmod "g+rwX" /work \
    && chown 1001:root /work
COPY --chown=1001:root --chmod=774 target/*-runner /work/application
COPY --chown=1001:root --chmod=774 podman/docker_entrypoint_start-quarkus.sh /work/docker_entrypoint_start-quarkus.sh

RUN mkdir -p /work/keytabs

EXPOSE 8080
EXPOSE 88/tcp
EXPOSE 88/udp
USER 1001

ENTRYPOINT ["/work/docker_entrypoint_start-quarkus.sh"]
