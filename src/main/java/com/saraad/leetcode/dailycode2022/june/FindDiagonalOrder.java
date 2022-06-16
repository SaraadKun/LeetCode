package com.saraad.leetcode.dailycode2022.june;

import com.saraad.util.JSONUtil;

public class FindDiagonalOrder {

    int[][] dirs = {{-1, 1}, {1, -1}};

    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int total = m * n;
        int[] ans = new int[total];
        int i = 0, j = 0, cnt = 0;
        int pos = 0;
        while(cnt < total) {
            ans[cnt++] = mat[i][j];
            int[] dir = dirs[pos & 1];
            if (i + dir[0] >= m || (i + dir[0] < 0 && j + dir[1] < n)) {
                j++;
                pos++;
            } else if (j + dir[1] >= n || j + dir[1] < 0) {
                i ++;
                pos++;
            } else {
                i += dir[0];
                j += dir[1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        FindDiagonalOrder obj = new FindDiagonalOrder();
        int[][] mat = {{1,2,3},{4,5,6},{7,8,9}};
        int[] ans = obj.findDiagonalOrder(mat);
        System.out.println(JSONUtil.writeValueAsString(ans));
    }

}
