package com.saraad.leetcode.dailycode2022.october;

import com.saraad.leetcode.utils.ArrayUtil;
import org.junit.Assert;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 25-10-2022 05:57
 */

public class ShortestBridge {

    int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};;
    Queue<int[]> q = new ArrayDeque<>();
    int n;
    int[][] g;

    public int shortestBridge(int[][] grid) {
        //dfs确定第一座岛的坐标,入队列,然后用bfs扩散找到第二座岛
        n = grid.length;
        g = grid;
        boolean[][] visited = new boolean[n][n];
        out: for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dfs(i, j, visited);
                    break out;
                }
            }
        }
        //将一组岛屿的坐标入队,dfs直到找到另一组岛屿的坐标,当前bfs的轮次即为最短距离
        while (!q.isEmpty()) {
            int[] arr = q.poll();
            int x = arr[0], y = arr[1], cnt = arr[2];
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
                    if (grid[nx][ny] == 1) {
                        return cnt;
                    }
                    q.offer(new int[]{nx, ny, cnt + 1});
                    visited[nx][ny] = true;
                }
            }
        }
        return 0;
    }

    private void dfs(int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        q.offer(new int[]{x, y, 0});
        for (int[] dir : dirs) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && g[nx][ny] == 1 && !visited[nx][ny]) {
                dfs(nx, ny, visited);
            }
        }
    }

    public static void main(String[] args) {
        ShortestBridge obj = new ShortestBridge();
        Assert.assertEquals(1, obj.shortestBridge(ArrayUtil.mock2d("[[0,1],[1,0]]")));
        Deque<Integer> dq = new ArrayDeque<>();
    }
}
