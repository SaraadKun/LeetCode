package com.saraad.leetcode.temp;

import com.carrotsearch.sizeof.RamUsageEstimator;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: TT2
 * @Package:com.saraad.leetcode.temp
 * @Description:
 * @author: saraad
 * @date: 2021/6/8 6:26 下午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class TT2 {

    private static InheritableThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set(123);
        System.out.printf("name: %s, value : %d\n",Thread.currentThread().getName(), threadLocal.get());
        CompletableFuture.supplyAsync(threadLocal::get)
                .thenApplyAsync((i) -> {
                    System.out.printf("name: %s, value : %d\n",Thread.currentThread().getName(), i);
                    int value = i + 10;
                    threadLocal.set(value);
                    return value;
                })
                .thenAccept((i) -> {
                    System.out.printf("name: %s, value : %d\n",Thread.currentThread().getName(), i);
                })
                .join();
        System.out.println("end");
//        Integer i = 1000;
//        Integer i = 0xb8;
//        System.out.println(RamUsageEstimator.sizeOf(i));
//        System.out.println(i);


    }

}
