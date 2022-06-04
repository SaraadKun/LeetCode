package com.saraad.structure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saraad.util.JSONUtil;

public class UF {

    char[][] g;
    int m, n;
    int[] p;
    int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public void solve(char[][] board) {
        //并查集 + dfs
        g = board;
        m = board.length;
        n = board[0].length;
        p = new int[m * n];
        for (int i = 0; i < p.length; i++){
            p[i] = i;
        }
        // for (int i = 0; i < m; i++) {
        //     for (int j = 0; j < n; j++) {
        //         p[getId(i, j)] = getId(i, j);
        //     }
        // }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == m -1 || j == n - 1) {
                    if (g[i][j] != 'O' || check(getId(i, j), 0)) {
                        continue;
                    }
                    dfs(i, j);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 'O' && !check(getId(i, j), 0)) {
                    g[i][j] = 'X';
                }
            }
        }

    }

    void dfs(int x, int y) {
        union(getId(x, y), 0);
        for (int[] dir : dirs) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx <= 0 || ny <= 0 || nx >= m - 1 || ny >= n - 1 || g[nx][ny] != 'O' || check(getId(nx, ny), 0)) {
                continue;
            }
            dfs(nx, ny);
        }
    }

    boolean check(int a, int b) {
        return find(a) == find(b);
    }

    int find(int a) {
        if (p[a] != a) {
            p[a] = find(p[a]);
        }
        return p[a];
    }

    void union(int a, int b) {
        p[find(a)] = find(b);
    }

    int getId(int x, int y) {
        return x * n + y;
    }

    public static void main(String[] args) {
        char[][] borad = new char[][]{{'O'}};
        UF uf = new UF();
        uf.solve(borad);
        System.out.println(JSONUtil.writeValueAsString(borad));
    }

}
