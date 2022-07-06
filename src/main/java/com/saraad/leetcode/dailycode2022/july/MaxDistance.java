package com.saraad.leetcode.dailycode2022.july;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/as-far-from-land-as-possible/
 * @Date: 06-07-2022 01:07
 */

public class MaxDistance {

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maxDistance(int[][] grid) {
            //多源BFS
            //找到所有陆地并入队,维护一个最大距离变量md,初始距离d=0,BFS,每轮的位置距离+1并更新md
            int n = grid.length;
            Queue<int[]> q = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        q.offer(new int[] {i, j, 0});
                    }
                }
            }
            int md = -1;
            while (!q.isEmpty()) {
                int[] p = q.poll();
                int x = p[0], y = p[1], d = p[2];
                for (int[] dir: dirs) {
                    int nx = x + dir[0], ny = y + dir[1];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 0) {
                        q.offer(new int[]{nx, ny, d + 1});
                        grid[nx][ny] = -1;
                        md = Math.max(md, d + 1);
                    }
                }
            }
            return md;
        }

    public static void main(String[] args) {
        MaxDistance m = new MaxDistance();
        int[][] grid = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
        int ans = m.maxDistance(grid);
        System.out.println(ans);
    }

}
