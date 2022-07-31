package com.saraad.leetcode.dailycode2022.july;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 31-07-2022 10:35
 */

public class MinimumOperations {
    public int minimumOperations(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num != 0) {
                set.add(num);
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        int[] nums = {1,5,0,3,5};
        System.out.println(new MinimumOperations().minimumOperations(nums));
    }

}
