package com.saraad.thread.demo;

import com.saraad.thread.util.ThreadUtil;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 28-06-2022 21:38
 */

public class ThreadState {
    public static void main(String[] args) {
//        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        new Thread(new SyncTimeWaiting(), "SyncTimeWaiting").start();
        new Thread(new Waiting(), "WaitingThread").start();
        new Thread(new Notify(), "NotifyThread").start();
//        new Thread(new Blocked(), "BlockedThread-1").start();
//        new Thread(new Blocked(), "BlockedThread-2").start();
    }

    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.err.println(Thread.currentThread().getName() + " : TimeWaiting start");
                ThreadUtil.second(100L);
                System.err.println(Thread.currentThread().getName() + " : TimeWaiting end");
            }
        }
    }

    static class Waiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        System.err.println(Thread.currentThread().getName() + " : Waiting start");
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.err.println(Thread.currentThread().getName() + " : Waiting end");
            }
        }
    }

    static class Blocked implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Blocked.class) {
                    System.err.println(Thread.currentThread().getName() + " : Blocked start");
                    while (true) {
                        ThreadUtil.second(100L);
                    }
//                    System.err.println(Thread.currentThread().getName() + " : Blocked end");

                }
            }
        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        System.err.println(Thread.currentThread().getName() + " : Notify start");
                        ThreadUtil.second(1L);
                        Waiting.class.notifyAll();
                        System.err.println(Thread.currentThread().getName() + " : Notify END");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ThreadUtil.second(1L);
//                System.err.println(Thread.currentThread().getName() + " : Notify end");
            }
        }
    }

    static class SyncTimeWaiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        System.err.println(Thread.currentThread().getName() + " : SyncTimeWaiting start");
                        ThreadUtil.second(6L);
                        System.err.println(Thread.currentThread().getName() + " : SyncTimeWaiting end");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ThreadUtil.second(1L);
            }
        }
    }

}
