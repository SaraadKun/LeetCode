package com.saraad.leetcode.dailycode2022.july;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 31-07-2022 11:23
 */

public class ClosestMeetingNode {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        if (node1 == node2) {
            return node1;
        }
        int max = edges.length, ans = edges.length;
        int[] visited = new int[edges.length];
        Queue<int[]> q1 = new ArrayDeque<>();
        Queue<int[]> q2 = new ArrayDeque<>();
        q1.offer(new int[]{node1, 0});
        q2.offer(new int[]{node2, 0});
        for (int i = 0; i < edges.length && (!q1.isEmpty() || !q2.isEmpty()); i++) {
            int[] c1 = null, c2 = null;
            if (!q1.isEmpty()) {
                c1 = q1.poll();
            }
            if (!q2.isEmpty()) {
                c2 = q2.poll();
            }
            if (c1 != null && c1[0] != -1) {
                visited[c1[0]] |= 1;
                q1.offer(new int[]{edges[c1[0]], c1[1] + 1});
                if (visited[c1[0]] == 3) {
                    if (max > c1[1]) {
                        max = c1[1];
                        ans = c1[0];
                    } else if (max == c1[1]) {
                        ans = Math.min(ans, c1[0]);
                    }
                }
            }
            if (c2 != null && c2[0] != -1) {
                visited[c2[0]] |= 2;
                q2.offer(new int[]{edges[c2[0]], c2[1] + 1});
                if (visited[c2[0]] == 3) {
                    if (max > c2[1]) {
                        max = c2[1];
                        ans = c2[0];
                    } else if (max == c2[1]) {
                        ans = Math.min(ans, c2[0]);
                    }
                }
            }
        }
        return ans == edges.length ? -1 : ans;
    }

    public static void main(String[] args) {
//        int[] edges = new int[] {2,2,3,-1};
//        int[] edges = new int[] {5,4,5,4,3,6,-1};
//        int node1 = 0, node2 = 1;
//        int[] edges = new int[] {4,4,8,-1,9,8,4,4,1,1};
//        int node1 = 5, node2 = 6;
        int[] edges = new int[] {4,3,0,5,3,-1};
        int node1 = 4, node2 = 0;
        System.out.println(new ClosestMeetingNode().closestMeetingNode(edges, node1, node2));
    }
}
