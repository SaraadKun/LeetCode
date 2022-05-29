package com.saraad.thread.demo;

/**
 * @Title: Producer
 * @Package:com.saraad.thread.demo
 * @Description:
 * @author: saraad
 * @date: 2021/9/23 10:18 上午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class Producer {

    private AbstractStorage storage;

    private int num;

    public Producer(AbstractStorage storage, int num) {
        this.storage = storage;
        this.num = num;
    }

    public void produce() {
        storage.produce(num);
    }

}
