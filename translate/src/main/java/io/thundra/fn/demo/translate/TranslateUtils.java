package io.thundra.fn.demo.translate;

import java.util.Random;

/**
 * @author serkan
 */
public final class TranslateUtils {

    private static final Random random = new Random();

    private TranslateUtils() {
    }

    public static void doProcess(int maxMillis) {
        try {
            Thread.sleep(maxMillis / 2 + (random.nextInt(maxMillis / 2)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
