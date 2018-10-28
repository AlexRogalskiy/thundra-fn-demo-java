#!/bin/bash

HOST_IP=`ipconfig getifaddr en0`
REDIS_URL="$HOST_IP"
JDBC_URL="jdbc:mysql://$HOST_IP:3306/test"
USER_API_URL="http://$HOST_IP:8080/r/thundra-fn-demo/user"
TRANSLATE_API_URL="http://$HOST_IP:8080/r/thundra-fn-demo/translate"

fn  --verbose deploy --local --all
fn config route thundra-fn-demo /user redis.url "$REDIS_URL"
fn config route thundra-fn-demo /user jdbc.url "$JDBC_URL"
fn config route thundra-fn-demo /hello user.api.url "$USER_API_URL"
fn config route thundra-fn-demo /hello translate.api.url "$TRANSLATE_API_URL"
