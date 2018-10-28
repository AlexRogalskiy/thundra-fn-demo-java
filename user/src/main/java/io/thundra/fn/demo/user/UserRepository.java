package io.thundra.fn.demo.user;

import io.thundra.agent.core.util.PropertyUtils;
import io.thundra.agent.trace.instrument.config.Traceable;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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

    private static final String JDBC_DRIVER_CLASSNAME = PropertyUtils.getStringProperty("jdbc.driverClassName");
    private static final String JDBC_URL = PropertyUtils.getStringProperty("jdbc.url");
    private static final String JDBC_USERNAME = PropertyUtils.getStringProperty("jdbc.username");
    private static final String JDBC_PASSWORD = PropertyUtils.getStringProperty("jdbc.password");

    private final Logger logger = Logger.getLogger(getClass());
    private final Connection connection;

    public UserRepository() {
        try {
            Class.forName(JDBC_DRIVER_CLASSNAME);
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(String username) {
        logger.info("Searching user with username: " + username);

        UserUtils.doProcess(50);

        try {
            ResultSet resultSet =
                    connection.createStatement().
                            executeQuery("SELECT * FROM Users WHERE username='" + username + "'");
            if (resultSet.next()) {
                logger.info("Found user with username: " + username);

                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Date birthday = resultSet.getDate("birthday");
                String language = resultSet.getString("lang");
                return new User(username, firstName, lastName, birthday, language);
            } else {
                logger.info("No user found with username: " + username);

                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
