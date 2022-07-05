package com.saraad.leetcode.dailycode2022.july;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/map-of-highest-peak/
 * @Date: 05-07-2022 18:42
 */

public class HighestPeak {

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int[][] highestPeak(int[][] isWater) {
        //找到所有水域点,加入队列,同时标记所有水域点高度为0,陆地点为-1(代表未访问)
        //BFS,每一轮循环标记的高度+1,直至循环结束
        int m = isWater.length, n = isWater[0].length;
        int[][] ans = new int[m][n];
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    q.offer(new int[]{i, j});
                    ans[i][j] = 0;
                } else {
                    ans[i][j] = -1;
                }
            }
        }
        //BFS
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && ans[nx][ny] == -1) {
                    ans[nx][ny] = ans[x][y] + 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        HighestPeak h = new HighestPeak();
        int[][] isWater = {{0, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 1, 1, 1, 0}, {0, 1, 1, 1, 0}, {0, 0, 0, 0, 0}};
        int[][] ans = h.highestPeak(isWater);
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }

}
