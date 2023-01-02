package com.saraad.leetcode.dailycode2022.october;

import org.junit.Assert;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 26-10-2022 22:13
 */

public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        //栈
        //1.维护全局变量,max(全局最大长度), cnt(当前合法子串的长度), pr(上一个非法右括号索引)
        //2.遍历s,遇到'('将其索引压栈,遇到')'判断若栈不为空则出栈,计算当前合法子串的长度(是否和上一个子串相邻,相邻则需合并长度),
        //用cnt更新max,遍历完成返回max
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int ans = 0, pr = -1;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else if (!stack.isEmpty()){
                stack.pop();
                int cnt = stack.isEmpty() ? i - pr : i - stack.peek();
                ans = Math.max(ans, cnt);
            } else {
                pr = i;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
//        LongestValidParentheses obj = new LongestValidParentheses();
//        Assert.assertEquals(4, obj.longestValidParentheses(")()())"));
//        List<Integer> list = null;
//        list.stream().filter(o ->true).count()
//        int a = 'A' - 0;
        System.out.println( Math.sqrt(4));
    }
}
