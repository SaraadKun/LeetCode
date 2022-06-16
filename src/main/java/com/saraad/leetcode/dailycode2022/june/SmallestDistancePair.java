package com.saraad.leetcode.dailycode2022.june;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmallestDistancePair {

    public int smallestDistancePair(int[] nums, int k) {
        //寻找第一个大于等于k的值  <k, hi
        Arrays.sort(nums);
        int lo = -1, hi = nums[nums.length - 1] - nums[0] + 1;
        while (lo + 1 != hi) {
            int mid = (hi - lo) / 2 + lo;
            if (check(nums, mid, k)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi;
    }

    boolean check(int[] nums, int diff, int target) {
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            int lo = i - 1, hi = nums.length;
            //最后一个小于等于k的值 <=k, lo
            while (lo + 1 != hi) {
                int mid = (hi - lo) / 2 + lo;
                if (nums[mid] - nums[i] <= diff) {
                    lo = mid;
                } else {
                    hi = mid;
                }
            }
            cnt += lo - i;
        }
        return cnt < target;
    }

    public static void main(String[] args) {
        SmallestDistancePair obj = new SmallestDistancePair();
        int[] nums = {1, 6, 1};
        int k = 3;
        String[] arr = {};
        Arrays.sort(arr, (o1, o2) -> o1.length() - o2.length());
        int ans = obj.smallestDistancePair(nums, k);
        System.out.println(ans);
        List<Integer> list = new ArrayList<>();
        for (int integer : list) {

        }
    }
}
