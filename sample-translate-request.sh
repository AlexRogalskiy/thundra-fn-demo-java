#!/bin/bash

HOST_IP=`ipconfig getifaddr en0`
echo "Translating 'Hello' from 'EN' to 'TR' ..."
TRANSLATION_RESULT=`curl -H "Content-Type: application/json" -d '{"toTranslate":"Hello","sourceLanguage":"EN","destinationLanguage":"TR"}' http://$HOST_IP:8080/r/thundra-fn-demo/translate`
echo "Translated to '$TRANSLATION_RESULT'"
