package io.thundra.fn.demo;

import io.thundra.agent.fn.core.handler.FnContext;
import io.thundra.agent.fn.core.handler.request.FnRequestHandler;

public class HelloFunction implements FnRequestHandler<String, String> {

    private final HelloService helloService = new HelloService();

    @Override
    public String doHandleRequest(String input, FnContext context) {
        return helloService.sayHello(input.trim());
    }

}
