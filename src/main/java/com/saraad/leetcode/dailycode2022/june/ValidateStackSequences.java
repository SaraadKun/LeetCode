package com.saraad.leetcode.dailycode2022.june;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Stack;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/submissions/
 * @Date: 23-06-2022 19:07
 */

public class ValidateStackSequences {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        for (int num : pushed) {
            stack.push(num);
            while(!stack.isEmpty() && popped[i] == stack.peek()) {
                i++;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidateStackSequences obj = new ValidateStackSequences();
//        int[] pushed = {1,2,3,4,5};
//        int[] popped = {4,5,3,2,1};
        int[] pushed = {1,2,3,4,5};
        int[] popped = {4,3,5,1,2};
//        assert Objects.equals(obj.validateStackSequences(pushed, popped), true);
        assert Objects.equals(obj.validateStackSequences(pushed, popped), false);
    }

}
