package com.saraad.leetcode.dailycode2022.july;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 13-07-2022 19:20
 */

public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        //栈模拟
        Deque<Integer> stack = new ArrayDeque<>();
        for (int a : asteroids) {
            Integer p = stack.peek();
            if (p == null || (p != null && p < 0)) {
                stack.push(a);
                continue;
            }
            if (a + p == 0) {
                stack.pop();
            } else if (a + p > 0) {
                if (a > 0) {
                    stack.push(a);
                }
            } else {
                while (p != null && p > 0 && a + p < 0) {
                    stack.pop();
                    p = stack.peek();
                }
                if (p == null || (p != null && p < 0)) {
                    stack.push(a);
                } else if (p + a == 0) {
                    stack.pop();
                }
            }
        }
        int[] ans = new int[stack.size()];
        int i = stack.size() - 1;
        while (!stack.isEmpty()) {
            ans[i--] = stack.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        AsteroidCollision asteroidCollision = new AsteroidCollision();
        int[] asteroids = {-2,-2,1,-2};
        int[] ans = asteroidCollision.asteroidCollision(asteroids);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
    }

}
