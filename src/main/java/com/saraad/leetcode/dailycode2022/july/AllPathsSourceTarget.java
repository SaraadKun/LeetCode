package com.saraad.leetcode.dailycode2022.july;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 09-07-2022 23:38
 */

public class AllPathsSourceTarget {

    List<List<Integer>> ans = new ArrayList<>();
    //使用双端队列来当做栈,方便添加进结果集
    ArrayDeque<Integer> deque = new ArrayDeque<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        //DFS
        deque.offer(0);
        dfs(graph, 0, graph.length);
        return ans;
    }

    private void dfs(int[][] graph, int idx, int n) {
        if (idx == n - 1) {
            ans.add(new ArrayList<>(deque));
            return;
        }
        for (int p : graph[idx]) {
            deque.addLast(p);
            dfs(graph, p, n);
            deque.pollLast();
        }
    }

    public static void main(String[] args) {
        AllPathsSourceTarget allPathsSourceTarget = new AllPathsSourceTarget();
        int[][] graph = {{1, 2}, {3}, {3}, {}};
        allPathsSourceTarget.allPathsSourceTarget(graph);
        System.out.println(allPathsSourceTarget.ans);
    }
}
