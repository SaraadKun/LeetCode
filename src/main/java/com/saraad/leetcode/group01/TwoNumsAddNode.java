package com.saraad.leetcode.group01;


import java.io.Serializable;

/**
 * @Title: TwoNumsAddNode
 * @Package:com.saraad.leetcode.group01
 * @Description:
 * @author: saraad
 * @date: 2019/11/28 5:42 下午
 * @Copyright: 2019  Inc. All rights reserved.
 */
public class TwoNumsAddNode {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(2);
        n1.next = new ListNode(4);
        n1.next.next = new ListNode(3);
        ListNode n2 = new ListNode(5);
        n2.next = new ListNode(6);
        n2.next.next = new ListNode(4);
//        Node result = addTwoNumbers(n1, n2, new Node(0));
        ListNode result = addTwoNumbers(n1, n2);
        System.out.println(123);
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2){
        if (l1 == null && l2 == null) return null;
        if (l1 == null) l1 = new ListNode(0);
        l1.val += l2 == null ? 0 : l2.val;
        if (l1.val > 9) {
            l1.val = l1.val % 10;
            if (l1.next == null)
                l1.next = new ListNode(1);
            else
                l1.next.val += 1;
            l1.next = addTwoNumbers(l1.next, l2 == null ? null : l2.next);
        } else {
            l1.next = addTwoNumbers(l1.next, l2 == null ? null : l2.next);
        }
        return l1;
    }

//    public static ListNode addTwoNumbers(ListNode n1, ListNode n2, ListNode reslut){
//        if (n1 == null && n2 == null && reslut.val == 0) return null;
//        reslut.val = reslut.val + (n1 == null ? 0 : n1.val) + (n2 == null ? 0 : n2.val);
//        if (reslut.val > 9){
//            reslut.val = reslut.val % 10;
//            reslut.next = addTwoNumbers((n1 == null ? null : n1.next), (n2 == null ? null : n2.next), new ListNode(1));
//        }else {
//            reslut.next = addTwoNumbers((n1 == null ? null : n1.next), (n2 == null ? null : n2.next), new ListNode(0));
//        }
//        return reslut;
//    }

}
class ListNode implements Serializable {

    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}
