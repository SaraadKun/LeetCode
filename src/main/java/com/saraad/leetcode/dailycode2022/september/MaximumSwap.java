package com.saraad.leetcode.dailycode2022.september;

import org.junit.Assert;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 13-09-2022 00:31
 */

public class MaximumSwap {
    public int maximumSwap(int num) {
        int[] arr = new int[9];
        for (int i = 0; i < 9 && num > 0; i++) {
            arr[8 - i] = num % 10;
            num /= 10;
        }
        boolean flag = true;
        for (int i = 0; i < 9; i++) {
            if (arr[i] == 0 && flag) {
                continue;
            }
            flag = false;
            int max = arr[i], idx = i;
            for (int j = i + 1; j < 9; j++) {
                if (arr[j] >= max) {
                    max = arr[j];
                    idx = j;
                }
            }
            if (max > arr[i]) {
                arr[idx] = arr[i];
                arr[i] = max;
                break;
            }
        }
        int ans = 0;
        for (int i = 0; i < 9; i++) {
            ans = ans * 10 + arr[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        MaximumSwap obj = new MaximumSwap();
        Assert.assertEquals(99910, obj.maximumSwap(99901));
    }
}
