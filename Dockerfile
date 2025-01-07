FROM registry.access.redhat.com/ubi8/ubi-minimal:8.10

WORKDIR /work/
RUN chown 1001 /work \
    && chmod "g+rwX" /work \
    && chown 1001:root /work
COPY --chown=1001:root --chmod=774 target/*-runner /work/application
COPY --chown=1001:root --chmod=774 docker_entrypoint_start-quarkus.sh /work/docker_entrypoint_start-quarkus.sh

EXPOSE 8080
EXPOSE 88/tcp
EXPOSE 88/udp
USER 1001

# ENTRYPOINT ["./application", "-Dquarkus.http.host=0.0.0.0"]
# CMD ["/bin/sh","docker_entrypoint_start-quarkus.sh"]
ENTRYPOINT ["/work/docker_entrypoint_start-quarkus.sh"]
