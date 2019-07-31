#!/bin/bash

workdir=$(cd $(dirname $0); pwd)

PID=`ps -ef|grep ${workdir}|grep -v grep|awk '{print $2}'`
if [[ -z ${PID} ]];then
    echo "The program <<${workdir}>> has been stoped."
else
    sh bin/shutdown.sh
    echo "Execute:sh bin/shutdown.sh, PID=${PID} the program <<${workdir}>> is being killed, please wait for 10 seconds..."
    sleep 10

   PID2=`ps -ef|grep ${workdir}|grep -v grep|awk '{print $2}'`
    if [[ ${PID2} ]];then
        kill -9 ${PID2}
        echo "Execute:kill -9 ${PID2}, the program <<${workdir}>> finished."
    fi
    echo "Stop PID=${PID} the program <<${workdir}>> finished."
fi