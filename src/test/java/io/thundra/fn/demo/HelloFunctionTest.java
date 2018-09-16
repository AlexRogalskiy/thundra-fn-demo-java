package io.thundra.fn.demo;

import com.fnproject.fn.testing.*;
import org.junit.*;

import static org.junit.Assert.*;

@Ignore
public class HelloFunctionTest {

    @Rule
    public final FnTestingRule testing = FnTestingRule.createDefault();

    @Test
    public void shouldReturnGreetingSuccessfullyWhenNoInputIsGiven() {
        testing.givenEvent().enqueue();
        testing.thenRun(HelloFunction.class, "handleRequest");

        FnResult result = testing.getOnlyResult();
        assertEquals("Hello stranger!", result.getBodyAsString());
    }

    @Test
    public void shouldReturnGreetingSuccessfullyWhenInputIsGiven() {
        testing.givenEvent().withBody("sozal").enqueue();
        testing.thenRun(HelloFunction.class, "handleRequest");

        FnResult result = testing.getOnlyResult();
        assertEquals("Hello Serkan ÖZAL", result.getBodyAsString());
    }

}