package com.saraad.leetcode.dailycode2022.august;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: <a href="https://leetcode.cn/problems/maximum-score-after-splitting-a-string/">...</a>
 * @Date: 14-08-2022 02:41
 */

public class MaxScore {
    public int maxScore(String s) {
        // s长度为n,s中'1'的数量为x,设i为切割位,[0,i]的1的个数为g(i),其中i∈[0,n-1)
        // f(i) = (i + 1 - g(i)) + x - g(i) = x + 1 + i - 2g(i)
        // 求max(f(i)),等价于求max(i - 2g(i));
        int n = s.length(), x = 0, gi = 0, max = -500;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                x++;
                gi++;
            }
            if (i != n - 1) {
                max = Math.max(max, i - 2 * gi + 1);
            }
        }
        return x == 0 ? n - 1 : x + max;
    }
}
