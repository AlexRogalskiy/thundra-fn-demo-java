package io.thundra.fn.demo;

import java.util.Random;

/**
 * @author serkan
 */
public final class HelloUtils {

    private static final Random random = new Random();

    private HelloUtils() {
    }

    public static void doProcess(int maxMillis) {
        try {
            Thread.sleep(maxMillis / 2 + (random.nextInt(maxMillis / 2)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
