package com.saraad.leetcode.dailycode2022.july;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 01-07-2022 14:09
 */

public class DiffWaysToCompute {


    Set<Character> set = Set.of('+', '-', '*');
    List<Integer> operators = new ArrayList<>();

    public List<Integer> diffWaysToCompute(String expression) {
        //将所有运算符的位置都找出来,然后根据每一个运算符的坐标将expression划分为2部分,
        //分别计算2部分的结果数组,然后计算两个数组的笛卡尔积并加入结果数组中;对每一部分递归执行上述步骤,可得最终结果.
        for (int i = 0; i < expression.length(); i++){
            if (set.contains(expression.charAt(i)))
                operators.add(i);
        }
        return compute(expression, 0, expression.length(), 0, operators.size());
    }

    List<Integer> compute(String expr, int start, int end, int lo, int hi) {
        if (end - start <= 2)
            return List.of(Integer.parseInt(expr.substring(start, end)));
        List<Integer> ans = new ArrayList<>();
        for (int i = lo; i < hi; i++) {
            List<Integer> part1 = compute(expr, start, operators.get(i), lo, i);
            List<Integer> part2 = compute(expr, operators.get(i) + 1, end, i + 1, hi);
            char operator = expr.charAt(operators.get(i));
            for (Integer p1 : part1) {
                for (Integer p2 : part2) {
                    if (operator == '+') {
                        ans.add(p1 + p2);
                    } else if (operator == '-') {
                        ans.add(p1 - p2);
                    } else {
                        ans.add(p1 * p2);
                    }
                }
            }
        }
        return ans;
    }
}
