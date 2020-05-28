package com.saraad.leetcode.dailycode;

import java.util.HashMap;
import java.util.Map;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: 202 快乐数
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 *
 *编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
 *
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/happy-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: saraad
 * @date: 2020/4/30 23:28
 * @Copyright: 2020  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class Code200430 {

    public static void main(String[] args) {
        int num = 2;
        boolean happy = isHappy(num);
        System.out.println(happy);
    }

    public static boolean isHappy(int n) {
        if (n <= 0) {return false;}
        Map<Integer, Integer> map = new HashMap<>();
        map.put(n,1);
        int sum = 0;
        int tmp = n;
        while (true) {
            while (tmp > 0) {
                sum += (int) Math.pow(tmp % 10, 2);
                tmp = tmp / 10;
            }
            if (sum == 1){
                return true;
            }
            //重复出现sum,无限循环
            if (map.put(sum, 1) != null){
                return false;
            }
            tmp = sum;
            sum = 0;
        }

    }

}
