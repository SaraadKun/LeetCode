package com.saraad.leetcode.dailycode2022.july;

import com.saraad.util.JSONUtil;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 10-07-2022 00:20
 */

public class CherryPickup {

    //TODO 解法是错的,回头再改
    //N [1,50]
    public int cherryPickup(int[][] grid) {
        //dp 第一轮遍历需要记录经过的路径,第一轮结束后重置dp数组,按照记录的路径清除已摘取的樱桃,
        //保留dp[N-1][N-1]的数值作为下轮dp起始数值
        int n = grid.length;
        int[][] dp = new int[n][n];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        Map<Integer, List<Integer>> paths = new HashMap<>();
        paths.put(0, List.of(0));
        dp[0][0] = grid[0][0];
        //第一轮dp
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == -1) {
                    continue;
                }
                int up = -1, left = -1;
                if (i - 1 >= 0) {
                    up = dp[i - 1][j];
                }
                if (j - 1 >= 0) {
                    left = dp[i][j - 1];
                }
                int idx = i * 100 + j;
                if (up > left) {
                    dp[i][j] = up + grid[i][j];
                    List<Integer> list = new ArrayList<>(paths.get(idx - 100));
                    list.add(idx);
                    paths.put(idx, list);
                } else if (left != -1) {
                    dp[i][j] = left + grid[i][j];
                    List<Integer> list = new ArrayList<>(paths.get(idx - 1));
                    list.add(idx);
                    paths.put(idx, list);
                }
            }
        }
        if (dp[n - 1][n - 1] == -1) {
            return 0;
        }
        //清除已经摘过的樱桃,重置dp数组
        for (int idx : paths.get((n - 1) * 100 + n - 1)) {
            grid[idx / 100][idx % 100] = 0;
        }
        int start = dp[n - 1][n - 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        dp[n - 1][n - 1] = start;
        //第二轮dp
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == -1) {
                    continue;
                }
                int down = -1, right = -1;
                if (i + 1 < n) {
                    down = dp[i + 1][j];
                }
                if (j + 1 < n) {
                    right = dp[i][j + 1];
                }
                if (down == -1 && right == -1) {
                    continue;
                }
                dp[i][j] = Math.max(down, right) + grid[i][j];
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        CherryPickup cp = new CherryPickup();
//        int[][] grid = {{0,1,-1},{1,0,-1},{1,1,1}};
        int[][] grid = mock();
        System.out.println(cp.cherryPickup(grid));
    }

    private static int[][] mock() {
        String data = "[[1,1,1,1,0,0,0],[0,0,0,1,0,0,0],[0,0,0,1,0,0,1],[1,0,0,1,0,0,0],[0,0,0,1,0,0,0],[0,0,0,1,0,0,0],[0,0,0,1,1,1,1]]";
        int[][] grid = JSONUtil.readValue(data, int[][].class);
        return grid;
    }
}
