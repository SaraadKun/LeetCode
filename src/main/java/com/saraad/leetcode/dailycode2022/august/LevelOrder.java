package com.saraad.leetcode.dailycode2022.august;

import com.saraad.leetcode.bean.TreeNode;
import com.saraad.util.JSONUtil;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: 1. <a href="https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/">...</a>
 *        2. <a href="https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/">...</a>
 *        3. <a href="https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/">...</a>
 * @Date: 05-08-2022 00:24
 */

public class LevelOrder {
    // https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof
    public int[] levelOrderI(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        int[] arr = new int[1000];
        int cnt = 0;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            arr[cnt++] = cur.val;
            if (cur.left != null) {
                q.offer(cur.left);
            }
            if (cur.right != null) {
                q.offer(cur.right);
            }
        }
        return Arrays.copyOf(arr, cnt);
    }

    // https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/
    Map<Integer, List<Integer>> map = new HashMap<>();
    int maxDepth = -1;
    public List<List<Integer>> levelOrderII(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, 0);
        for (int i = 0; i <= maxDepth; i++) {
            ans.add(map.get(i));
        }
        return ans;
    }

    // https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
    public List<List<Integer>> levelOrderIII(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, 0);
        for (int i = 0; i <= maxDepth; i++) {
            if ((i & 1) == 1) {
                Collections.reverse(map.get(i));
            }
            ans.add(map.get(i));
        }
        return ans;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        List<Integer> list = map.get(depth);
        if (list == null) {
            list = new ArrayList<>();
            map.put(depth, list);
        }
        list.add(root.val);
        maxDepth = Math.max(depth, maxDepth);
        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }



    public static void main(String[] args) {
        int maxDepth = -1;
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        System.out.println(list);
        Collections.reverse(list);
        System.out.println(list);
    }
}
