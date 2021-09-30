package com.saraad.thread.demo;

import java.util.Spliterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title: TestPubSub
 * @Package:com.saraad.thread.demo
 * @Description:
 * @author: saraad
 * @date: 2021/9/23 10:23 上午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class TestPubSub {

    private static final ExecutorService executorService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    public static void main(String[] args) {
//        Runtime.getRuntime().addShutdownHook(new Thread(TestPubSub.executorService::shutdown));
        Storage1 storage = new Storage1();
        Producer p1 = new Producer(storage, 50);
        Producer p2 = new Producer(storage, 70);
        Producer p3 = new Producer(storage, 80);
        Consumer c1 = new Consumer(storage, 30);
        Consumer c2 = new Consumer(storage, 40);
        Consumer c3 = new Consumer(storage, 20);
        Consumer c4 = new Consumer(storage, 50);
        Consumer c5 = new Consumer(storage, 20);
        Consumer c6 = new Consumer(storage, 30);
        executorService.submit(p1::produce);
        executorService.submit(p2::produce);
        executorService.submit(p3::produce);
        executorService.submit(c1::consume);
        executorService.submit(c2::consume);
        executorService.submit(c3::consume);
        executorService.submit(c4::consume);
        executorService.submit(c5::consume);
        executorService.submit(c6::consume);

        System.out.println("任务提交完成");
        executorService.shutdown();

    }

}
