package com.saraad.leetcode.dailycode2022.june;

/**
 * @Description: 1423. 可获得的最大点数
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/maximum-points-you-can-obtain-from-cards/
 * @Date: 26-06-2022 22:41
 */

public class MaxScore {
    public int maxScore(int[] cardPoints, int k) {
        //转化问题为求连续的最小数组窗口
        int  n = cardPoints.length, lo = 0, hi = n - k - 1, sum = 0, cur = 0, min = 0;
        for (int i = lo; i < n - k; i++) {
            sum += cardPoints[i];
        }
        cur = sum;
        min = sum;
        while (hi < n - 1) {
            cur = cur - cardPoints[lo++] + cardPoints[++hi];
            sum += cardPoints[hi];
            min = Math.min(min, cur);
        }
        return sum - min;
    }
}
