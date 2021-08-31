package com.saraad.leetcode.dailycode.april;

public class ShipWithinDays {
    public int shipWithinDays(int[] weights, int D) {
        int max = 0, sum = 0;
        for (int i = 0; i < weights.length; i++) {
            max = Math.max(max, weights[i]);
            sum += weights[i];
        }
        int lo = max, hi = sum;
        while (lo > hi) {
            int mid = lo + (hi - lo) / 2;
            int cnt = 0, cur = 0;
            for (int i = 0; i < weights.length; i++) {
                if (cur + weights[i] > mid) {
                    cnt++;
                    cur = 0;
                }
                cur += weights[i];
            }
            if (cnt <= D) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }


}
