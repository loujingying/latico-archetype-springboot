#!/bin/bash

workdir=$(cd $(dirname $0); pwd)

PID=`ps -ef|grep ${workdir}|grep -v grep|awk '{print $2}'`
if [[ -z ${PID} ]];then
    echo "The program <<${workdir}>> has been stoped."
else
    echo "==========="
    echo "|PID=${PID}|"
    echo "==========="
    echo `ps -ef|grep ${workdir}|grep -v grep`
fi