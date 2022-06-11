package com.saraad.leetcode.dailycode;

public class MinSteps {

    public static void main(String[] args) {
        MinSteps instance = new MinSteps();
        int res = instance.minSteps(15);
        System.out.println(res);

    }

    public int minSteps(int n) {
        if (n == 1) {
            return 0;
        }
        return calFactor(n, 0);
    }

    private int calFactor(int n, int res) {
        int m = (int)Math.sqrt(n);
        //找到n最小的质因数,将商递归处理
        for (int i = 2; i <= m; i++) {
            if (n % i == 0) {
                return calFactor(n / i, res + i);
            }
        }
        return res + n;
    }

}
