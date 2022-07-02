package com.saraad.leetcode.dailycode2022.july;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 01-07-2022 15:45
 */

public class ColorBorder {

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        //bfs
        int[][] res = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                res[i][j] = grid[i][j];
            }
        }
        int target = grid[row][col];
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {row, col});
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int[] cur = q.poll();
                visited[cur[0]][cur[1]] = true;
                boolean isBorder = false;
                for (int[] dir : dirs) {
                    int r = cur[0] + dir[0], c = cur[1] + dir[1];
                    if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length &&
                            grid[r][c] == target) {
                        if (!visited[r][c])
                            q.offer(new int[]{r, c});
                    } else {
                        isBorder = true;
                    }
                }
                //染色
                if (isBorder)
                    res[cur[0]][cur[1]] = color;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ColorBorder cb = new ColorBorder();
//        int[][] grid = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
//        int[][] res = cb.colorBorder(grid, 1, 1, 2);
        int grid[][] = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int row = 0;
        int col = 0;
        int color = 3;
        int[][] res = cb.colorBorder(grid, row, col, color);
        for (int[] r : res) {
            for (int c : r)
                System.out.print(c + " ");
            System.out.println();
        }
    }

}
