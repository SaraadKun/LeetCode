package com.saraad.leetcode.dailycode2022.july;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/bus-routes/
 * @Date: 18-07-2022 22:29
 */

public class NumBusesToDestination {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        //预处理routes,key为公交站编号,value为公交车编号集合
        //BFS source入队,并将source对应的所有公交车所经过的公交站入队,
        //记录公交车使用情况,避免重复搜索.记录bfs轮次即为所需乘坐的公交车数量
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = routes.length;
        for (int i = 0; i < n; i++) {
            for (int j : routes[i]) {
                List<Integer> list = map.getOrDefault(j, new ArrayList<>());
                list.add(i);
                map.put(j, list);
            }
        }
        //记录访问过的公交车编号
        boolean[] visited = new boolean[n];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{source, 0});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            //数组cur[0]为站点编号,cur[1]为搭乘过的公交车数量
            int station = cur[0], step = cur[1];
            if (station == target) {
                return step;
            }
            //找到所有途径当前站点公交车,将这些公交车所经过的站点全部入队,并标记公交车已处理过
            for (int bus : map.get(station)) {
                if (!visited[bus]) {
                    for (int ns : routes[bus]) {
                        q.offer(new int[]{ns, step + 1});
                    }
                }
                visited[bus] = true;
            }
        }
        return -1;
    }
}
