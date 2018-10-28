package io.thundra.fn.demo.user;

import io.thundra.agent.fn.core.handler.FnContext;
import io.thundra.agent.fn.core.handler.request.FnRequestHandler;

/**
 * @author serkan
 */
public class UserFunction implements FnRequestHandler<String, User> {

    private final UserService userService = new UserService();

    @Override
    public User doHandleRequest(String request, FnContext context) {
        return userService.getUser(request);
    }

}
