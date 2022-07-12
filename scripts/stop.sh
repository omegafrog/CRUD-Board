#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

IDLE_PORT=$(find_idel_port)

ecoh "> $IDLE_PORT pid:"
IDLE_PID=$(lsof -ti tcp:${IDLE_PORT})

if [ -z ${IDLE_PID} ]
then
  echo "> no running application"
else
  echo ">kill -15 $IDLE_PID"
  kill -15 ${IDLE_PID}
  sleep 5
fi
