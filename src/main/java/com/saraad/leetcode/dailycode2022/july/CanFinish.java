package com.saraad.leetcode.dailycode2022.july;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/course-schedule/
 * @Date: 21-07-2022 22:54
 */

public class CanFinish {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //邻接表预处理数据建图,计算每个课程的入度,每轮选入度为0的课程入队进行bfs
        //若队列为空时所有课程都已访问过则返回true,否则false. 可根据入度数组判断是否全部课程都被访问过
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        int[] cnts = new int[numCourses];
        for (int[] p : prerequisites) {
            adj.get(p[0]).add(p[1]);
            cnts[p[1]]++;
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (cnts[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int ne : adj.get(cur)) {
                //入度为0,入队
                if (--cnts[ne] == 0) {
                    q.offer(ne);
                }
            }
        }
        //判断是否可以完成所有课程
        for (int n : cnts) {
            if (n != 0) {
                return false;
            }
        }
        return true;
    }
}
