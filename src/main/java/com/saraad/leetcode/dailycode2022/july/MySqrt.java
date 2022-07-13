package com.saraad.leetcode.dailycode2022.july;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 13-07-2022 19:50
 */

public class MySqrt {

    public int mySqrt(int x) {
        //二分法,找到最后一个小于等于的元素
        int max = 1 << 16, lo = -1, hi = x >= max ? max : x + 1;
        while (lo + 1 != hi) {
            int mid = (hi - lo) / 2 + lo;
            if ((long)mid * mid <= x) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

}
