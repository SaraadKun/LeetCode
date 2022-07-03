package com.saraad.leetcode.dailycode2022.july;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 03-07-2022 18:39
 */

public class NextGreaterElement {
    public int nextGreaterElement(int n) {
        List<Integer> list = new ArrayList<>();
        while (n > 0) {
            list.add(n % 10);
            n /= 10;
        }
        //从低位向高位遍历,找到第一个降序排列的数对坐标lo, hi(即 lo > hi),此时[0, lo]为升序
        //从低位遍历升序区间[0, lo],找到第一个大于hi的坐标,并与hi交换,交换后[0, lo]仍为升序
        //翻转区间[0,lo],所得结果即为第一个大于n的值,判断边界条件返回
        boolean flag = true;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) > list.get(i)) {
                int tmp = list.get(i - 1);
                list.set(i - 1, list.get(i));
                list.set(i, tmp);
                flag = false;
                break;
            }
        }
        //若循环未找到符合要求的数对,返回-1
        if (flag) {
            return -1;
        }
        long sum = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            sum = sum * 10 + list.get(i);
            if (sum > Integer.MAX_VALUE) {
                return -1;
            }
        }
        return (int) sum;
    }

    public static void main(String[] args) {
        NextGreaterElement nge = new NextGreaterElement();
        System.out.println(nge.nextGreaterElement(230421));
    }
}
