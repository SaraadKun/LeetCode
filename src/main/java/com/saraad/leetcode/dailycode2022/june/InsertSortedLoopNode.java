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

    //2022-08-02船新解法
    public Node insert(Node head, int insertVal) {
        //遍历一遍链表,找到最大值节点p,此时p.next即为最小值节点;若链表节点值均相等,则p为head的前置节点
        // 1) 若p.val > insertVal && p.next.val < insertVal, 则从p.next开始向后遍历,找到最后一个val小于insertVal的节点p,在p之后插入新节点
        // 2) 1)步结束后,可以判定p.val <= insertVal,在p之后插入新节点;若链表节点值均相等,也在p后插入
        if (head == null) {
            head = new Node(insertVal);
            head.next = head;
            return head;
        }
        Node p = head;
        while (p.next.val >= p.val && p.next != head) {
            p = p.next;
        }
        //此时p即为最大值节点(或者head的前置节点)
        if (p.val > insertVal && p.next.val < insertVal) {
            while (p.next.val < insertVal) {
                p = p.next;
            }
        }
        Node ne = p.next;
        p.next = new Node(insertVal, ne);
        return head;
    }

//    public Node insert(Node head, int insertVal) {
//        if (head == null){
//            head = new Node(insertVal);
//            head.next = head;
//            return head;
//        }
//
//        if (head.next == head) {
//            head.next = new Node(insertVal);
//            head.next.next = head;
//            return head;
//        }
//        Node p1 = head, p2 = head.next, max = p2;
//        int cnt = 0;
//        //1.val小于or大于所有节点 寻找到最大节点插入到其后
//        //2.val介于最大值和最小值之间，必定存在 v1 <= val <= v2, 找到第一个v1，插入其后
//        while (true) {
//            //遍历完一遍链表，说明为情况1
//            if (p1 == head) {
//                cnt++;
//                if (cnt > 1) {
//                    Node min = max.next;
//                    max.next = new Node(insertVal);
//                    max.next.next = min;
//                    break;
//                }
//            }
//            //情况2
//            if (p1.val <= insertVal && p2.val > insertVal) {
//                p1.next = new Node(insertVal);
//                p1.next.next = p2;
//                break;
//            }
//            p1 = p2;
//            p2 = p2.next;
//            max = max.next.val >= max.val ? max.next : max;
//        }
//        return head;
//    }

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
