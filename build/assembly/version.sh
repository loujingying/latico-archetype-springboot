#!/bin/bash
#要访问的IP和端口
ip=127.0.0.1
port=8080

# 读取application.properties配置文件中的端口
for line in `cat ./config/application.properties`
do
    if [[ ${line} == appPort=* ]]
    then
        port=${line:8}
        break
    fi
done

echo "------------------------------------------------------"
echo "curl -i --max-time 10 \"http://${ip}:${port}/version\""
echo "------------------------------------------------------"
# 执行http访问
curl -i --max-time 10 "http://${ip}:${port}/version"
echo ""
echo "------------------------------------------------------"