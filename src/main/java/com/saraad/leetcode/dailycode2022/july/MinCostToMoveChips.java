package com.saraad.leetcode.dailycode2022.july;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/minimum-cost-to-move-chips-to-the-same-position/
 * @Date: 09-07-2022 13:26
 */

public class MinCostToMoveChips {
    public int minCostToMoveChips(int[] position) {
        int[] ans = new int[2];
        for (int p : position)
            ans[p & 1]++;
        return Math.min(ans[0], ans[1]);
    }
}
