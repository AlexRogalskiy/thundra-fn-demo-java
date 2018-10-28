package io.thundra.fn.demo.user;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.opsgenie.core.util.ExceptionUtil;
import io.thundra.agent.core.util.PropertyUtils;
import io.thundra.agent.trace.instrument.config.Traceable;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

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
public class UserCache {

    private static final String REDIS_URL = PropertyUtils.getStringProperty("redis.url");
    private static final String CACHE_NAME = "Users";
    private static final int TTL = 5 * 60; // 5 minutes

    private final Logger logger = Logger.getLogger(getClass());

    private final ObjectMapper objectMapper =
            new ObjectMapper().
                    configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false).
                    configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).
                    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).
                    enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

    private final Jedis jedis;

    public UserCache() {
        this.jedis = new Jedis(REDIS_URL);
    }

    public UserCache(String redisUrl) {
        this.jedis = new Jedis(redisUrl);
    }

    public User get(String username) {
        logger.trace(String.format("User with username %s will be retrieved from cache", username));

        UserUtils.doProcess(10);

        User user = null;

        try {
            String keyStr = objectMapper.writeValueAsString(CACHE_NAME + "::" + username);
            String valueStr = jedis.get(keyStr);
            if (valueStr == null) {
                return null;
            }
            user = objectMapper.readValue(valueStr, User.class);
        } catch (Throwable t) {
            ExceptionUtil.sneakyThrow(t);
        }

        logger.trace(String.format("User with username %s has been retrieved from cache: %s", username, user));

        return user;
    }

    public void put(String username, User user) {
        logger.trace(String.format("User with username %s will be put into cache: %s", username, user));

        UserUtils.doProcess(20);

        try {
            String keyStr = objectMapper.writeValueAsString(CACHE_NAME + "::" + username);
            String valueStr = objectMapper.writeValueAsString(user);
            jedis.set(keyStr, valueStr);
            jedis.expire(keyStr, TTL);
        } catch (Throwable t) {
            ExceptionUtil.sneakyThrow(t);
        }

        logger.trace(String.format("User with username %s has been put into cache: %s", username, user));
    }

}
