#!/bin/bash

fn config route thundra-fn-demo /hello thundra.agent.trace.instrument.traceableConfig "io.thundra.fn.demo.hello.HelloService.sayHello[traceArguments=true,traceArgumentNames=true,serializeArgumentsAsJson=true,traceReturnValue=true,serializeReturnValueAsJson=true,traceLineByLine=true,traceLinesWithSource=true,traceLocalVariables=true]"
