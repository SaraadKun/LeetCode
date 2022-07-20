package com.saraad.leetcode.dailycode2022.july;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 20-07-2022 09:01
 */

public class ShiftGrid {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length, total = m * n;
        int[][] temp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int row = (i + (j + k) / n) % m;
                int col = (j + k) % n;
                temp[row][col] = grid[i][j];
            }
        }
        return Stream.of(temp).map(arr -> Arrays.stream(arr).mapToObj(Integer::valueOf).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
