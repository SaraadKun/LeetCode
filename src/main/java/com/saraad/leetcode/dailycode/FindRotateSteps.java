package com.saraad.leetcode.dailycode;

import java.util.*;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: FindRotateSteps
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * @author: saraad
 * @date: 2020/11/11 23:06
 * @Copyright: 2020  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class FindRotateSteps {

    public int findRotateSteps(String ring, String key) {
        //map存储ring中每个元素出现的位置集合,key为元素,val为数组,存放index位置
        //dp[i][j],i为key的第i个元素,j为ring的第j个元素等于key[i],dp[i][j]为ring[j]到12点方向(dp[i-1][k])的距离
        //状态转移方程: dp[i][j]=min(dp[i-1][k]+min(abs(j-k),n-abs(j-k))+1) k∈map[i-1]
        int n = ring.length(), m = key.length();
        Map<Character, List<Integer>> map = new HashMap<>();
        char[] chars = ring.toCharArray();
        for ( int i = 0; i < n; i++) {
            if(!map.containsKey(chars[i])){
                map.put(chars[i], new ArrayList<>());
            }
            map.get(chars[i]).add(i);
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], 10001);
        }
        //dp[0][j]
        for (Integer j : map.get(key.charAt(0))) {
            dp[0][j] = Math.min(j, n - j) + 1;
        }
        for (int i = 1; i < m; i++) {
            for (Integer j : map.get(key.charAt(i))) {
                for (Integer k : map.get(key.charAt(i - 1))) {
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][k] + Math.min(Math.abs(j-k), n - Math.abs(j-k) +1));
                }
            }
        }
        return Arrays.stream(dp[m-1]).min().getAsInt();

    }

}
