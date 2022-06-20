package com.saraad.leetcode.dailycode2022.june;

import com.saraad.leetcode.bean.Node;

public class InsertSortedLoopNode {

    public static void main(String[] args) {
        InsertSortedLoopNode obj = new InsertSortedLoopNode();
        Node head = mock();
        printNode(head);
        Node n = obj.insert(head, 4);
        printNode(n);
    }

    public Node insert(Node head, int insertVal) {
        if (head == null){
            head = new Node(insertVal);
            head.next = head;
            return head;
        }

        if (head.next == head) {
            head.next = new Node(insertVal);
            head.next.next = head;
            return head;
        }
        Node p1 = head, p2 = head.next, max = p2;
        int cnt = 0;
        //1.val小于or大于所有节点 寻找到最大节点插入到其后
        //2.val介于最大值和最小值之间，必定存在 v1 <= val <= v2, 找到第一个v1，插入其后
        while (true) {
            //遍历完一遍链表，说明为情况1
            if (p1 == head) {
                cnt++;
                if (cnt > 1) {
                    Node min = max.next;
                    max.next = new Node(insertVal);
                    max.next.next = min;
                    break;
                }
            }
            //情况2
            if (p1.val <= insertVal && p2.val > insertVal) {
                p1.next = new Node(insertVal);
                p1.next.next = p2;
                break;
            }
            p1 = p2;
            p2 = p2.next;
            max = max.next.val >= max.val ? max.next : max;
        }
        return head;
    }

    private static Node mock() {
        Node head = new Node(1);
        head.next = new Node(3);
        head.next.next = new Node(3);
        head.next.next.next = head;
//        Node head = new Node(1);
//        head.next = head;
//        Node head = null;
        return head;
    }

    private static void printNode(Node n) {
        if (n == null)
            return;
        System.out.printf("%d, ", n.val);
        Node start = n.next;
        while (start != null && start != n) {
            System.out.printf("%d, ", start.val);
            start = start.next;
        }
        System.out.println("\r");
    }

}
