package io.thundra.fn.demo.hello;

import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

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

    public static String readAll(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
