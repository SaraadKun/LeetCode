package com.saraad.leetcode.dailycode2022.august;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 07-08-2022 01:57
 */

public class ExclusiveTime {
    public int[] exclusiveTime(int n, List<String> logs) {
        //假设日志严格有序且合法,所以不对日志格式情况做判断
        //遍历logs,对每一条log:
        // 若为start,则压栈,同时若stack不为空,维护一个当前函数与它的父级函数的映射关系
        // 若为end,pop()并统计当前函数时间,同时找到当前函数的父级函数,父级函数减去当前函数占用时间
        int[] ans = new int[n];
        Deque<int[]> stack = new ArrayDeque<>(); // arr[0] 为函数索引, arr[1] 为函数开始时间
        for (String log : logs) {
            String[] arr = log.split(":");
            if ("start".equals(arr[1])) {
                int idx = Integer.parseInt(arr[0]), t = Integer.parseInt(arr[2]);
                stack.push(new int[]{idx, t});
            } else {
                int[] cur = stack.pop();
                int diff = Integer.parseInt(arr[2]) - cur[1] + 1;
                ans[cur[0]] += diff;
                if (!stack.isEmpty()) {
                    ans[stack.peek()[0]] -= diff;
                }
            }
        }
        return ans;
    }
}
