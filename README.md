# kdownload

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/kdownload-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

sudo apt install krb5-user
sudo dpkg-reconfigure krb5-config
mvn dependency:sources

mickael@deborah:~/Documents/kerberos/kdownload$ KRB5_CONFIG=/tmp/devservices-krb513579479556088609956.conf curl --negotiate -u bob@EXAMPLE.COM -v http://localhost:8080/api/users/me
Enter host password for user 'bob@EXAMPLE.COM':
* Host localhost:8080 was resolved.
* IPv6: ::1
* IPv4: 127.0.0.1
*   Trying [::1]:8080...
* connect to ::1 port 8080 from ::1 port 52894 failed: Connexion refusée
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080
* gss_init_sec_context() failed: No credentials were supplied, or the credentials were unavailable or inaccessible. SPNEGO cannot find mechanisms to negotiate. 
* Server auth using Negotiate with user 'bob@EXAMPLE.COM'
> GET /api/users/me HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.5.0
> Accept: */*
> 
< HTTP/1.1 401 Unauthorized
* gss_init_sec_context() failed: No credentials were supplied, or the credentials were unavailable or inaccessible. SPNEGO cannot find mechanisms to negotiate. 
< www-authenticate: Negotiate
< content-length: 0
< 
* Connection #0 to host localhost left intact
mickael@deborah:~/Documents/kerberos/kdownload$ KRB5_CONFIG=/tmp/devservices-krb513579479556088609956.conf kinit bob@EXAMPLE.COM
Password for bob@EXAMPLE.COM: 
mickael@deborah:~/Documents/kerberos/kdownload$ KRB5_CONFIG=/tmp/devservices-krb513579479556088609956.conf klist
Ticket cache: FILE:/tmp/krb5cc_1000
Default principal: bob@EXAMPLE.COM

Valid starting       Expires              Service principal
22/11/2024 17:44:51  23/11/2024 05:44:51  krbtgt/EXAMPLE.COM@EXAMPLE.COM
	renew until 29/11/2024 17:44:48
22/11/2024 17:44:58  23/11/2024 05:44:51  HTTP/localhost@EXAMPLE.COM
	renew until 29/11/2024 17:44:48
mickael@deborah:~/Documents/kerberos/kdownload$ KRB5_CONFIG=/tmp/devservices-krb513579479556088609956.conf curl --negotiate -u bob@EXAMPLE.COM -v http://localhost:8080/api/users/me
Enter host password for user 'bob@EXAMPLE.COM':
* Host localhost:8080 was resolved.
* IPv6: ::1
* IPv4: 127.0.0.1
*   Trying [::1]:8080...
* connect to ::1 port 8080 from ::1 port 56168 failed: Connexion refusée
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080
* Server auth using Negotiate with user 'bob@EXAMPLE.COM'
> GET /api/users/me HTTP/1.1
> Host: localhost:8080
> Authorization: Negotiate YIIDHAYGKwYBBQUCoIIDEDCCAwygDTALBgkqhkiG9xIBAgKiggL5BIIC9WCCAvEGCSqGSIb3EgECAgEAboIC4DCCAtygAwIBBaEDAgEOogcDBQAgAAAAo4IB9mGCAfIwggHuoAMCAQWhDRsLRVhBTVBMRS5DT02iHDAaoAMCAQOhEzARGwRIVFRQGwlsb2NhbGhvc3SjggG4MIIBtKADAgESoQMCAQGiggGmBIIBoqqTSyZHuqYw4SgJRsDdq4r3WyiMNZABBtHsBYN8qSad1ctUjxJ1c2uuYk4wgg5DI2/VsraGC8Df/Jr7BY3L07zO8JSLZt4PKvlbgkvIbaa2E4c9XUXanIO+HeQzcF7t7C6KARmX5j4DIHML0s5tc0liw1Veo30+DK8IBqj2Ldhe51t9ywpINsUREMd4Me/WHCfWVSKuXlwHYtpczR+7dsqEisiffEoYjTw7ditR8SN3COUwZdwomkTxcWwppBeLFOneQpiEt9sBanhwXj7O7GCUXXyySnCJW/Mq1nXs/iqIACKSBi69pA2tvkD8oH42OhVjzmm57rPjFvZyX1cNYn3qhxGfD+rodurvEYskrwylVdQeN8gcKqNnl4+MkPYSjW4KCEUeqV+oWzc0p7pW+vs7W0eOteZ5EQG9xUr2v/swXPUKWcB0YJTQuk3nNTKw3/eyQHze932WxklpMiCKq7RcffBDts96IyP9saDy25a0vQOQTVx6Fjp2J1xU969l6Sq5CivrFZ7dptKEXKN9z4GyqV4pIa6idFMlipWm5iimvF6kgcwwgcmgAwIBEqKBwQSBvtmbmOBu2r70pDjXJW4dQ0IvqCkLE2o3yiZby5XTNvnEkx/N9R7wDYC+DXB0u49zjambJ8h9EvZbGipNBN//5FANx/nuGWsYlS29m6dREpii3tmaiY7QM4m+cCZAHXstLmv7XKyLkcrFlCmWx2mAkbVVWX+FZiDsVGjS7QL4hnkTRPaMCbd/TKJ+u+srebPsbd+wfoAeXXWp500TJb801Rl1Dy5QmEhCo0n/Dt5WI0lNj6t76jtcs1YaXBxdgAs=
> User-Agent: curl/8.5.0
> Accept: */*
> 
< HTTP/1.1 200 OK
< www-authenticate: Negotiate oYGFMIGCoAMKAQChCwYJKoZIhvcSAQICom4EbGBqBgkqhkiG9xIBAgICAG9bMFmgAwIBBaEDAgEPok0wS6ADAgESokQEQtQCd0X1BVzR4YPRFvxsPdn67K0Eiy8t/+S1dCE9ZMkMvnBlclBW4yLtnBuHrQtfRWLCPJ4TYxqEN92tigjsOozwxQ==
< content-length: 3
< Content-Type: text/plain;charset=UTF-8
< 
* Connection #0 to host localhost left intact

