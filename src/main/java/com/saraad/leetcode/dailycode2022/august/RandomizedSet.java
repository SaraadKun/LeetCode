package com.saraad.leetcode.dailycode2022.august;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * @Description: desc
 * @Author: Saraad
 * @Link: <a href="https://leetcode.cn/problems/FortPu/">...</a>
 * @Date: 03-08-2022 15:54
 */
public class RandomizedSet {

    //最多进行 2 * 105 次 insert ， remove 和 getRandom 方法调用
    int[] ids = new int[200010];
    Map<Integer, Integer> map = new HashMap<>();
    int p = 0;
    Random rd = new Random();
    /** Initialize your data structure here. */
    public RandomizedSet() {

    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        ids[p] = val;
        map.put(val, p++);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int idx = map.get(val);
        ids[idx] = ids[--p];
        map.put(ids[idx], idx);
        map.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return ids[rd.nextInt(p)];
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */