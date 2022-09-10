package com.saraad.leetcode.dailycode2022.september;

import com.saraad.leetcode.bean.TreeNode;
import com.saraad.leetcode.utils.TreeNodeUtil;
import org.junit.Assert;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 10-09-2022 01:44
 */

public class TrimBST {

    public TreeNode trimBST(TreeNode root, int low, int high) {
        while (root != null && (root.val < low || root.val > high)) {
            root = root.val < low ? root.right : root.left;
        }
        if (root == null) {
            return null;
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
    public static void main(String[] args) {
        TrimBST obj = new TrimBST();
        TreeNode root = TreeNodeUtil.mock("[45,30,46,10,36,null,49,8,24,34,42,48,null,4,9,14,25,31,35,41,43,47,null,0,6,null,null,11,20,null,28,null,33,null,null,37,null,null,44,null,null,null,1,5,7,null,12,19,21,26,29,32,null,null,38,null,null,null,3,null,null,null,null,null,13,18,null,null,22,null,27,null,null,null,null,null,39,2,null,null,null,15,null,null,23,null,null,null,40,null,null,null,16,null,null,null,null,null,17]");
        int low = 32, high = 44;
//        TreeNode root = TreeNodeUtil.mock("[18,0,40,null,2,22,49,1,17,21,32,45,null,null,null,9,null,19,null,29,37,44,47,8,13,null,20,26,30,33,39,42,null,46,48,3,null,10,16,null,null,24,27,null,31,null,35,38,null,41,43,null,null,null,null,null,4,null,12,14,null,23,25,null,28,null,null,34,36,null,null,null,null,null,null,null,7,11,null,null,15,null,null,null,null,null,null,null,null,null,null,5,null,null,null,null,null,null,6]");
//        int low = 35, high = 35;
        TreeNode ans = obj.trimBST(root, low, high);
        System.out.println(ans.val);
    }
}
