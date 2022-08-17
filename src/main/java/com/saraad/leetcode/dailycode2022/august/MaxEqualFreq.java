package com.saraad.leetcode.dailycode2022.august;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 18-08-2022 05:48
 */

public class MaxEqualFreq {
    public int maxEqualFreq(int[] nums) {
        int n = nums.length, ans = 0;
        Map<Integer, Integer> map = new HashMap<>(), cnts = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int key = nums[i], val = map.getOrDefault(key, 0);
            map.put(key, val + 1);
            cnts.compute(val + 1, (k, v) -> v == null ? 1 : v + 1);
            cnts.compute(val, (k, v) -> v != null && v > 1 ? v - 1 : null);
            if (cnts.size() == 1) { //size为1时判断key是否为1或者key的值是否为1(只出现一次)
                for (Integer c : cnts.keySet()) {
                    ans = c == 1 || cnts.get(c) == 1 ? i + 1 : ans;
                }
            } else if (cnts.size() == 2) { //size为2时判断是否有一个key值为1且(key - 1 == key2 || key == 1)
                int pk = -1;
                for (Integer c : cnts.keySet()) {
                    if (cnts.get(c) == 1 && (c == pk + 1 || c == 1)) {
                        ans = i + 1;
                    }
                    pk = c;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        MaxEqualFreq freq = new MaxEqualFreq();
        Assert.assertEquals(7, freq.maxEqualFreq(new int[] {2,2,1,1,5,3,3,5}));
        Assert.assertEquals(2, freq.maxEqualFreq(new int[] {1,2}));
        Assert.assertEquals(7, freq.maxEqualFreq(new int[] {1,1,1,2,2,2,3,3,3}));
        Assert.assertEquals(5, freq.maxEqualFreq(new int[] {1,1,1,2,2,2}));
        Assert.assertEquals(2, freq.maxEqualFreq(new int[] {1,1}));
    }
}
