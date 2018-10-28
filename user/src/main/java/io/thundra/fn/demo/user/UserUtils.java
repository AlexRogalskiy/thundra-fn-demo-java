package io.thundra.fn.demo.user;

import java.util.Random;

/**
 * @author serkan
 */
public final class UserUtils {

    private static final Random random = new Random();

    private UserUtils() {
    }

    public static void doProcess(int maxMillis) {
        try {
            Thread.sleep(maxMillis / 2 + (random.nextInt(maxMillis / 2)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
