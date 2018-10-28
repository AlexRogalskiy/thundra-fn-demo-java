package io.thundra.fn.demo.hello;

import io.thundra.agent.fn.core.handler.FnContext;
import io.thundra.agent.fn.core.handler.request.FnRequestHandler;

/**
 * @author serkan
 */
public class HelloFunction implements FnRequestHandler<String, String> {

    private final HelloService helloService = new HelloService();

    @Override
    public String doHandleRequest(String input, FnContext context) {
        return helloService.sayHello(input.trim());
    }

}
