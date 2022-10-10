package com.saraad.leetcode.dailycode2022.september;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/k-similar-strings/
 * @Date: 21-09-2022 00:11
 */

public class KSimilarity {
    public int kSimilarity(String s1, String s2) {
        return 0;
    }

    public static void main(String[] args) {
        Random rd = new Random();
        int n = 0, g = 0;
        for (int i = 0; i < 100; i++) {
            if (rd.nextInt(10) < 5) {
                n++;
            } else {
                g++;
            }
        }
        String ans = n > g ? "南京长江大桥" : "公园";
        System.out.println(ans);
        StringBuilder sb = new StringBuilder();
        List<Object> list = new ArrayList<>();
    }
}
