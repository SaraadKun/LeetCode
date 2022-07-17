package com.saraad.leetcode.dailycode2022.july;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/qIsx9U/
 * @Date: 16-07-2022 21:13
 */

class MovingAverage {

    private int[] nums = new int[1000];
    private int size, cnt = 0, curSum = 0;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
    }
    
    public double next(int val) {
        cnt++;
        nums[(cnt - 1) % 1000] = val;
        int pre = nums[(cnt + 1000 - size - 1) % 1000];
        curSum = curSum - pre + val;
        if (cnt < size) {
            return (double) curSum / cnt;
        }
        return (double) curSum / size;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */