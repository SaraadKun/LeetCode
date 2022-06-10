package com.saraad.leetcode.bean;

import lombok.Data;

@Data
public class Pair<K, V> {
    K key;
    V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public static <K, V> Pair of(K key, V value){
        return new Pair(key, value);
    }
}