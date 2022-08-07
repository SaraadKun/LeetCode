package com.saraad.leetcode.dailycode2022.august;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 07-08-2022 10:45
 */

public class ArithmeticTriplets {
    public int arithmeticTriplets(int[] nums, int diff) {
        int ans = 0, n = nums.length, i = 0, j = 1;
        while (i < n - 2 && j < n - 1) {
            if (nums[j] - nums[i] < diff) {
                j++;
            } else if (nums[j] - nums[i] > diff) {
                i++;
            } else {
                int k = j + 1;
                while (k < n) {
                    if (nums[k] - nums[j] < diff) {
                        k++;
                        continue;
                    } else if (nums[k] - nums[j] == diff) {
                        ans++;
                    }
                    break;
                }
                i++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        ArithmeticTriplets arithmeticTriplets = new ArithmeticTriplets();
        int[] nums = {0,1,4,6,7,10};
        int diff = 3;
        int ans = arithmeticTriplets.arithmeticTriplets(nums, diff);
        System.out.println(ans);
    }
}
