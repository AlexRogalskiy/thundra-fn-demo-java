#!/bin/bash

HOST_IP=`ipconfig getifaddr en0`
echo "Saying hello to user with username 'sozal' ..."
HELLO_RESULT=`curl -d "sozal" http://$HOST_IP:8080/r/thundra-fn-demo/hello`
echo "$HELLO_RESULT"
