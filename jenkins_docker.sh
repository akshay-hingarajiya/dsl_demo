#!/bin/sh

docker run \
-u root \
--rm \
-d \
-p 8080:8080 \
-p 50000:50000 \
-v jenkins-data:/var/jenkins_home \
-v usr/local/bin/docker:/usr/bin/docker \
jenkinsci/blueocea
