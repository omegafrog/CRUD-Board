#!/bin/bash

REPOSITORY=/home/ec2-user/workspace
PROJECT_NAME=CRUD-Board

cd $REPOSITORY

echo "> Copy build file"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> Check running app pid"

CURRENT_PID=$(pgrep -fl CRUD-Board | grep jar | awk '{print $1}')

echo "> Current runing app : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
        echo "> No running app"
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5
fi

echo "> deploy new app"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)
echo "> JAR Name: $JAR_NAME"

chmod +x $JAR_NAME

echo "> $REPOSITORY/$JAR_NAME"

nohup java -jar \
        -Dspring.config.location=classpath:/application-real.properties,/home/ec2-user/workspace/application-oauth.properties,classpath:/application-real-db.properties \
        -Dspring.profiles.active=real \
        $JAR_NAME > $REPOSITORY/nohub.out 2>&1 &