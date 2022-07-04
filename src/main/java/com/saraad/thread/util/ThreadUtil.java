package com.saraad.thread.util;

import java.util.concurrent.TimeUnit;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 24-06-2022 17:48
 */

public class ThreadUtil {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
