package com.saraad.leetcode.dailycode2022.july;

import com.saraad.leetcode.bean.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 17-07-2022 15:50
 */

public class IsSameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        //DFS
        if (p == null && q == null) return true;
        if (p == null ^ q == null) return false;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public boolean isSameTreeBFS(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }
        //bfs
//        Queue<TreeNode> pq = new ArrayDeque<>();
        Queue<TreeNode> pq = new LinkedList<>();
        pq.offer(p);
        Queue<TreeNode> qq = new LinkedList<>();
        qq.offer(q);
        while (!pq.isEmpty() && !qq.isEmpty()) {
            TreeNode pc = pq.poll(), qc = qq.poll();
            if (pc == null && qc == null) continue;
            if (pc == null || qc == null || pc.val != qc.val) {
                return false;
            }
            pq.offer(pc.left);
            pq.offer(pc.right);
            qq.offer(qc.left);
            qq.offer(qc.right);
        }
        return pq.isEmpty() && qq.isEmpty();
    }

    public static void main(String[] args) {
        IsSameTree isSameTree = new IsSameTree();
        TreeNode p = new TreeNode(1);
        TreeNode q = new TreeNode(1);
        TreeNode p1 = new TreeNode(2);
        TreeNode q1 = new TreeNode(2);
        TreeNode p2 = new TreeNode(3);
        TreeNode q2 = new TreeNode(3);
        p.left = p1;
        p.right = p2;
        q.left = q1;
        q.right = q2;
        System.out.println(isSameTree.isSameTree(p, q));
    }
}
