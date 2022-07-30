package com.saraad.leetcode.dailycode2022.july;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 29-07-2022 14:59
 */

public class SwimInWater {

    int[] id;

    private boolean connected(int x, int y) {
        return find(x) == find(y);
    }

    private int find(int x) {
        if (id[x] != x) {
            id[x] = find(id[x]);
        }
        return id[x];
    }

    private void union(int x, int y) {
        int idx = find(x), idy = find(y);
        if (idx == idy) {
            return;
        }
        id[idx] = idy;
    }

    public int swimInWater(int[][] grid) {
        //遍历点集,相邻的点构造边并附权重,对边按权重排序
        //创建UF,遍历排序后的边集合,每加一条边判断首尾点是否连通
        //若联通,则本次添加的边的权重即为最大高度,也即最大等待时间;
        int n = grid.length;
        //初始化UF
        id = new int[n * n];
        for (int i = 0; i < n * n; i++) {
            id[i] = i;
        }
        int[][] edges = new int[2 * n * (n - 1)][];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cid = i * n + j;
                //构造边
                if (j != n - 1) {
                    edges[idx++] = new int[]{cid, cid + 1, Math.max(grid[i][j], grid[i][j + 1])};
                }
                if (i != n - 1) {
                    edges[idx++] = new int[]{cid, cid + n, Math.max(grid[i][j], grid[i + 1][j])};
                }
            }
        }
        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));
        int start = 0, end = n * n - 1;
        for (int[] edge : edges) {
            union(edge[0], edge[1]);
            if (connected(start, end)) {
                return edge[2];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0, 2}, {1, 3}};
        SwimInWater swimInWater = new SwimInWater();
        System.out.println(swimInWater.swimInWater(grid));
    }
}
