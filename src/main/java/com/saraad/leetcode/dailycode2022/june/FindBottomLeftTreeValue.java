package com.saraad.leetcode.dailycode2022.june;

import com.saraad.leetcode.bean.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 22-06-2022 16:16
 */

public class FindBottomLeftTreeValue {

    int maxDepth = 0;
    int ans;

    public int findBottomLeftValue(TreeNode root) {
        //dfs + 记录最大深度
        dfs(root, 1);
        return ans;
        //return bfs(root);  //or use bfs
    }

    void dfs(TreeNode root, int depth) {
        if (root == null)
            return;
        if (depth > maxDepth) {
            maxDepth = depth;
            ans = root.val;
        }
        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }

    int bfs(TreeNode root) {
        int ans = root.val;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            ans = q.peek().val;
            while (size-- > 0) {
                TreeNode node = q.poll();
                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // TODO 这个偷懒不写了
    }


}
