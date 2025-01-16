#!/bin/bash
VERSION=1.0.3
git fetch --tags
git random
podman build --tag mpatron/kdownload:${VERSION} --file ./Dockerfile
podman save localhost/mpatron/kdownload:${VERSION} -o /mnt/c/Temp/kdownload-${VERSION}.tar
echo scp /mnt/c/Temp/kdownload-${VERSION}.tar mpatron@10.6.193.182:/home/mpatron/Documents
