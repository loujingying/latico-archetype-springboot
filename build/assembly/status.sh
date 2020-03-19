#!/bin/bash
# {project.build.finalName}

workdir=$(cd $(dirname $0); pwd)
export programname=$(cat ${workdir}/programname.cfg)

PID=`ps -ef|grep ${workdir}|grep ${programname}|grep -v grep|awk '{print $2}'`
if [[ -z ${PID} ]];then
    echo "The program <<${workdir} && ${programname}>> has been stoped."
else
    echo "==========="
    echo "|PID:${PID}|"
    echo "==========="
    echo `ps -ef|grep ${workdir}|grep ${programname}|grep -v grep`
fi