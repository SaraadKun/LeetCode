package com.saraad.leetcode.dailycode;

/**
 * @Title: MinDays
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * @author: saraad
 * @date: 2021/5/9 2:29 下午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class MinDays {

    public static void main(String[] args) {
        double pow = Math.pow(2.0, 31.0);
        System.out.println(String.valueOf(pow));
        System.out.println(Integer.MAX_VALUE);
    }

    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int xor = 0;
        for (int i = 0; i <= n; i++) {
            xor ^= i;
        }
        int x = 0;
        for (int i = 1; i < n - 1; i += 2) {
            x ^= encoded[i];
        }
        int first = xor ^ x;
        int[] ans = new int[n];
        ans[0] = first;
        for (int i = 1; i < ans.length; i++) {
            ans[i] = ans[i - 1] ^ encoded[i - 1];
        }
        return ans;
    }

    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if (n < m * k) {
            return -1;
        }
        int hi = 0, lo = Integer.MAX_VALUE;
        for (int d : bloomDay) {
            hi = Math.max(hi, d);
            lo = Math.min(lo, d);
        }
        //二分查找
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (check(bloomDay, m, k, mid)){
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private boolean check(int[] bloomDay, int m, int k, int mid) {
        int cnt = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] > mid) {
                cnt = 0;
            }else {
                cnt++;
                if (cnt == k) {
                    m--;
                    if (m == 0){
                        return true;
                    }
                    cnt = 0;
                }
            }
        }
        return false;
    }
}
