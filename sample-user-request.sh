#!/bin/bash

HOST_IP=`ipconfig getifaddr en0`
echo "Getting user with username 'sozal' ..."
USER_RESULT=`curl -d "sozal" http://$HOST_IP:8080/r/thundra-fn-demo/user`
echo "Retrieved user: $USER_RESULT"
