package com.saraad.leetcode.dailycode;

/**
 * @Title: BinarySearch
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * @author: saraad
 * @date: 2021/9/6 9:31 上午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch instance = new BinarySearch();
//        int[] nums = {-1, 0, 3, 5, 9, 12};
//        int[] nums = {-1,0,2,5,9,12};
//        int[] nums = {2};
//        int target = 2;
//        int res = instance.search(nums, target);
        int res = instance.fib(93);

        System.out.println(res);
    }

    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        long d1 = 0, d2 = 1;
        int mod = 1000000007;
        for (int i = 2; i <= n; i++){
            long dn = (d1 + d2) % mod;
            d1 = d2;
            d2 = dn;
        }
        return (int) d2;
    }

    public int search(int[] nums, int target) {
        if (nums[0] > target || nums[nums.length - 1] < target) {
            return -1;
        }
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (hi - lo) / 2 + lo;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return  -1;
//        return binarySearch(nums, target, 0, nums.length - 1);
    }

    public int binarySearch(int[] nums, int target, int lo, int hi) {
        if (lo > hi) {
            return -1;
        }
        int mid = (hi - lo) / 2 + lo;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] < target) {
            return binarySearch(nums, target, mid + 1, hi);
        }
        return binarySearch(nums, target, lo, mid - 1);
    }

}