https://docs.quarkiverse.io/quarkus-kerberos/dev/index.html#_jaas_login_configuration

~~~bash
curl -Ls https://sh.jbang.dev | bash -s - trust add https://repo1.maven.org/maven2/io/quarkus/quarkus-cli/
curl -Ls https://sh.jbang.dev | bash -s - app install --fresh --force quarkus@quarkusio
~~~


quarkus create app org.jobjects:kdownload:1.0-SNAPSHOT
mickael@deborah:~/Documents/kerberos$ quarkus create app org.jobjects:kdownload:1.0-SNAPSHOT
Looking for the newly published extensions in registry.quarkus.io
-----------

applying codestarts...
📚 java
🔨 maven
📦 quarkus
📝 config-properties
🔧 tooling-dockerfiles
🔧 tooling-maven-wrapper
🚀 rest-codestart

-----------
[SUCCESS] ✅  quarkus project has been successfully generated in:
--> /home/mickael/Documents/kerberos/kdownload
-----------
Navigate into this directory and get started: quarkus dev

:1,$s/[ \t]*$//

sed -i 's/[ \t]*$//' ./readme.md


MAVEN :
curl -L -O --output-dir ~/tmp https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz
sudo tar xvfz ~/tmp/apache-maven-3.9.9-bin.tar.gz -C /opt
M2_HOME='/opt/apache-maven-3.9.9'
export PATH="$M2_HOME/bin:$PATH"


./mvnw quarkus:dev
./mvnw quarkus:add-extension -Dextensions='io.quarkiverse.kerberos:quarkus-kerberos'
KRB5_CONFIG=/tmp/devservices-krb513579479556088609956.conf curl --negotiate -u bob@EXAMPLE.COM -v http://localhost:8080/api/users/me

./mvnw verify -Dnative
./mvnw package -Dnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true

https://quarkus.io/guides/building-native-image#creating-a-container

DOCKER_BUILDKIT=1 docker build -f src/main/docker/Dockerfile.multistage -t quarkus-quickstart/getting-started .
docker buildx build -f src/main/docker/Dockerfile.multistage -t quarkus-quickstart/getting-started .
docker run -i --rm -p 8080:8080 quarkus-quickstart/getting-started

echo -e "net.bridge.bridge-nf-call-iptables = 1" | sudo tee /etc/sysctl.d/11-docker.conf
echo -e "net.bridge.bridge-nf-call-ip6tables = 1" | sudo tee /etc/sysctl.d/11-docker.conf
sudo modprobe br_netfilter
sudo sysctl -p /etc/sysctl.d/11-docker.conf

## Podman important faire:
systemctl --user enable podman.socket --now
export DOCKER_HOST=unix://$(podman info --format '{{.Host.RemoteSocket.Path}}')
