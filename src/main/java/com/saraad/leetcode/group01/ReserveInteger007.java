package com.saraad.leetcode.group01;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: ReserveInteger007
 * @Package:com.saraad.leetcode.group01
 * @Description:
 * @author: saraad
 * @date: 2020/1/4 0:36
 * @Copyright: 2020  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class ReserveInteger007 {

    public static void main(String[] args) {
        System.out.println(reverse(123));
    }

    public static int reverse(int x){
        int sign = x < 0 ? -1 : 1;
        int rev = 0;
        int temp = (x < 0) ? -x : x;
        //从低位开始轮流取每一位数字并反转
        //考虑边界条件 2^31-1 Integer.MAX_VALUE = 2147483647
        while (temp > 0) {
            int pop = temp % 10;
            temp /= 10;
            if (rev>Integer.MAX_VALUE/10 || (rev==Integer.MAX_VALUE/10 && pop > 7))
                return 0;
            rev = rev * 10 + pop;
        }
        return sign * rev;
    }

}
