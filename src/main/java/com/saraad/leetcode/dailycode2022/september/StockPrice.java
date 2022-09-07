package com.saraad.leetcode.dailycode2022.september;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/stock-price-fluctuation/
 * @Date: 05-09-2022 12:37
 */
class StockPrice {

    int max = 0;
    HashMap<Integer, Integer> t = new HashMap<>();
    TreeMap<Integer, Integer> p = new TreeMap<>();

    public StockPrice() {
    }

    public void update(int timestamp, int price) {
        Integer prev = t.getOrDefault(timestamp, 0);
        if (prev > 0) {
            Integer cnt = p.get(prev);
            if (cnt == 1) {
                p.remove(prev);
            } else {
                p.put(prev, cnt - 1);
            }
        }
        t.put(timestamp, price);
        p.put(price, p.getOrDefault(price, 0) + 1);
        max = Math.max(max, timestamp);
    }

    public int current() {
        return t.get(max);
    }

    public int maximum() {
        return p.lastKey();
    }

    public int minimum() {
        return p.firstKey();
    }

    public static void main(String[] args) {
        StockPrice obj = new StockPrice();
        //["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
        //[[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
        obj.update(1, 10);
        obj.update(2, 5);
        System.out.println(obj.current());
        System.out.println(obj.maximum());
        obj.update(1,3);
        System.out.println(obj.maximum());
        obj.update(4,2);
        System.out.println(obj.minimum());
    }
}