package com.saraad.leetcode.dailycode2022.july;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 04-07-2022 16:34
 */

public class NumWays {

    public int numWays(int n, int[][] relation, int k) {
        return numWaysDP(n, relation, k);
//        return numWaysBFS(n, relation, k);
    }

    //DP
    public int numWaysDP(int n, int[][] relation, int k) {
        //dp[i][j] 代表经过第i轮遍历后,编号为j的人所能收到消息的路径数
        int[][] dp = new int[k + 1][n];
        dp[0][0] = 1; //初始消息从0,0处发出
        for (int i = 0; i < k; i++) {
            for (int[] arr : relation) {
                dp[i + 1][arr[1]] += dp[i][arr[0]];
            }
        }
        return dp[k][n - 1];
    }

    //BFS
    public int numWaysBFS(int n, int[][] relation, int k) {
        //经过确定的k轮消息可到达的路径数,不包含历史到达路径
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] arr: relation) {
            Set<Integer> set = map.getOrDefault(arr[0], new HashSet<>());
            set.add(arr[1]);
            map.put(arr[0], set);
        }
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        int ans = 0, step = 0;
        while (step < k && !q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int key = q.poll();
                map.getOrDefault(key, Set.of()).forEach(q::offer);
            }
            step++;
        }
        while(!q.isEmpty()) {
            if (q.poll() == n - 1) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        NumWays numWays = new NumWays();
        int n = 3, k = 2;
        int[][] relation = {{0, 1}, {1, 2}};
        System.out.println(numWays.numWays(n, relation, k));
    }
}
