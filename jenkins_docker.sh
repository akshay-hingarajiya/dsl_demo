#!/bin/sh

docker run \
-u root \
--rm \
-d \
-p 8080:8080 \
-p 50000:50000 \
-v jenkins-data:/var/jenkins_home \
-v tcp://127.0.0.1:2376:/var/run/docker.sock \
jenkinsci/blueocea
