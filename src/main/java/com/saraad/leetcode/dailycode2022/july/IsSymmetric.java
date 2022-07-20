package com.saraad.leetcode.dailycode2022.july;

import com.saraad.leetcode.bean.TreeNode;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 20-07-2022 19:36
 */

public class IsSymmetric {

    public boolean isSymmetric(TreeNode root) {
        //对称的比较根节点两颗子树的每一个节点
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null ^ right == null) {
            return false;
        }
        return left.val == right.val && dfs(left.left, right.right) && dfs(left.right, right.left);
    }
}
