package com.saraad.leetcode.dailycode2022.august;

import java.util.Arrays;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: 1374. 生成每种字符都是奇数个的字符串
 * @Date: 01-08-2022 23:07
 */

public class GenerateTheString {

    public String generateTheString(int n) {
        char[] chs = new char[n];
        Arrays.fill(chs, 'a');
        if ((n & 1) == 0) {
            chs[0] = 'b';
        }
        return new String(chs);
    }

}
