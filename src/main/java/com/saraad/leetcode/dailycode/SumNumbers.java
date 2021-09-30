package com.saraad.leetcode.dailycode;

import com.saraad.leetcode.bean.TreeNode;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: SumNumbers
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * @author: saraad
 * @date: 2020/10/29 23:39
 * @Copyright: 2020  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class SumNumbers {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        int sum = new SumNumbers().sumNumbers(root);
        System.out.println(sum);
    }

    public int sumNumbers(TreeNode root) {
        if (root == null){
            return 0;
        }
        return calcNumbers(0, root);
    }
    public int calcNumbers(int num, TreeNode node) {
        num = num*10 + node.val;
        if (node.left == null && node.right == null){
            return num;
        }
        int sum = 0;
        if (node.left != null)
            sum += calcNumbers(num, node.left);
        if (node.right != null)
            sum += calcNumbers(num, node.right);
        return sum;
    }


}
