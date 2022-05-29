package com.saraad.leetcode.temp;

/**
 * @Title: TT2
 * @Package:com.saraad.leetcode.temp
 * @Description:
 * @author: saraad
 * @date: 2021/6/8 6:26 下午
 * @Copyright: 2021  Inc. All rights reserved.
 */
public class TT2 {

    public static void main(String[] args) {
        int i = 253;
        int k = 1;
        while ((i >>= 1) > 0) {
            if ((i & 1) == 1) {
                System.out.println(1 << k);
            }
            ++k;
        }
    }

}
