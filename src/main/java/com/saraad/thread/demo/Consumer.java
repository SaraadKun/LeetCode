package com.saraad.thread.demo;

/**
 * @Title: Consumer
 * @Package:com.saraad.thread.demo
 * @Description:
 * @author: saraad
 * @date: 2021/9/23 10:22 上午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class Consumer {

    private AbstractStorage storage;

    private int num;

    public Consumer(AbstractStorage storage, int num) {
        this.storage = storage;
        this.num = num;
    }

    public void consume() {
        storage.consume(num);
    }
}
