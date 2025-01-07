# FROM registry.access.redhat.com/ubi8/ubi-minimal:8.10
FROM ubuntu

ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get -qq update && \
    apt-get -yqq install krb5-user libpam-krb5 curl && \
    apt-get -yqq clean

WORKDIR /work/

# RUN chown 1001 /work \
#     && chmod "g+rwX" /work \
#     && chown 1001:root /work
# COPY --chown=1001:root --chmod=774 target/*-runner /work/application
# COPY --chown=1001:root --chmod=774 docker_entrypoint_start-quarkus.sh /work/docker_entrypoint_start-quarkus.sh

RUN chmod "g+rwX" /work
COPY --chmod=774 target/*-runner /work/application
COPY --chmod=774 docker_entrypoint_start-quarkus.sh /work/docker_entrypoint_start-quarkus.sh

RUN mkdir -p /work/keytabs

EXPOSE 8080
EXPOSE 88/tcp
EXPOSE 88/udp
# USER 1001

ENTRYPOINT ["/work/docker_entrypoint_start-quarkus.sh"]
