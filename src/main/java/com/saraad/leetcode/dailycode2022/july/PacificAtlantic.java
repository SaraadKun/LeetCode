package com.saraad.leetcode.dailycode2022.july;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 15-07-2022 21:13
 */

public class PacificAtlantic {

    int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};//← ↑ → ↓

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        //1.AO相邻的陆地格子入队,BFS找到所有高度大于等于的格子并set记录.
        //2.PO相邻的陆地格子入队,BFS找所有高度大于等于的格子并与1.中结果比对,若相同则加入结果集
        List<List<Integer>> ans = new ArrayList<>();
        //idx = m << 8 | n
        int m = heights.length, n = heights[0].length;
        Set<Integer> set = new HashSet<>();
        Queue<int[]> aq = new ArrayDeque<>();
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            set.add(i << 8 | n - 1);
            aq.offer(new int[]{i, n - 1});
            visited[i][n - 1] = true;
        }
        for (int i = 0; i < n - 1; i++) {
            set.add((m - 1) << 8 | i);
            aq.offer(new int[]{m - 1, i});
            visited[m - 1][i] = true;
        }
        while (!aq.isEmpty()) {
            int[] cur = aq.poll();
            int x = cur[0], y = cur[1], h = heights[x][y];
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n
                        && !visited[nx][ny] && heights[nx][ny] >= h) {
                    aq.offer(new int[]{nx, ny});
                    set.add(nx << 8 | ny);
                    visited[nx][ny] = true;
                }
            }
        }
        //po相邻陆地格子入队,第二轮bfs
        visited = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            aq.offer(new int[]{i, 0});
            visited[i][0] = true;
        }
        for (int i = 1; i < n; i++) {
            aq.offer(new int[]{0, i});
            visited[0][i] = true;
        }
        while (!aq.isEmpty()) {
            int[] cur = aq.poll();
            int x = cur[0], y = cur[1], h = heights[x][y];
            if (set.contains(x << 8 | y)) {
                ans.add(List.of(x, y));
            }
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n
                        && !visited[nx][ny] && heights[nx][ny] >= h) {
                    aq.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[][] heights = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        int[][] heights = {{1,2,3},{8,9,4},{7,6,5}};
        PacificAtlantic p = new PacificAtlantic();
        List<List<Integer>> ans = p.pacificAtlantic(heights);
//        System.out.println(ans);//[[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
        System.out.println(ans);
        // 1, 2, 3
        // 8, 9, 4
        // 7, 6, 5
    }
}
