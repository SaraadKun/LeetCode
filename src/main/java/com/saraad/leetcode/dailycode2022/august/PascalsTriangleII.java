package com.saraad.leetcode.dailycode2022.august;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 03-08-2022 17:37
 */

public class PascalsTriangleII {
    public List<Integer> getRow(int rowIndex) {
        int[] ans = new int[rowIndex + 1];
        ans[0] = 1;
        for (int i = 1; i <= rowIndex; i++) {
            ans[i] = (int)((long)ans[i - 1] * (rowIndex - i + 1) / i);
        }
        return Arrays.stream(ans).boxed().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        PascalsTriangleII obj = new PascalsTriangleII();
        System.out.println(obj.getRow(33));
    }
}
