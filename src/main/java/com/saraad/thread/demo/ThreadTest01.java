package com.saraad.thread.demo;

import com.saraad.thread.util.ThreadUtil;

import java.time.LocalDate;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 24-06-2022 17:36
 */

public class ThreadTest01 {

    static final Object obj = new Object();

    public static void main(String[] args) throws Exception{
//        Thread t1 = new Thread(() ->{
//            synchronized (obj) {
//                ThreadUtil.sleep(2000L);
//                System.out.println(Thread.currentThread().getName() + ": " + LocalDate.now());
//            }
//        } );
//        t1.start();
//        synchronized (obj) {
//            System.out.println(Thread.currentThread().getName() + ": start synchronized");
//            t1.join();
//        }
        Thread t2 = new Thread(() ->{
             synchronized (Thread.currentThread()) {
                 ThreadUtil.sleep(2000L);
                 System.out.println(Thread.currentThread().getName() + ": " + LocalDate.now());
             }
        });
        t2.start();
        synchronized (t2) {
            System.out.println(Thread.currentThread().getName() + ": start synchronized");
            t2.join();
        }
        System.out.println(Thread.currentThread().getName() + ": end");
    }

}
