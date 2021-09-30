package com.saraad.leetcode.dailycode.april;

import com.saraad.leetcode.bean.TreeNode;

/**
 * 897
 */
public class IncreasingBST {

    public TreeNode increasingBST(TreeNode root) {
        TreeNode pre = new TreeNode( -1);
        dfs(pre, root);
        return pre.right;
    }

    private TreeNode dfs(TreeNode pre, TreeNode root) {
        if (root == null) {
            return pre;
        }
        pre = dfs(pre, root.left);
        pre.right = new TreeNode(root.val);
        pre = pre.right;
        pre = dfs(pre, root.right);
        return pre;
    }

}
