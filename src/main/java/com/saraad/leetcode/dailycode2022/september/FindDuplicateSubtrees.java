package com.saraad.leetcode.dailycode2022.september;

import com.saraad.leetcode.bean.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 05-09-2022 12:37
 */

public class FindDuplicateSubtrees {

    Map<String, Integer> map = new HashMap<>();
    List<TreeNode> ans = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return ans;
    }

    private String dfs(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String key = sb.append(root.val).append("_")
                .append(dfs(root.left)).append("_")
                .append(dfs(root.right)).toString();
        Integer cnt = map.getOrDefault(key, 0);
        if (cnt == 1) {
            ans.add(root);
        }
        map.put(key, cnt + 1);
        return key;
    }

    public static void main(String[] args) {
        System.out.println(null == null);
    }
}
