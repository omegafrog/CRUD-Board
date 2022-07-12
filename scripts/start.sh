#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh


REPOSITORY=/home/ec2-user/workspace
PROJECT_NAME=CRUD-Board

cd $REPOSITORY

echo "> Copy build file"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> deploy new app"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)
echo "> JAR Name: $JAR_NAME"

chmod +x $JAR_NAME
IDLE_PROFILE=$(find_idle_profile)

echo "> $REPOSITORY/$JAR_NAME"

nohup java -jar \
        -Dspring.config.location=classpath:/application.properties,classpath:/application-$IDLE_PROFILE.properties,/home/ec2-user/workspace/application-oauth.properties,classpath:/application-real-db.properties \
        -Dspring.profiles.active=$IDLE_PROFILE \
        $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &