package com.saraad.leetcode.dailycode2022.august;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 07-08-2022 03:35
 */

public class AlienOrder {
    public String alienOrder(String[] words) {
        //建图,统计入度,拓扑排序,判断是否有环(是否所有字母都遍历过)
        int n = words.length;
        boolean[][] adj = new boolean[26][26];
        int[] ind = new int[26];
        Arrays.fill(ind, -1);
        for (int i = 0; i < n - 1; i++) {
            char[] chs1 = words[i].toCharArray(), chs2 = words[i + 1].toCharArray();
            int j = 0, m = Math.min(chs1.length, chs2.length);
            for (; j < m; j++) {
                if (chs1[j] != chs2[j]) {
                    int idx1 = chs1[j] - 'a', idx2 = chs2[j] - 'a';
                    ind[idx1] = Math.max(ind[idx1], 0);
                    ind[idx2] = Math.max(ind[idx2], 0);
                    if (!adj[idx1][idx2]) {
                        adj[idx1][idx2] = true;
                        ind[idx2]++;
                    }
                    break;
                }
            }
            for (; j < chs1.length; j++) {
                int idx = chs1[j] - 'a';
                ind[idx] = Math.max(ind[idx], 0);
            }
        }
        for (char ch : words[n - 1].toCharArray()) {
            int idx = ch - 'a';
            ind[idx] = Math.max(ind[idx], 0);
        }
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < 26; i++) {
            if (ind[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int cur = q.poll();
            sb.append((char)('a' + cur));
            for (int i = 0; i < 26; i++) {
                if (adj[cur][i]) {
                    ind[i]--;
                    if (ind[i] == 0) {
                        q.offer(i);
                    }
                }
            }
        }
        for (int d : ind) {
            if (d > 0) {
                return "";
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        AlienOrder alienOrder = new AlienOrder();
//        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        String[] words = {"abc", "ab"};
        System.out.println(alienOrder.alienOrder(words));
    }
}
