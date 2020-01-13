#!/bin/bash
#要访问的IP、端口、上下文路径，上下文路径的前缀是/开头，后缀不带/
ip=127.0.0.1
port=8080
contextPath=/

# 读取application.properties配置文件中的端口
for line in `cat ./config/application.properties`
do
    if [[ ${line} == appPort=* ]]
    then
        port=${line:8}

    elif [[ ${line} == appContextPath=* ]]
    then
        contextPath=${line:15}

    fi
done

echo "------------------------------------------------------"
echo "curl -i --max-time 10 \"http://${ip}:${port}${contextPath}/version\""
echo "------------------------------------------------------"
# 执行http访问
curl -i --max-time 10 "http://${ip}:${port}${contextPath}/version"
echo ""
echo "------------------------------------------------------"