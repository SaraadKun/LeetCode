package com.saraad.thread.demo;

/**
 * @Title: AbstractStorage
 * @Package:com.saraad.thread.demo
 * @Description:
 * @author: saraad
 * @date: 2021/9/23 10:03 上午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public abstract class AbstractStorage {

    abstract void produce(int num);

    abstract void consume(int num);

}
