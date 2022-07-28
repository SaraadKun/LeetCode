package com.saraad.leetcode.dailycode2022.july;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 27-07-2022 17:55
 */

public class FractionAddition {

    public String fractionAddition(String expression) {
        //遍历输入字符串,使用三元组存放当前分数 [符号位, 分子, 分母]
        //因为分母范围为[1,10],分数个数[1,10],故 p= ∏10,i=1 i 一定是所有分母的公倍数
        int p = 1;
        for (int i = 2; i <= 10; i++) {
            p *= i;
        }
        //分母为p时的分子
        int numerators = 0;
        char[] chs = expression.toCharArray();
        int n = chs.length;
        for (int i = 0; i < n; i++) {
            int symbol, numerator, denominator;
            int[] arr = new int[3];
            //处理首位符号
            if (i == 0 && chs[i] != '-') {
                symbol = 1;
            } else {
                symbol = chs[i++] == '-' ? -1 : 1;
            }
            //处理分子
            if (chs[i + 1] == '0') {
                numerator = 10;
                i += 2;
            } else {
                numerator = chs[i++] - '0';
            }
            //处理分母
            if (i + 2 < n && chs[i + 2] == '0') {
                denominator = 10;
                i += 2;
            } else {
                denominator = chs[++i] - '0';
            }
            //根据公倍数p计算分子
            numerators += symbol * numerator * (p / denominator);
        }
        StringBuilder sb = new StringBuilder();
        if (numerators < 0) {
            sb.append('-');
            numerators *= -1;
        }
        //计算分子分母最大公约数,化简分数
        int gcd = gcd(p, numerators);
        sb.append(numerators / gcd).append('/').append(p / gcd);
        return sb.toString();
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        FractionAddition fa = new FractionAddition();
        System.out.println(fa.fractionAddition("-1/2+1/2"));
        System.out.println(fa.fractionAddition("-1/2+1/2+1/3"));
        System.out.println(fa.fractionAddition("-10/7+1/9+2/7+2/1-1/3+3/10-1/10+8/7-4/9-3/2"));
        System.out.println(fa.fractionAddition("-1/2+1/2+1/3+1/4+1/5"));
        System.out.println(fa.fractionAddition("-1/2+1/2+1/3+1/4+1/5+1/6"));
        System.out.println(fa.fractionAddition("-1/2+1/2+1/3+1/4+1/5+1/6+1/7"));
        System.out.println(fa.fractionAddition("-1/2+1/2+1/3+1/4+1/5+1/6+1/7+1/8"));
        System.out.println(fa.fractionAddition("-1/2+1/2+1/3+1/4+1/5+1/6+1/7+1/8+1/9"));
        System.out.println(fa.fractionAddition("-1/2+1/2+1/3+1/4+1/5+1/6+1/7+1/8+1/9+1/10"));
    }
}
