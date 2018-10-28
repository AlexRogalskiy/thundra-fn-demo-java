package io.thundra.fn.demo.user;

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

    private final UserCache userCache = new UserCache();
    private final UserRepository userRepository = new UserRepository();

    public User getUser(String username) {
        logger.info("Getting user with username: " + username);

        User user = userCache.get(username);
        if (user != null) {
            return user;
        }

        user = userRepository.getUser(username);
        if (user != null) {
            userCache.put(username, user);
        }

        return user;
    }

}
