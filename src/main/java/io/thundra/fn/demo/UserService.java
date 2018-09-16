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
public class UserService {

    private final Logger logger = Logger.getLogger(getClass());
    private final UserRepository userRepository = new UserRepository();

    public User getUser(String username) {
        logger.info("Getting user with username: " + username);

        HelloUtils.doProcess(50);

        return userRepository.getUser(username);
    }

}
