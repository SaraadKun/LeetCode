package com.saraad.leetcode.dailycode2022.august;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 10-08-2022 17:19
 */

public class SolveEquation {
    public String solveEquation(String equation) {
        //equation 只有一个 '='.
        String[] eqs = equation.split("=");
        //分别统计等式两边的表达式,处理为ax+b的形式,以数组形式返回
        int[] eq1 = process(eqs[0]);
        int[] eq2 = process(eqs[1]);
        int coe = eq1[0] - eq2[0], con = eq2[1] - eq1[1];
        if (coe == 0) {
            return con == 0 ? "Infinite solutions" : "No solution";
        }
        return "x=" + con / coe;
    }

    // int[] arr, arr[0]为x系数,arr[1]为常数项
    private int[] process(String eq) {
        char[] chs = eq.toCharArray();
        int[] ans = new int[2];
        int n = chs.length, sign = 1, idx = 1, cur = 0;
        for (int i = 0; i < n; i++) {
            char ch = chs[i];
            if ('-' == ch || '+' == ch) {
                ans[idx] += cur * sign;
                cur = 0;
                idx = 1;
                sign = ch == '-' ? -1 : 1;
                continue;
            }
            if (ch == 'x') {
                cur = cur == 0 ? (i > 0 && chs[i - 1] == '0' ? 0 : 1) : cur;
                idx = 0;
            } else {
                cur = cur * 10 + ch - '0';
            }
        }
        ans[idx] += cur * sign;
        return ans;
    }
}
