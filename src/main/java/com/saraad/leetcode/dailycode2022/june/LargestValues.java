package com.saraad.leetcode.dailycode2022.june;

import com.saraad.leetcode.bean.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 515. 在每个树行中找最大值
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/find-largest-value-in-each-tree-row/
 * @Date: 24-06-2022 08:37
 */

public class LargestValues {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans, 0);
        return ans;
    }

    private void dfs(TreeNode root, List<Integer> ans, int h) {
        if (root == null)
            return;
        if (ans.size() == h)
            ans.add(root.val);
        else
            ans.set(h, Math.max(ans.get(h), root.val));
        dfs(root.left, ans, h + 1);
        dfs(root.right, ans, h + 1);
    }

    public static void main(String[] args) {
        //TODO 实现一个数组转TreeNode测试用例的方法
    }

}
