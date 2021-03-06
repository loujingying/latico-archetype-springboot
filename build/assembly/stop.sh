#!/bin/bash
# ${project.build.finalName}

workdir=$(cd $(dirname $0); pwd)
export programname=$(cat ${workdir}/programname.cfg)

PID=`ps -ef|grep ${workdir}|grep ${programname}|grep -v grep|awk '{print $2}'`
if [[ -z ${PID} ]];then
    echo "The program <<${workdir} && ${programname}>> has been stoped."
else
    kill ${PID}
    echo "Execute:kill ${PID}, the program <<${workdir} && ${programname}>> is being killed, please wait for 10 seconds..."
    sleep 10

   PID2=`ps -ef|grep ${workdir}|grep ${programname}|grep -v grep|awk '{print $2}'`
    if [[ ${PID2} ]];then
        kill -9 ${PID2}
        echo "Execute:kill -9 ${PID2}, the program <<${workdir} && ${programname}>> finished."
    fi
    echo "Stop PID=${PID} the program <<${workdir} && ${programname}>> finished."
fi