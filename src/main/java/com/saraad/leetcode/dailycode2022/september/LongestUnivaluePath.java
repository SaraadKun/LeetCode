package com.saraad.leetcode.dailycode2022.september;

import com.saraad.leetcode.bean.TreeNode;
import com.saraad.leetcode.utils.TreeNodeUtil;
import org.junit.Assert;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 02-09-2022 03:22
 */

public class LongestUnivaluePath {

    int ans = 0;
    public int longestUnivaluePath(TreeNode root) {
        dfs(root, -1001);
        return ans;
    }

    public int dfs(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, root.val), right = dfs(root.right, root.val);
        ans = Math.max(left + right, ans);
        if (root.val == val) {
            return Math.max(left, right) + 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        LongestUnivaluePath obj = new LongestUnivaluePath();
        Assert.assertEquals(2, obj.longestUnivaluePath(mock("[1,4,5,4,4,5]")));
        Assert.assertEquals(4, obj.longestUnivaluePath(mock("[1,null,1,1,1,1,1,1]")));
        TreeMap<Integer, String> map = new TreeMap<>();
        Map.Entry<Integer, String> integerStringEntry = map.floorEntry(1);
    }

    private static TreeNode mock(String input) {
        return TreeNodeUtil.mock(input);
    }
}
