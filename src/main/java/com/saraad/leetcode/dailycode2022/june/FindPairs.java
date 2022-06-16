package com.saraad.leetcode.dailycode2022.june;

import java.util.Arrays;

public class FindPairs {

    public static void main(String[] args) {
        FindPairs obj = new FindPairs();
        int[] nums = {1, 2, 1, 4, 5};
        System.out.println(obj.findPairs(nums, 0));
    }

    public int findPairs(int[] nums, int k) {
        int ans = 0;
        Arrays.sort(nums);
        int lo = 0, hi = 1;
        while (lo < nums.length - 1) {
            if (hi <= lo) {
                hi = lo + 1;
            }
            while (hi < nums.length && nums[hi] - nums[lo] < k){
                hi++;
            }
            if (hi < nums.length && nums[hi] - nums[lo] == k) {
                ans++;
                while (lo < nums.length - 1 && nums[lo + 1] == nums[lo]) {
                    lo++;
                }
            }
            lo++;
        }
        return ans;
    }
}
