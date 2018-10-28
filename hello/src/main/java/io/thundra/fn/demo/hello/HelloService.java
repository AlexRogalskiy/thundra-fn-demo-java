package io.thundra.fn.demo.hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.thundra.agent.core.util.PropertyUtils;
import io.thundra.agent.trace.instrument.config.Traceable;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import java.io.Serializable;
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
public class HelloService {

    private static final String USER_API_URL = PropertyUtils.getStringProperty("user.api.url");
    private static final String TRANSLATE_API_URL = PropertyUtils.getStringProperty("translate.api.url");

    private final Logger logger = Logger.getLogger(getClass());
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String sayHello(String username) {
        logger.info("Looking user with username: " + username);

        HelloUtils.doProcess(10);

        try {
            User user = getUser(username);
            if (user == null) {
                return "Hello stranger!";
            } else {
                String translatedHelloText = translateHelloMessage(user.getLanguage());
                return translatedHelloText + " " + user.getFirstName() + " " + user.getLastName();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private User getUser(String username) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(USER_API_URL);
        post.setEntity(new StringEntity(username));
        HttpResponse response = httpClient.execute(post);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.getEntity().getContent(), User.class);
    }

    private String translateHelloMessage(String language) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(TRANSLATE_API_URL);
        StringEntity requestEntity =
                new StringEntity(
                        objectMapper.writeValueAsString(
                                new TranslateRequest("Hello", "EN", language)),
                        ContentType.APPLICATION_JSON);
        post.setEntity(requestEntity);
        HttpResponse response = httpClient.execute(post);
        return HelloUtils.readAll(response.getEntity().getContent());
    }

    public static class User implements Serializable {

        private String username;
        private String firstName;
        private String lastName;
        private Date birthday;
        private String language;

        public User() {
        }

        public User(String username, String firstName, String lastName, Date birthday, String language) {
            this.username = username;
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthday = birthday;
            this.language = language;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", birthday=" + birthday +
                    ", language='" + language + '\'' +
                    '}';
        }

    }

    public static class TranslateRequest {

        private String toTranslate;
        private String sourceLanguage;
        private String destinationLanguage;

        public TranslateRequest() {
        }

        public TranslateRequest(String toTranslate, String sourceLanguage, String destinationLanguage) {
            this.toTranslate = toTranslate;
            this.sourceLanguage = sourceLanguage;
            this.destinationLanguage = destinationLanguage;
        }

        public String getToTranslate() {
            return toTranslate;
        }

        public void setToTranslate(String toTranslate) {
            this.toTranslate = toTranslate;
        }

        public String getSourceLanguage() {
            return sourceLanguage;
        }

        public void setSourceLanguage(String sourceLanguage) {
            this.sourceLanguage = sourceLanguage;
        }

        public String getDestinationLanguage() {
            return destinationLanguage;
        }

        public void setDestinationLanguage(String destinationLanguage) {
            this.destinationLanguage = destinationLanguage;
        }

    }

}
