package com.saraad.leetcode.dailycode2022.july;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/snakes-and-ladders
 * @Date: 02-07-2022 14:22
 */

public class SnakesAndLadders {

    public int snakesAndLadders(int[][] board) {
        int n = board.length, target = n * n;
        //扁平化数组
        int[] arr = new int[target + 1];
        // boolean flag = true;
        // for (int row = n - 1, idx = 1; row >= 0; row--) {
        //     for (int col = flag ? 0 : n - 1; flag ? col < n : col >= 0; col += flag ? 1 : -1) {
        //         arr[idx++] = board[row][col];
        //     }
        //     flag = !flag;
        // }
        int row = n - 1, col = 0, idx = 0;
        while (idx < target) {
            arr[idx + 1] = board[row][col];
            if ((col == n - 1 && (idx / n & 1) == 0) || (col == 0 && (idx / n & 1) == 1))  {
                row--;
            } else if ((idx / n & 1) == 0) {
                col++;
            } else if ((idx / n & 1) == 1) {
                col--;
            }
            idx++;
        }
        return bfs(arr);
    }

    int bfs(int[] arr) {
        Deque<Integer> d = new ArrayDeque<>();
        Map<Integer, Integer> m = new HashMap<>();
        d.addLast(1);
        m.put(1, 0);
        while (!d.isEmpty()) {
            int poll = d.pollFirst();
            int step = m.get(poll);
            if (poll == arr.length - 1) return step;
            for (int i = 1; i <= 6; i++) {
                int np = poll + i;
                if (np <= 0 || np > arr.length - 1) continue;
                if (arr[np] != -1) np = arr[np];
                if (m.containsKey(np)) continue;
                m.put(np, step + 1);
                d.addLast(np);
            }
        }
        return -1;
    }

    int bfs1(int[] arr) {
        boolean[] visited = new boolean[arr.length];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        int ans = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int cur = q.poll();
                for (int j = 1; j <= 6; j++) {
                    int next = cur + j;
                    if (arr[next] != -1) {
                        next = arr[next];
                    }
                    if (next >= arr.length) {
                        return ans + 1;
                    }
                    if (!visited[next]) {
                        q.offer(next);
                    }
                }
                visited[cur] = true;
            }
            ans++;
        }
        return -1;
    }

    public static void main(String[] args) {
        Deque<Integer> d = new ArrayDeque<>();
        Queue<Integer> q = new ArrayDeque<>();
        d.addLast(1);
        d.offer(1) ;
    }
}
