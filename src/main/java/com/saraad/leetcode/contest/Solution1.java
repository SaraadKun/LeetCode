package com.saraad.leetcode.contest;

class Solution1 {

    int[][] arr;
    int m, n, ans = 0;
    int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public int solution(int[][] energy) {
        arr = energy;
        m = energy.length;
        n = energy[0].length;
        backtrack(0, 0, 0);
        return ans;
    }

    private void backtrack(int row, int col, int cnt) {
        if (row >= m || (m - row) * n - col + cnt <= ans) {
            return;
        }
        for (int i = row; i < m; i++) {
            for (int j = i == row ? col : 0; j < n; j++) {
                if (arr[i][j] == 0 && check(i, j)){
                    arr[i][j] = 1;
                    ans = Math.max (ans, cnt + 1);
                    if (j + 1 < n) {
                        backtrack(row, j + 1, cnt + 1);
                    } else {
                        backtrack(i + 1, 0, cnt + 1);
                    }
                    arr[i][j] = 0;
                }
            }
        }
    }

    private boolean check(int x, int y) {
        for (int[] dir : dirs) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && arr[nx][ny] > 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(factorial(20));

    }

    private static long factorial(int n ) {
        long res = 1;
        while (n > 1) {
            res *= n--;
        }
        return res;
    }


    private static int[][] mock() {
        int m = 2;
        int n = 4;
//        int[][] arr = new int[m][n];
//        int[][] arr = {{1, 0, 0, 0}, {0, 0, 0, 1}};
        int[][] arr = {
                {1, 0, 0, 0},
                {0, 0, 0, 1}

        };

        return arr;
    }
}