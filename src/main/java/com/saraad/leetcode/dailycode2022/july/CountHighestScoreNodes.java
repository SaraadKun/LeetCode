package com.saraad.leetcode.dailycode2022.july;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 22-07-2022 23:27
 */

public class CountHighestScoreNodes {
    public int countHighestScoreNodes(int[] parents) {
        //[-1,2,0,2,0]
        //遍历数组,统计各个节点的size用数组存储, sz = lsz + rsz + 1;
        //同时记录父节点->子节点的映射关系,null用-1表示
        //遍历每个节点,其大小为(psz - sz) * lsz * rsz;
        //若为根节点,则psz = sz + 1, 左右子树为null时,lsz rsz默认为1
        //取上述结果最大值计数返回
        int n = parents.length;
        int[] sz = new int[n];
        Arrays.fill(sz, 1);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (parents[i] != -1) {
                List<Integer> children = map.getOrDefault(parents[i], new ArrayList<>());
                children.add(i);
                map.put(parents[i], children);
            }
        }
        //使用bfs加栈存储节点信息,bfs结束后,pop栈计算各节点size
        Deque<Integer> stack = new ArrayDeque<>();
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        while (!q.isEmpty()) {
            int cur = q.poll();
            stack.push(cur);
            for (int ne : map.getOrDefault(cur, List.of())) {
                q.offer(ne);
            }
        }
        //弹栈从底向上计算size
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            for (int child : map.getOrDefault(cur, List.of())) {
                sz[cur] += sz[child];
            }
        }
        //遍历每个节点,寻找最大值并计数
        int ans = 0;
        long max = 0;
        for (int i = 0; i < n; i++) {
            int psz = sz[0], lsz = 1, rsz = 1;
            long cur;
            if (i == 0) {
                psz += 1;
            }
            List<Integer> children = map.getOrDefault(i, List.of());
            if (children.size() == 1) {
                lsz = sz[children.get(0)];
            } else if (children.size() == 2) {
                lsz = sz[children.get(0)];
                rsz = sz[children.get(1)];
            }
            cur = (long)(psz - sz[i]) * lsz * rsz;
            if (cur == max) {
                ans++;
            } else if (cur > max) {
                max = cur;
                ans = 1;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        CountHighestScoreNodes c = new CountHighestScoreNodes();
        int[] parents = {-1, 2, 0, 2, 0};
        System.out.println(c.countHighestScoreNodes(parents));
    }
}
