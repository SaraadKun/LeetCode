package com.saraad.leetcode.dailycode2022.july;

import com.saraad.leetcode.bean.TreeNode;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/binary-tree-pruning/
 * @Date: 21-07-2022 22:55
 */

public class PruneTree {

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }
}
