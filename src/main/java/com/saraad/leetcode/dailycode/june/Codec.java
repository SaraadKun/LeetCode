package com.saraad.leetcode.dailycode.june;

import com.saraad.leetcode.bean.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return dfs(root, new StringBuilder()).toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> list = new ArrayList<>(Arrays.asList(data.split(",")));
        return parse(list);
    }

    private StringBuilder dfs(TreeNode root, StringBuilder builder) {
        if (root == null) {
            return builder.append("null").append(",");
        }
        builder.append(root.val).append(",");
        dfs(root.left, builder);
        dfs(root.right, builder);
        return builder;
    }

    private TreeNode parse(List<String> arr) {
        if ("null".equals(arr.get(0))) {
            arr.remove(0);
            return null;
        }
        TreeNode cur = new TreeNode(Integer.parseInt(arr.get(0)));
        arr.remove(0);
        cur.left = parse(arr);
        cur.right = parse(arr);
        return cur;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));