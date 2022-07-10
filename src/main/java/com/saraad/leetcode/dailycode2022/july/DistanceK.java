package com.saraad.leetcode.dailycode2022.july;

import com.saraad.leetcode.bean.TreeNode;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/all-nodes-distance-k-in-binary-tree/
 * @Date: 10-07-2022 22:44
 */

public class DistanceK {

    //邻接表,存储所有边的信息
    private Map<Integer, List<Integer>> adj = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        //遍历建图,然后BFS
        dfs(root, -1);
        List<Integer> ans = new ArrayList<>();
        boolean[] visited = new boolean[501];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(target.val);
        visited[target.val] = true;
        while (!q.isEmpty() && k > 0) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int cur = q.poll();
                for (int next : adj.getOrDefault(cur, List.of())) {
                    if (!visited[next]) {
                        q.offer(next);
                        visited[next] = true;
                    }
                }
            }
            k--;
        }
        //首轮遍历只有target节点,距离为0,经过k轮遍历后,队列里的节点都是距离为k的节点了
        while(!q.isEmpty()) {
            ans.add(q.poll());
        }
        return ans;

    }

    //遍历时将当前节点与父节点的边信息存入邻接表
    void dfs(TreeNode root, int parent) {
        if (root == null) {
            return;
        }
        if (parent != -1) {
            List<Integer> cur = adj.getOrDefault(root.val, new ArrayList<>());
            cur.add(parent);
            adj.put(root.val, cur);
            List<Integer> p = adj.getOrDefault(parent, new ArrayList<>());
            p.add(root.val);
            adj.put(parent, p);
        }
        dfs(root.left, root.val);
        dfs(root.right, root.val);
    }
}
