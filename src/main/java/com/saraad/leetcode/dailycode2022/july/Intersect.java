package com.saraad.leetcode.dailycode2022.july;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 15-07-2022 18:17
 */

public class Intersect {

    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {}
        public Node(int _isLeaf,int _val) {
            isLeaf = _isLeaf == 1;
            val = _val == 1;
        }

        public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    };

    public Node intersect(Node quadTree1, Node quadTree2) {
        Node node = new Node();
        //都为叶子节点,直接比较并返回一个叶子节点
        if (quadTree1.isLeaf && quadTree2.isLeaf) {
            node.isLeaf = true;
            node.val = quadTree1.val || quadTree2.val;
            return node;
        }
        //构造一个叶子节点
        Node child = new Node();
        child.isLeaf = true;
        if (quadTree1.isLeaf) {
            child.val = quadTree1.val;
            node.topLeft = intersect(child, quadTree2.topLeft);
            node.topRight = intersect(child, quadTree2.topRight);
            node.bottomLeft = intersect(child, quadTree2.bottomLeft);
            node.bottomRight = intersect(child, quadTree2.bottomRight);
        } else if (quadTree2.isLeaf){
            child.val = quadTree2.val;
            node.topLeft = intersect(child, quadTree1.topLeft);
            node.topRight = intersect(child, quadTree1.topRight);
            node.bottomLeft = intersect(child, quadTree1.bottomLeft);
            node.bottomRight = intersect(child, quadTree1.bottomRight);
        } else {
            node.topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
            node.topRight = intersect(quadTree1.topRight, quadTree2.topRight);
            node.bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
            node.bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);
        }
        boolean isAllLeaf = node.topLeft.isLeaf && node.topRight.isLeaf && node.bottomLeft.isLeaf && node.bottomRight.isLeaf;
        boolean isAllEqual = node.topLeft.val == node.topRight.val && node.topRight.val == node.bottomLeft.val && node.bottomLeft.val == node.bottomRight.val;
        if (isAllLeaf && isAllEqual) {
            node.val = node.topLeft.val;
            node.isLeaf = true;
            node.topLeft = null;
            node.topRight = null;
            node.bottomLeft = null;
            node.bottomRight = null;
        }
        return node;
    }

    public static void main(String[] args) {
        Node quadTree1 = mock1();
        Node quadTree2 = mock2();
        Node node = new Intersect().intersect(quadTree1, quadTree2);
        System.out.println(node.val);
    }

    //[[0,1],[1,1],[1,1],[1,0],[1,0]]
    private static Node mock1() {
        Node quadTree1 = new Node(0,1);
        quadTree1.topLeft = new Node(1,1);
        quadTree1.topRight = new Node(1,1);
        quadTree1.bottomLeft = new Node(1,0);
        quadTree1.bottomRight = new Node(1,0);
        return quadTree1;
    }
    //[[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
    private static Node mock2() {
        Node quadTree2 = new Node(0,1);
        quadTree2.topLeft = new Node(1,1);
        quadTree2.topRight = new Node(0,1);
        quadTree2.topRight.topLeft = new Node(1,0);
        quadTree2.topRight.topRight = new Node(1,0);
        quadTree2.topRight.bottomLeft = new Node(1,1);
        quadTree2.topRight.bottomRight = new Node(1,1);
        quadTree2.bottomLeft = new Node(1,1);
        quadTree2.bottomRight = new Node(1,0);
        return quadTree2;
    }
}
