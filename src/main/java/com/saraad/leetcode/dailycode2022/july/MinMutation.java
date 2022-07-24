package com.saraad.leetcode.dailycode2022.july;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 24-07-2022 16:21
 */

public class MinMutation {

    public int minMutation(String start, String end, String[] bank) {
        //预处理数据,添加中间节点,建图+BFS,记录已访问过的节点,避免重复访问
        Map<String, List<String>> adj = new HashMap<>();
        //预处理bank建图
        for (String gene : bank) {
            List<String> list = adj.getOrDefault(gene, new ArrayList<>());
            char[] chs = gene.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                char tmp = chs[i];
                chs[i] = '*';
                String ne = new String(chs);
                list.add(ne);
                List<String> nes = adj.getOrDefault(ne, new ArrayList<>());
                nes.add(gene);
                adj.put(ne, nes);
                chs[i] = tmp;
            }
            adj.put(gene, list);
        }
        //end不在图中,提前返回
        if (!adj.containsKey(end)) {
            return -1;
        }
        //使用map记录到达该节点的步数
        Map<String, Integer> map = new HashMap<>();
        Set<String> visited = new HashSet();
        Queue<String> q = new ArrayDeque<>();
        //将start相关中间节点入队
        char[] schs = start.toCharArray();
        for (int i = 0; i < schs.length; i++) {
            char tmp = schs[i];
            schs[i] = '*';
            String ne = new String(schs);
            if (adj.containsKey(ne)) {
                q.offer(ne);
                visited.add(ne);
                map.put(ne, 1);
            }
            schs[i] = tmp;
        }
        //BFS
        while (!q.isEmpty()) {
            String cur = q.poll();
            int step = map.get(cur);
            for (String ne: adj.get(cur)) {
                if (end.equals(ne)) {
                    return (step + 1) / 2;
                }
                if (!visited.contains(ne)) {
                    q.offer(ne);
                    visited.add(ne);
                    map.put(ne, step + 1);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        MinMutation minMutation = new MinMutation();
        String start = "AAAACCCC", end = "CCCCCCCC";
        String[] bank = {"AAAACCCA","AAACCCCA","AACCCCCA","AACCCCCC","ACCCCCCC","CCCCCCCC","AAACCCCC","AACCCCCC"};
        System.out.println(minMutation.minMutation(start, end, bank));
    }

}
