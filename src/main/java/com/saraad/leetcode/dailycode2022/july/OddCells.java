package com.saraad.leetcode.dailycode2022.july;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 12-07-2022 20:00
 */

public class OddCells {

    public int oddCells(int m, int n, int[][] indices) {
        //分别统计每行每列的奇数数目,最后结果为 mx + ny - 2xy
        boolean[] rows = new boolean[m], cols = new boolean[n];
        for (int[] p : indices) {
            rows[p[0]] = !rows[p[0]];
            cols[p[1]] = !cols[p[1]];
        }
        int oddr = 0, oddc = 0;
        for (boolean r : rows)
            if (r) oddr++;
        for (boolean c : cols)
            if (c) oddc++;
        return oddr * n + oddc * m - 2 * oddr * oddc;
    }
}
