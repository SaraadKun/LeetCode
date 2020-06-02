package com.saraad.leetcode.dailycode;

import com.saraad.leetcode.bean.TreeNode;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: 112.路径总和
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: saraad
 * @date: 2020/6/3 4:27
 * @Copyright: 2020  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class Code200602 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */

    public static void main(String[] args) {
        TreeNode treeNode = mockData();
        int target = -5;
        boolean flag = hasPathSum(treeNode, target);
        System.out.println(flag);
    }

    private static TreeNode mockData() {
//        TreeNode root = new TreeNode(5);
//        root.left = new TreeNode(4);
//        root.left.left = new TreeNode(11);
//        root.left.left.left = new TreeNode(7);
//        root.left.left.right = new TreeNode(2);
//        root.right = new TreeNode(8);
//        root.right.left = new TreeNode(13);
//        root.right.right = new TreeNode(4);
//        root.right.right.right = new TreeNode(1);
        TreeNode root = new TreeNode(-2);
        root.left = null;
        root.left =  new TreeNode(-3);
        return root;
    }

    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null){
            return false;
        }
        sum -= root.val;
        if (root.left == null && root.right == null){
            return sum == 0;
        }else {
            return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
        }
    }

}
