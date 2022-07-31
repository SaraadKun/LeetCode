package com.saraad.leetcode.dailycode2022.july;

import com.saraad.leetcode.bean.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 31-07-2022 00:48
 */

public class MaxLevelSum {
    public int maxLevelSum(TreeNode root) {
        //BFS + 全局变量计数,按层遍历
        int ans = 0, max = -100001;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int step = 1;
        while (!q.isEmpty()) {
            int sz = q.size(), sum = 0;
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                sum += cur.val;
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            if (sum > max) {
                max = sum;
                ans = step;
            }
            step++;
        }
        return ans;
    }

    public static void main(String[] args) {
        MaxLevelSum maxLevelSum = new MaxLevelSum();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(7);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(-8);
        System.out.println(maxLevelSum.maxLevelSum(root));
    }
}
