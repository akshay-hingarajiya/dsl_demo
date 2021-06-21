#!/bin/sh

docker run \
-u root \
--privileged
--rm \
-d \
-p 8080:8080 \
-p 50000:50000 \
-v jenkins-data:/var/jenkins_home \
-v /var/run/docker.sock:/var/run/docker.sock \
-v $(which docker):/usr/bin/docker \
#jenkinsci/blueocea
jenkins/jenkins:latest
