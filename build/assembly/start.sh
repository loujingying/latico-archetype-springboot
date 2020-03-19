#!/bin/bash
# ${project.build.finalName}

workdir=$(cd $(dirname $0); pwd)
export javapath=$(cat ${workdir}/javapath.cfg)
export programname=$(cat ${workdir}/programname.cfg)

PID=`ps -ef|grep ${workdir}|grep ${programname}|grep -v grep|awk '{print $2}'`

if [[ -z ${PID} ]];then

    logdir="logs"
    if [[ ! -d ${logdir} ]];then
      mkdir ${logdir}
    fi

    ${javapath} -Dprogramname=${programname} -Dprogrampath=${workdir} -Dloader.path=lib -jar lib/${programname}.jar >/dev/null 2>logs/err.log &
    sleep 1
    PID=`ps -ef|grep ${workdir}|grep ${programname}|grep -v grep|awk '{print $2}'`
    echo "PID=$PID the program <<${workdir} && ${programname}>> starting..."
else
    echo "PID=$PID the program <<${workdir} && ${programname}>> has been running.Please stop it firstly."
fi