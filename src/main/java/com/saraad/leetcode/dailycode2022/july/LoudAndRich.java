package com.saraad.leetcode.dailycode2022.july;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/loud-and-rich/
 * @Date: 21-07-2022 22:34
 */

public class LoudAndRich {

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        //用邻接表对richer建图,rich -> pool,同时统计入度
        //在进行拓扑排序的过程中,尝试根据更新相关节点(比当前节点穷的节点)的ans,拓扑排序完毕返回ans即可
        int n = quiet.length;
        int[] ans = new int[n], cnts = new int[n];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] p: richer) {
            cnts[p[1]]++;
            adj.get(p[0]).add(p[1]);
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (cnts[i] == 0) {
                q.offer(i);
            }
            ans[i] = i;
        }
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int ne : adj.get(cur)) {
                if (--cnts[ne] == 0) {
                    q.offer(ne);
                }
                if (quiet[ans[ne]] >= quiet[ans[cur]]) {
                    ans[ne] = ans[cur];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LoudAndRich loudAndRich = new LoudAndRich();
        //[[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]]
        //[3,2,5,4,6,1,7,0]
        //[5,5,2,5,4,5,6,7]
        int[][] richer = {{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}};
        int[] quiet = {3, 2, 5, 4, 6, 1, 7, 0};
        int[] ans = loudAndRich.loudAndRich(richer, quiet);
        System.out.println(Arrays.toString(ans));
    }
}
