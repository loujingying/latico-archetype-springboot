#!/bin/bash
workdir=$(cd $(dirname $0); pwd)

PID=`ps -ef|grep ${workdir}|grep -v grep|awk '{print $2}'`

if [[ -z ${PID} ]];then
    echo "Execute:sh bin/startup.sh"
    sh bin/startup.sh
    sleep 1
    PID=`ps -ef|grep ${workdir}|grep -v grep|awk '{print $2}'`
    echo "PID=$PID the program <<${workdir}>> starting..."
else
    echo "PID=$PID the program <<${workdir}>> has been running.Please stop it firstly."
fi