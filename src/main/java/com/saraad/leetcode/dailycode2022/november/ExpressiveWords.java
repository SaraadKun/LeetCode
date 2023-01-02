package com.saraad.leetcode.dailycode2022.november;

import com.saraad.leetcode.utils.ArrayUtil;
import org.junit.Assert;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 25-11-2022 20:39
 */

public class ExpressiveWords {

    public int expressiveWords(String s, String[] words) {
        int n = s.length(), ans = 0;
        for (String word : words) {
            int len = word.length(), i = 0, j = 0;
            boolean flag = true;
            while (flag && i < n && j < len) {
                if (word.charAt(j) == s.charAt(i)) {
                    int cnt1 = 1, cnt2 = 1;
                    while (i < n - 1 && s.charAt(i + 1) == s.charAt(i)) {
                        i++;
                        cnt1++;
                    }
                    while (j < len - 1 && word.charAt(j + 1) == word.charAt(j)) {
                        j++;
                        cnt2++;
                    }
                    if (cnt1 < cnt2 || (cnt1 > cnt2 && cnt1 < 3)) {
                        flag = false;
                    }
                    i++;
                    j++;
                } else {
                    flag = false;
                }
            }
            if (i != n || j != len) {
                flag = false;
            }
            ans += flag ? 1 : 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        ExpressiveWords obj = new ExpressiveWords();
//        Assert.assertEquals(1, obj.expressiveWords("heeellooo", ArrayUtil.mockStringArray("[\"hello\", \"hi\", \"helo\"]")));
        double rate = 0.02,
//               base = 40000d;
               base = 269576.84;
        int years = 30;
        double r = base * years, t = 0;
//        for (int i = years; i >= 0; i--) {
//            t += obj.calc(rate, base, i);
//        }
        t = obj.calc(rate, base, years);
        r = base;
        System.out.println(r);
        System.out.println(t);
    }

    double calc(double rate, double base, int years) {
        double r = 1 + rate, target = base;
        for (int i = 0; i < years; i++) {
            target *= r;
        }
        return target;
    }
}
