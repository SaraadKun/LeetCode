package com.saraad.leetcode.dailycode2022.august;

import com.saraad.leetcode.bean.TreeNode;
import com.saraad.leetcode.dailycode2022.utils.TreeNodeUtil;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 27-08-2022 13:12
 */

public class WidthOfBinaryTree {
    int[][] arr;
    int h, ans = 0;

    public int widthOfBinaryTree(TreeNode root) {
        h = high(root);
        int n = (1 << h) - 1;
        arr = new int[h][2];
        for (int i = 0; i < h; i++) {
            arr[i][0] = n + 1;
            arr[i][1] = -1;
        }
        dfs(root, 0, n / 2);
        return ans;
    }

    private void dfs(TreeNode root, int row, int col) {
        if (root == null) {
            return;
        }
        arr[row][0] = Math.min(arr[row][0], col);
        arr[row][1] = Math.max(arr[row][1], col);
        ans = Math.max(ans, arr[row][1] - arr[row][0] + 1);
        dfs(root.left, row + 1, col - (1 << h - row - 2));
        dfs(root.right, row + 1, col + (1 << h - row - 2));
    }

    private int high(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(high(root.left), high(root.right)) + 1;
    }

    public static void main(String[] args) {
        WidthOfBinaryTree obj = new WidthOfBinaryTree();
        TreeNode root = mock();
        System.out.println(obj.widthOfBinaryTree(root));
    }

    private static TreeNode mock() {
        Integer[] arr = {1, 3, 2, 5, 3, null, 9};
        return TreeNodeUtil.mock(arr);
    }
}
