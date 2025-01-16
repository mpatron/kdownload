# kdownload

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

~~~shell script
./mvnw compile quarkus:dev
~~~

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

~~~shell script
./mvnw package
~~~

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

~~~shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
~~~

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

~~~shell script
./mvnw package -Dnative
~~~

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

~~~shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
~~~

You can then execute your native executable with: `./target/kdownload-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

~~~bash
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
~~~

[https://docs.quarkiverse.io/quarkus-kerberos/dev/index.html#_jaas_login_configuration](https://docs.quarkiverse.io/quarkus-kerberos/dev/index.html#_jaas_login_configuration)

~~~bash
curl -Ls https://sh.jbang.dev | bash -s - trust add https://repo1.maven.org/maven2/io/quarkus/quarkus-cli/
curl -Ls https://sh.jbang.dev | bash -s - app install --fresh --force quarkus@quarkusio
~~~

~~~bash
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

~~~

~~~txt
:1,$s/[ \t]*$//
sed -i 's/[ \t]*$//' ./readme.md
~~~

MAVEN :

~~~bash
curl -L -O --output-dir ~/tmp https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz
sudo tar xvfz ~/tmp/apache-maven-3.9.9-bin.tar.gz -C /opt
M2_HOME='/opt/apache-maven-3.9.9'
export PATH="$M2_HOME/bin:$PATH"
~~~

~~~bash
./mvnw quarkus:dev
./mvnw quarkus:add-extension -Dextensions='io.quarkiverse.kerberos:quarkus-kerberos'
KRB5_CONFIG=/tmp/devservices-krb513579479556088609956.conf curl --negotiate -u bob@EXAMPLE.COM -v http://localhost:8080/api/users/me

./mvnw verify -Dnative
./mvnw package -Dnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true
~~~

[https://quarkus.io/guides/building-native-image#creating-a-container](https://quarkus.io/guides/building-native-image#creating-a-container)

~~~bash
DOCKER_BUILDKIT=1 docker build -f src/main/docker/Dockerfile.multistage -t quarkus-quickstart/getting-started .
docker buildx build -f src/main/docker/Dockerfile.multistage -t quarkus-quickstart/getting-started .
docker run -i --rm -p 8080:8080 quarkus-quickstart/getting-started

echo -e "net.bridge.bridge-nf-call-iptables = 1" | sudo tee /etc/sysctl.d/11-docker.conf
echo -e "net.bridge.bridge-nf-call-ip6tables = 1" | sudo tee /etc/sysctl.d/11-docker.conf
sudo modprobe br_netfilter
sudo sysctl -p /etc/sysctl.d/11-docker.conf
~~~

## Podman important faire

~~~bash
systemctl --user enable podman.socket --now
export DOCKER_HOST=unix://$(podman info --format '{{.Host.RemoteSocket.Path}}')
quarkus build --native -Dquarkus.native.container-build=true -Dquarkus.native.container-runtime=podman
~~~

~~~bash
KRB5_CONFIG=$(ls -latr /tmp/devservices-krb*.conf | tail -n 1 | awk '{print $9}') kinit alice@EXAMPLE.COM
curl --negotiate -u alice@EXAMPLE.COM -i -X POST -H "Content-Type: multipart/form-data" --form "data=@mvnw.cmd" http://localhost:8080/api/upload

KRB5_CONFIG=$(ls -latr /tmp/devservices-krb*.conf | tail -n 1 | awk '{print $9}') kinit alice
KRB5_CONFIG=$(ls -latr /tmp/devservices-krb*.conf | tail -n 1 | awk '{print $9}') klist

KRB5_CONFIG=$(ls -latr /tmp/devservices-krb*.conf | tail -n 1 | awk '{print $9}') curl --negotiate -u alice@EXAMPLE.COM -v http://localhost:8080/api/users/me

KRB5_CONFIG=$(ls -latr /tmp/devservices-krb*.conf | tail -n 1 | awk '{print $9}') curl --negotiate -u alice@EXAMPLE.COM -i -X POST -H "Content-Type: multipart/form-data" --form "data=@mvnw.cmd" http://localhost:8080/api/upload
KRB5_CONFIG=$(ls -latr /tmp/devservices-krb*.conf | tail -n 1 | awk '{print $9}') curl --negotiate --trace-time --trace-ids --trace target/log.log --user alice@EXAMPLE.COM --include --request POST --header "Content-Type: multipart/form-data" --form "data=@mvnw.cmd" http://localhost:8080/api/upload
KRB5_CONFIG=$(ls -latr /tmp/devservices-krb*.conf | tail -n 1 | awk '{print $9}') curl --negotiate --user alice@EXAMPLE.COM --include --request POST --header "Content-Type: multipart/form-data" --form "data=@mvnw.cmd" http://localhost:8080/api/upload

KRB5_CONFIG=$(ls -latr /tmp/devservices-krb*.conf | tail -n 1 | awk '{print $9}') kdestroy
KRB5_CONFIG=$(ls -latr /tmp/devservices-krb*.conf | tail -n 1 | awk '{print $9}') klist


export QUARKUS_KERBEROS_ENABLED=true 
export QUARKUS_KERBEROS_DEBUG=true 
export QUARKUS_KERBEROS_KEYTAB_PATH=/home/mickael/tmp/http.deborah.jobjects.org.keytab
export QUARKUS_KERBEROS_SERVICE_PRINCIPAL_NAME="HTTP/deborah.jobjects.org"
export QUARKUS_KERBEROS_SERVICE_PRINCIPAL_REALM=JOBJECTS.ORG
~~~

Ouverture de session sur le client freeipa :

~~~bash
printf "HelloWorld\x21\n" | kinit admin
klist
ipa dnszone-add 1.168.192.in-addr.arpa.
# ou comme ça:
# ipa dnszone-add --name-from-ip=192.168.1.0/24

dig @idm deborah.jobjects.org +short
dig @idm -x 192.168.1.18 +short

DOMAIN=JOBJECTS.ORG
NAMESERVER=idm
for i in _ldap._tcp _kerberos._tcp _kerberos._udp _kerberos-master._tcp _kerberos-master._udp _ntp._udp; do echo ""; dig @${NAMESERVER} ${i}.${DOMAIN} srv +nocmd +noquestion +nocomments +nostats +noaa +noadditional +noauthority; done | egrep -v "^;" | egrep _
~~~

~~~bash
# ipa dnsrecord-add jobjects.org deborah --a-rec 192.168.1.18 --a-create-reverse
ipa dnsrecord-add 1.168.192.in-addr.arpa. 18 --ptr-rec=deborah.jobjects.org.
ipa dnsrecord-add jobjects.org deborah --a-rec 192.168.1.18

[root@idm ~]# dig @idm -x 192.168.1.18 +short
deborah.jobjects.org.
[root@idm ~]# dig @idm deborah.jobjects.org +short
192.168.1.18
[root@idm ~]# ipa host-add deborah.jobjects.org
---------------------------------
Added host "deborah.jobjects.org"
---------------------------------
  Host name: deborah.jobjects.org
  Principal name: host/deborah.jobjects.org@JOBJECTS.ORG
  Principal alias: host/deborah.jobjects.org@JOBJECTS.ORG
  Password: False
  Keytab: False
  Managed by: deborah.jobjects.org
[root@idm ~]# ipa service-add HTTP/deborah.jobjects.org
------------------------------------------------------
Added service "HTTP/deborah.jobjects.org@JOBJECTS.ORG"
------------------------------------------------------
  Principal name: HTTP/deborah.jobjects.org@JOBJECTS.ORG
  Principal alias: HTTP/deborah.jobjects.org@JOBJECTS.ORG
  Managed by: deborah.jobjects.org
[root@idm ~]# ipa-getkeytab -s idm.jobjects.org idm -p HTTP/deborah.jobjects.org -k ./http.deborah.jobjects.org.keytab
Keytab successfully retrieved and stored in: ./http.deborah.jobjects.org.keytab
~~~

ipa-getcert request -r -f /etc/ssl/certs/smtp.crt -k /etc/ssl/certs/smtp.key -K smtp/mail.jobjects.org

~~~bash
mickael@deborah:~$ cat /etc/hosts | grep idm
192.168.122.100 idm.jobjects.org idm
mickael@deborah:~/tmp$ scp root@idm.jobjects.org:/root/http.deborah.jobjects.org.keytab .
The authenticity of host 'idm.jobjects.org (192.168.122.100)' can't be established.
ED25519 key fingerprint is SHA256:t8/ARrLySaFGB9fZ0zJThJk7VGnTsGTpTdaT//j/g+Q.
This host key is known by the following other names/addresses:
    ~/.ssh/known_hosts:17: [hashed name]
    ~/.ssh/known_hosts:20: [hashed name]
Are you sure you want to continue connecting (yes/no/[fingerprint])? yes
Warning: Permanently added 'idm.jobjects.org' (ED25519) to the list of known hosts.
(root@idm.jobjects.org) Password: 
http.deborah.jobjects.org.keytab                                                         100%  358   549.4KB/s   00:00
mickael@deborah:~/tmp$ cd ~/Documents/kerberos/kdownload/
mickael@deborah:~/Documents/kerberos/kdownload$ quarkus build --native -Dquarkus.native.container-build=true -Dquarkus.native.container-runtime=podman
...

export QUARKUS_KERBEROS_ENABLED=true
export QUARKUS_KERBEROS_DEBUG=true
export QUARKUS_KERBEROS_KEYTAB_PATH=/home/mickael/tmp/http.deborah.jobjects.org.keytab
export QUARKUS_KERBEROS_SERVICE_PRINCIPAL_NAME="HTTP/deborah.jobjects.org"
export QUARKUS_KERBEROS_SERVICE_PRINCIPAL_REALM=JOBJECTS.ORG
-Dquarkus.kerberos.debug=true -Dquarkus.kerberos.keytab-path=/home/mickael/tmp/http.deborah.jobjects.org.keytab -Dquarkus.kerberos.service-principal-name="HTTP/deborah.jobjects.org" -Dquarkus.kerberos.service-principal-realm=JOBJECTS.ORG -Dquarkus.kerberos.enabled=true 
/home/mickael/Documents/kerberos/kdownload/target/kdownload-1.0-SNAPSHOT-runner -Dquarkus.kerberos.debug=true -Dquarkus.kerberos.keytab-path=/home/mickael/tmp/http.deborah.jobjects.org.keytab -Dquarkus.kerberos.service-principal-name="HTTP/deborah.jobjects.org" -Dquarkus.kerberos.service-principal-realm=JOBJECTS.ORG -Dquarkus.kerberos.devservices.realm=JOBJECTS.ORG -Dquarkus.kerberos.enabled=true -D quarkus.kerberos.devservices.enabled=false
__  ____  __  _____   ___  __ ____  ______ 
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/ 
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \   
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/   
2024-11-29 17:41:45,246 INFO  [io.quarkus] (main) kdownload 1.0-SNAPSHOT native (powered by Quarkus 3.16.4) started in 0.050s. Listening on: http://0.0.0.0:8080
2024-11-29 17:41:45,246 INFO  [io.quarkus] (main) Profile prod activated. 
2024-11-29 17:41:45,246 INFO  [io.quarkus] (main) Installed features: [cdi, config-yaml, kerberos, rest, rest-client, security, smallrye-context-propagation, vertx]

mickael@deborah:~/Documents/kerberos/kdownload$ kinit mickael
Password for mickael@JOBJECTS.ORG: 
mickael@deborah:~/Documents/kerberos/kdownload$ curl --negotiate --user mickael@JOBJECTS.ORG --include --request POST --header "Content-Type: multipart/form-data" --form "data=@mvnw.cmd" http://deborah.jobjecst.org:8080/api/upload
curl --negotiate --user mickael@JOBJECTS.ORG http://deborah.jobjecst.org:8080/api/users/me
~~~bash

~~~bash
unset QUARKUS_KERBEROS_ENABLED QUARKUS_KERBEROS_DEBUG QUARKUS_KERBEROS_KEYTAB_PATH QUARKUS_KERBEROS_SERVICE_PRINCIPAL_NAME QUARKUS_KERBEROS_SERVICE_PRINCIPAL_REALM
==================================
~~~

~~~bash
quarkus build --native -Dquarkus.container-image.build=true -Dquarkus.native.container-runtime=podman -Dquarkus.native.additional-build-args="--static","--libc=musl"
quarkus build --native -Dquarkus.container-image.build=true -Dquarkus.native.container-runtime=podman
podman image ls | grep kdownload

firewall-cmd --zone=public --add-port=8080/tcp --permanent
firewall-cmd --complete-reload
firewall-cmd --list-all

sudo apt install build-essential musl-tools libz-dev zlib1g-dev

quarkus build --native -Dquarkus.container-image.build=true --no-tests
~~~

~~~bash
VERSION=1.0.3
# build
podman build --tag mpatron/kdownload:${VERSION} --file ./Dockerfile
podman save localhost/mpatron/kdownload:${VERSION} -o /mnt/c/Temp/kdownload.tar.gz
# lancement
podman run --rm --detach --replace --cap-add=NET_RAW --name kdownload  --volume /home/mickael/tmp:/work/keytabs --volume ./podman/krb5.conf:/etc/krb5.conf --publish 8080:8080 --env KEYTAB_FILE=/work/keytabs/http.deborah.jobjects.org.keytab mpatron/kdownload:${VERSION}
# Eteindre
podman stop kdownload 
# show log
podman logs kdownload
# debug
podman exec -it kdownload /bin/bash
~~~

~~~bash
kinit mickael@JOBJECTS.ORG
curl --verbose --negotiate http://deborah.jobjects.org:8080/api/users/me
# Quand on envoie un fichier (ici 'mvnw.cmd'), il sera mis dans /tmp/$USER.keytab du containener
curl --verbose --negotiate --include --request POST --header "Content-Type: multipart/form-data" --form "data=@mvnw.cmd" http://localhost:8080/api/upload
curl --verbose --negotiate --include --request POST --header "Content-Type: multipart/form-data" --form "data=@$(klist | grep FILE | cut -d : -f 3)" http://deborah.jobjects.org:8080/api/upload
~~~
