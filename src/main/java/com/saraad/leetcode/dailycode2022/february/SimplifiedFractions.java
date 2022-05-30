package com.saraad.leetcode.dailycode2022.february;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 1447. 最简分数
 */
public class SimplifiedFractions {

    public static void main(String[] args) {
        SimplifiedFractions instance = new SimplifiedFractions();
        List<String> list = instance.simplifiedFractions(4);
        System.out.println(JSON.toJSONString(list));
    }

    public List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            for(int j = 1; j < i; j++) {
//                if (validate(i, j)) {
                if (gcd(i, j) == 1) {
                    ans.add(j + "/" + i);
                }
            }
        }
        return ans;
    }

    private int gcd(int n, int m) {
//        return m == 0 ? n : gcd(m, n % m);
        for ( ; m != 0; ){
            int tmp = n % m;
            n = m;
            m = tmp;
        }
        return n;
    }

    // n > m
    private boolean validate(int n, int m) {
        if (n <= m) {
            return false;
        }
        while (m != 0) {
            int tmp = n % m;
            if (tmp == 1) {
                return true;
            }
            n = m;
            m = tmp;
        }
        return false;
    }

}
