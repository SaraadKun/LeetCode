package com.saraad.leetcode.dailycode;

public class Flatten {

    public Node flatten(Node head) {
        Node cur = head;
        while(cur != null) {
            Node next = cur.next;
            if (cur.child != null) {
                cur = flatChildren(cur);
                cur.next = next;
                next.prev = cur;
            }
            cur = next;
        }
        return head;
    }

    private Node flatChildren(Node p) {
        Node cur = p.child;
        p.next = cur;
        p.child = null;
        cur.prev = p;
        while(cur != null) {
            Node next = cur.next;
            if (cur.child != null) {
                cur = flatChildren(cur);
                cur.next = next;
                if (next != null) {
                    next.prev = cur;
                }
            }
            p = cur;
            cur = next;
        }
        return p;
    }

    public static void main(String[] args) {
        Flatten instance = new Flatten();
        Node head = mockData();
        Node res = instance.flatten(head);
        System.out.println(res.val);
    }

    private static Node mockData() {
        Node head = new Node(1);
        Node h2 = new Node(2);
        h2.prev = head;
        head.next = h2;
        Node h3 = new Node(3);
        h3.prev = h2;
        h2.next = h3;
        Node h4 = new Node(4);
        h4.prev = h3;
        h3.next = h4;
        Node h5 = new Node(5);
        h5.prev = h4;
        h4.next = h5;
        Node h6 = new Node(6);
        h6.prev = h5;
        h5.next = h6;

        Node h7 = new Node(7);
        h3.child = h7;
        Node h8 = new Node(8);
        h8.prev = h7;
        h7.next = h8;
        Node h9 = new Node(9);
        h9.prev = h8;
        h8.next = h9;
        Node h10 = new Node(10);
        h10.prev = h9;
        h9.next = h10;

        Node h11 = new Node(11);
        h8.child = h11;
        Node h12 = new Node(12);
        h12.prev = h11;
        h11.next = h12;

        return head;
    }

}

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
    public Node(){}
    public Node(int val){
        this.val = val;
    }
}
