package io.thundra.fn.demo;

import io.thundra.agent.trace.instrument.config.Traceable;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
public class UserRepository {

    private final Logger logger = Logger.getLogger(getClass());
    private final Map<String, User> userMap =
            new HashMap<String, User>() {{
                put("sozal",
                    new User(
                            "sozal",
                            "Serkan",
                            "ÖZAL",
                            new Date(1986 - 1900, 9 - 1, 15)));
            }};

    public User getUser(String username) {
        logger.info("Searching user with username: " + username);

        HelloUtils.doProcess(100);

        return userMap.get(username);
    }

}
