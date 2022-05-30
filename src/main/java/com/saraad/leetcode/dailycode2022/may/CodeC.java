package com.saraad.leetcode.dailycode2022.may;

import com.alibaba.fastjson.JSON;
import com.saraad.leetcode.bean.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeC {

    class Tuple2 {

        int[] arr;
        int idx;

        boolean isEmpty() {
            return arr == null || arr.length == 0 || arr.length <= idx;
        }

        Tuple2(int[] arr, int idx) {
            this.arr = arr;
            this.idx = idx;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        //前序遍历
        dfs(root, list);
        String data = list.toString();
        return data.substring(1, data.length() - 1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        int[] arr = Arrays.stream(data.split(", ")).mapToInt(Integer::parseInt).toArray();
        Tuple2 tuple = new Tuple2(arr, 0);
        return reduction(Integer.MIN_VALUE, Integer.MAX_VALUE, tuple);

    }

    private void dfs(TreeNode root, List<Integer> data) {
        if (root == null) {
            return;
        }
        data.add(root.val);
        dfs(root.left, data);
        dfs(root.right, data);
    }

    private TreeNode reduction(int min, int max, Tuple2 data) {
        if (data.isEmpty() || data.arr[data.idx] < min || data.arr[data.idx] > max) {
            return null;
        }
        int val = data.arr[data.idx++];
        TreeNode root = new TreeNode(val);
        root.left = reduction(min, val, data);
        root.right = reduction(val, max, data);
        return root;
    }

    public static void main(String[] args) {
        TreeNode data = getData();
        CodeC instance = new CodeC();
        String str = instance.serialize(data);
        System.out.println(str);
        TreeNode res = instance.deserialize(str);
        System.out.println(JSON.toJSONString(data));
        System.out.println(JSON.toJSONString(res));
    }

    private static TreeNode getData() {
        TreeNode data = new TreeNode(2);
        data.left = new TreeNode(1);
        data.right = new TreeNode(3);
        return data;
    }
}
