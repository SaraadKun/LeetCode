package com.saraad.thread.demo;

import com.saraad.thread.util.ThreadUtil;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 28-06-2022 23:15
 */

public class ThreadInterrupted {

    static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
//        Thread t1 = new Thread(() -> {
//            synchronized (obj) {
//                System.out.println(Thread.currentThread().getName() + ": start wait");
//                try {
//                    System.out.println(Thread.currentThread().getName() + ": start interrupted : " + Thread.currentThread().isInterrupted());
//                    obj.wait();
//                } catch (InterruptedException e) {
//                    System.out.println(Thread.currentThread().getName() + ": interrupted : " + Thread.currentThread().isInterrupted());
//                }
//            }
//
//        });
//        t1.start();
//        t1.interrupt();
//        ThreadUtil.second(1L);
//        synchronized (obj) {
//            System.out.println(Thread.currentThread().getName() + ": start notify");
//            obj.notify();
//        }
//        synchronized (obj) {
//            obj.notify();
//        }

        Thread t2 = new Thread(new InterruptDemo());
        t2.start();
        ThreadUtil.second(2);
        t2.interrupt();

        System.out.println(Thread.currentThread().getName() + ": end");
    }

    static class InterruptDemo implements Runnable {
        private volatile int cnt = 0;
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName() + ": isWorking : " + cnt);
                    TimeUnit.SECONDS.sleep(1);
                    cnt++;
                } catch (InterruptedException e) {
                    System.err.println(Thread.currentThread().getName() + ": interrupted : " + Thread.currentThread().isInterrupted());
                    break;
                }
            }
            System.out.println(Thread.currentThread().getName() + " : end");
        }
    }

}
