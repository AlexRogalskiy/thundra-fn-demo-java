package io.thundra.fn.demo;

import io.thundra.agent.trace.instrument.config.Traceable;
import org.apache.log4j.Logger;

/**
 * @author serkan
 */
@Traceable(
        traceArguments = true,
        traceArgumentNames = true,
        serializeArgumentsAsJson = true,
        traceReturnValue = true,
        serializeReturnValueAsJson = true
)
public class HelloService {

    private final Logger logger = Logger.getLogger(getClass());
    private final UserService userService = new UserService();

    public String sayHello(String username) {
        logger.info("Looking user with username: " + username);

        HelloUtils.doProcess(10);

        User user = userService.getUser(username);
        if (user == null) {
            return "Hello stranger!";
        } else {
            return "Hello " + user.getFirstName() + " " + user.getLastName();
        }
    }

}
