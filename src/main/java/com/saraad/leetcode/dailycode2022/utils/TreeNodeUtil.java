package com.saraad.leetcode.dailycode2022.utils;

import com.saraad.leetcode.bean.TreeNode;
import com.saraad.util.JSONUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 02-09-2022 03:26
 */

public class TreeNodeUtil {

//        Integer[] arr = {1, 3, 2, 5, 3, null, 9};

    /**
     * @param arr
     * @return
     * @Description: 中序遍历数组转TreeNode
     */
    public static TreeNode mock(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int n = arr.length, idx = 1;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty() && idx < n) {
            TreeNode cur = q.poll();
            if (cur == null) {
                continue;
            }
            cur.left = arr[idx] == null ? null : new TreeNode(arr[idx]);
            idx++;
            cur.right = idx < n && arr[idx] == null ? null : new TreeNode(arr[idx]);
            idx++;
            q.offer(cur.left);
            q.offer(cur.right);
        }
        return root;
    }

    public static TreeNode mock(String input) {
        Integer[] arr = JSONUtil.readValue(input, Integer[].class);
        return mock(arr);
    }

    public static void main(String[] args) {
        String str = "[1, 3, 2, 5, 3, null, 9]";
        TreeNode mock = mock(str);
        System.out.println(mock.val);
    }
}
