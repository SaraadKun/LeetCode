package com.saraad.thread.demo;

import java.util.LinkedList;

/**
 * @Title: Storage1
 * @Package:com.saraad.thread.demo
 * @Description:
 * @author: saraad
 * @date: 2021/9/23 10:05 上午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class Storage1 extends AbstractStorage {

    private final int MAX_SIZE = 100;

    private final LinkedList<Object> list;

    public Storage1() {
        this.list = new LinkedList<>();
    }

    @Override
    public void produce(int num) {
        synchronized (list) {
            while (list.size() + num > MAX_SIZE) {
                try {
                    System.out.printf("线程:%s  当前容量为: %d, 欲生产数量为: %d, 大于最大容量: %d, 暂停生产\n",
                            Thread.currentThread().getName(), list.size(), num, MAX_SIZE);
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("线程:%s  当前容量为: %d, 欲生产数量为: %d, 最大容量: %d, 开始生产\n",
                    Thread.currentThread().getName(), list.size(), num, MAX_SIZE);
            for (int i = 0; i < num; i++) {
                list.add(new Object());
            }
            list.notifyAll();
            System.out.printf("线程: %s, 生产完毕, 剩余库存为: %d\n", Thread.currentThread().getName(), list.size());
        }
    }

    @Override
    public void consume(int num) {
        synchronized (list) {
            while (list.size() < num) {
                try {
                    System.out.printf("线程:%s  当前库存为: %d, 欲消费数量为: %d, 库存不足, 暂停消费\n",
                            Thread.currentThread().getName(), list.size(), num);
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("线程:%s  当前库存为: %d, 欲消费数量为: %d, 开始消费\n",
                    Thread.currentThread().getName(), list.size(), num);
            for (int i = 0; i < num; i++) {
                list.poll();
            }
            list.notifyAll();
            System.out.printf("线程: %s, 消费完毕, 剩余库存为: %d\n", Thread.currentThread().getName(), list.size());
        }
    }
}
