package com.saraad.leetcode.dailycode2022.august;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 22-08-2022 12:15
 */

public class FindMaxLength {
    public int findMaxLength(int[] nums) {
        int n = nums.length, ans = 0, sum = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sum += nums[i] == 0 ? -1 : 1;
            if (map.containsKey(-sum)) {
                ans = Math.max(ans, i - map.get(-sum) + 1);
            }
            map.putIfAbsent(sum, i);
        }
        return ans;
    }

    public static void main(String[] args) {
        FindMaxLength freq = new FindMaxLength();
        Assert.assertEquals(2, freq.findMaxLength(new int[] {0,1}));
    }
}
