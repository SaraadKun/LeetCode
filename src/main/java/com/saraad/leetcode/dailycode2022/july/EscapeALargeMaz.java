package com.saraad.leetcode.dailycode2022.july;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/escape-a-large-maze/
 * @Date: 03-07-2022 20:36
 */

public class EscapeALargeMaz {

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    //离散化
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        //离散化,相当于将非blocked点进行压缩
        //先排序,去重,然后对数据进行索引,最后对索引所构成的新集合进行搜索
        //使用treeSet实现去重和排序操作  treeSet存放所有的blocked点以及source和target点
        TreeSet<Integer> rows = new TreeSet<>();
        TreeSet<Integer> cols = new TreeSet<>();
        for (int[] b : blocked) {
            rows.add(b[0]);
            cols.add(b[1]);
        }
        rows.add(source[0]);
        cols.add(source[1]);
        rows.add(target[0]);
        cols.add(target[1]);
        //对数据进行索引,起始索引为下边界0
        Map<Integer, Integer> rMapping = new HashMap<>();
        Map<Integer, Integer> cMapping = new HashMap<>();
        int ridx = 0,  preRow = 0;
        for (int row : rows) {
            ridx += row == preRow ? 0 : preRow + 1 == row ? 1 : 2;
            rMapping.put(row, ridx);
            preRow = row;
        }
        if (preRow != 999999) {
            ridx++;
        }
        int cidx = 0, preCol = 0;
        for (int col : cols) {
            cidx += col == preCol ? 0 : preCol + 1 == col ? 1 : 2;
            cMapping.put(col, cidx);
            preCol = col;
        }
        if (preCol != 999999) {
            cidx++;
        }
        //构建新集合
        int[][] grid = new int[ridx + 1][cidx + 1];
        for (int[] b : blocked) {
            grid[rMapping.get(b[0])][cMapping.get(b[1])] = 1; //1表示blocked点
        }
        int sx = rMapping.get(source[0]), sy = cMapping.get(source[1]);
        int tx = rMapping.get(target[0]), ty = cMapping.get(target[1]);
        //BFS
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx, sy});
        grid[sx][sy] = 2; //2表示已访问的点
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int[] dir : dirs) {
                int nx = cur[0] + dir[0], ny = cur[1] + dir[1];
                if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] == 0) {
                    if (nx == tx && ny == ty) {
                        return true;
                    }
                    grid[nx][ny] = 2;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        return false;
    }

    //有限次BFS
    public boolean isEscapePossible1(int[][] blocked, int[] source, int[] target) {
        //N个block点利用边界最多可以包围住n * (n - 1) / 2个点(等差数列1,2,3...n-1)
        //从source开始bfs,若遍历的非block点不超过n * (n - 1) / 2 遍历结束,则说明source被包围住了,若能经过target,则返回true,否则返回false
        //若source开始bfs遍历超过n * (n - 1) / 2 个非block点,则说明source未被包围
        //从target开始bfs,若遍历的非block点不超过n * (n - 1) / 2 遍历结束,target被包围,返回false,否则返回true
        long LEN = 1000000;
        int t = blocked.length * (blocked.length - 1) / 2;
        //从source开始bfs
        Map<Long, Boolean> visited = new HashMap<>();
        for (int[] b : blocked) {
            visited.put(b[0] * LEN + b[1], true);
        }
        Queue<int[]> q = new ArrayDeque<>();
        int cnt = 0;
        q.offer(source);
        visited.put(source[0] * LEN + source[1], true);
        while (!q.isEmpty() && cnt <= t) {
            int[] cur = q.poll();
            for (int[] dir : dirs) {
                int nx = cur[0] + dir[0], ny = cur[1] + dir[1];
                if (nx >= 0 && nx < LEN && ny >= 0 && ny < LEN && !visited.containsKey(nx * LEN + ny)) {
                    if (nx == target[0] && ny == target[1]) {
                        return true;
                    }
                    cnt++;
                    q.offer(new int[]{nx, ny});
                    visited.put(nx * LEN + ny, true);
                }
            }
        }
        if (cnt <= t) {
            return false;
        }
        //从target开始bfs
        cnt = 0;
        q.clear();
        visited.clear();
        for (int[] b : blocked) {
            visited.put(b[0] * LEN + b[1], true);
        }
        q.offer(target);
        visited.put(target[0] * LEN + target[1], true);
        while (!q.isEmpty() && cnt <= t) {
            int[] cur = q.poll();
            for (int[] dir : dirs) {
                int nx = cur[0] + dir[0], ny = cur[1] + dir[1];
                if (nx >= 0 && nx < LEN && ny >= 0 && ny < LEN && !visited.containsKey(nx * LEN + ny)) {
                    if (nx == source[0] && ny == source[1]) {
                        return true;
                    }
                    cnt++;
                    q.offer(new int[]{nx, ny});
                    visited.put(nx * LEN + ny, true);
                }
            }
        }
        if (cnt <= t) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        EscapeALargeMaz e = new EscapeALargeMaz();
        int[][] blocked = {{0, 1}, {1, 0}, {1, 2}, {2, 1}};
        int[] source = {0, 0};
        int[] target = {2, 2};
        System.out.println(e.isEscapePossible(blocked, source, target));
    }

}
