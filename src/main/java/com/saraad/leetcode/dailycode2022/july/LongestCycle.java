package com.saraad.leetcode.dailycode2022.july;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 31-07-2022 12:06
 */

public class LongestCycle {
    public int longestCycle(int[] edges) {
        //1.统计所有入度为0的点,从每个入度为0的点开始搜索,发现环并计算长度 or 遍历步长为n结束循环
        //2.若1.无环,反转图,再过一遍1. //TODO
        int n = edges.length, ans = -1;

        return ans;
    }
}
