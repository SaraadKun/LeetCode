package com.saraad.leetcode.dailycode2022.july;

import com.saraad.leetcode.bean.TreeNode;

class CBTInserter {

    int high, cnt;
    TreeNode root;

    //统计当前树的节点总数,已排满的树的高度
    public CBTInserter(TreeNode root) {
        this.root = root;
        dfs(root);
        updateHigh();
    }
    
    public int insert(int val) {
        cnt++;
        int res = addNode(root, cnt - (1 << high), high - 1, val);
        updateHigh();
        return res;
    }
    
    public TreeNode get_root() {
        return root;
    }

    private int addNode(TreeNode root, int idx, int h, int val){
        if (h == 0) {
            if (idx < 1) {
                root.left = new TreeNode(val);
            } else {
                root.right = new TreeNode(val);
            }
            return root.val;
        }
        int m = 1 << h;
        if (idx < m) {
            return addNode(root.left, idx, h - 1, val);
        } else {
            return addNode(root.right, idx - m, h - 1, val);
        }
    }

    private void updateHigh() {
        int n = cnt + 1, h = 0;
        while ((n >>= 1) > 0) {
            h++;
        }
        high = h;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        cnt++;
        dfs(root.left);
        dfs(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = mock();
        CBTInserter cbt = new CBTInserter(root);
        System.out.println(cbt.insert(3));
        System.out.println(cbt.insert(4));
        System.out.println(cbt.get_root().val);

    }

    private static TreeNode mock() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.left.left = new TreeNode(4);
//        root.left.right = new TreeNode(5);
//        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(7);
        return root;
    }
}