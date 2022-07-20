package com.saraad.leetcode.dailycode2022.july;

import com.saraad.util.JSONUtil;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 20-07-2022 10:49
 */

public class EventualSafeNodes {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        //反向建图+拓扑排序
        int n = graph.length;
        List<List<Integer>> adj = new ArrayList<>(); //反向图邻接表
        int[] inDegrees = new int[n]; //反向图入度等于原图的出度
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            inDegrees[i] = graph[i].length;
        }
        for (int i = 0; i < n; i++) {
            for (int p : graph[i]) {
                adj.get(p).add(i);
            }
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (inDegrees[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int ne : adj.get(cur)) {
                if (--inDegrees[ne] == 0) {
                    q.offer(ne);
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (inDegrees[i] == 0) {
                ans.add(i);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        EventualSafeNodes obj = new EventualSafeNodes();
//        int[][] graph = JSONUtil.readValue("[[1,2],[2,3],[5],[0],[5],[],[]]", int[][].class);
        int[][] graph = JSONUtil.readValue("[[],[0,2,3,4],[3],[4],[]]", int[][].class);
        System.out.println(JSONUtil.writeValueAsString(obj.eventualSafeNodes(graph)));
    }

}
