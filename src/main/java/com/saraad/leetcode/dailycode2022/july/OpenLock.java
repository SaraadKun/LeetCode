package com.saraad.leetcode.dailycode2022.july;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 11-07-2022 18:16
 */

public class OpenLock {

    int[] dirs = {-1, 1};

    public int openLock(String[] deadends, String target) {
        Set<Integer> set = new HashSet<>();
        for (String deadend : deadends) {
            set.add(Integer.parseInt(deadend));
        }
        //面向测试用例的编程
        if (set.contains(0)) {
            return -1;
        }
        if ("0000".equals(target)) {
            return 0;
        }
        int t = Integer.parseInt(target);
        //bfs,4个位置,每次只增加(or减小)1步,随机落在每个位置上
        //q中存放长度为5的int数组, arr[4]为步数,[arr[0], arr[3]]分别表示左起第1-4位的数字
        boolean[] visited = new boolean[10000];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 0, 0, 0});
        visited[0] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int step = cur[4];
            for (int dir : dirs) {
                for (int i = 0; i < 4; i++) {
                    int n = 0;
                    for (int j = 0; j < 4; j++) {
                        if (j == i) {
                            n = n * 10 + (cur[j] + dir + 10) % 10;
                        } else {
                            n = n * 10 + cur[j];
                        }
                    }
                    if (t == n) {
                        return step + 1;
                    }
                    if (!visited[n] && !set.contains(n)) {
                        q.offer(new int[] {n / 1000, n / 100 % 10, n / 10 % 10, n % 10, step + 1});
                        visited[n] = true;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
