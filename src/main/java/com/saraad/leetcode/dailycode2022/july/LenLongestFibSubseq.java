package com.saraad.leetcode.dailycode2022.july;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence/
 * @Date: 09-07-2022 23:32
 */

public class LenLongestFibSubseq {

    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length, ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        //dp[j][i] j < i, 代表以j, i结尾的数列最大长度
        int[][] dp = new int[n][n];
        for (int i = 2; i < n; i++) {
            //arr严格递增,要保证存在target < j,则arr[i] - arr[j] < arr[j]
            for (int j = i - 1; j > 0 && arr[j] * 2 > arr[i]; j--) {
                int target = map.getOrDefault(arr[i] - arr[j], -1);
                if (target >=0) {
                    dp[j][i] = Math.max(dp[target][j] + 1, 3);
                }
                ans = Math.max(ans, dp[j][i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LenLongestFibSubseq lenLongestFibSubseq = new LenLongestFibSubseq();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(lenLongestFibSubseq.lenLongestFibSubseq(arr));
    }

}
