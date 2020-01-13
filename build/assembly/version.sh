#!/bin/bash
#要访问的IP和端口
ip=127.0.0.1
port=8080

# 执行http访问
curl -i --max-time 10 "http://${ip}:${port}/version"