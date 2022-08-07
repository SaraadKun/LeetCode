package com.saraad.leetcode.dailycode2022.august;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 07-08-2022 10:52
 */

public class ReachableNodes {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        Set<Integer> set = new HashSet<>();
        for (int num : restricted) {
            set.add(num);
        }
        int ans = 0;
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        visited[0] = true;
        while (!q.isEmpty()) {
            int cur = q.poll();
            ans++;
            for (int next : adj.get(cur)) {
                if (!visited[next] && !set.contains(next)) {
                    q.offer(next);
                    visited[next] = true;
                }
            }
        }
        return ans;
    }

}
