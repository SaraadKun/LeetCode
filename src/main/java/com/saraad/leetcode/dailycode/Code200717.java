package com.saraad.leetcode.dailycode;


/**
 * @Title: Code200717
 * @Package:com.saraad.leetcode.dailycode
 * @Description: 148. 排序链表
 *
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 示例 1:
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * @author: saraad
 * @date: 2020/7/17 4:49 下午
 * @Copyright: 2020  Inc. All rights reserved.
 */
public class Code200717 {

    public static void main(String[] args) {

    }

    public static  ListNode sortList(ListNode head) {
        if (head == null || head.next == null){return head;}
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        //求链表长度
        int len = 0;
        for (ListNode p = head; p != null; p=p.next) len++;
        //每次归并步长 [1,len/2]
        for (int i = 1; i < len; i = 2*i){
            ListNode pre = dummy;
            //j<len-i => 最后一段为有序,不需要再次合并
            for (int j = 0; j < len-i; j+=2*i) {
                ListNode first = pre.next;
                ListNode second = pre.next;
                for (int k = 0; k < i; k++) second = second.next;
                int f = 0, s = 0;
                //归并
                while (f < i && s < i && second != null){
                    if (first.val < second.val){
                        pre.next = first;
                        pre = pre.next;
                        first = first.next;
                        f++;
                    }else {
                        pre.next = second;
                        pre = pre.next;
                        second = second.next;
                        s++;
                    }
                }
                //循环结束后左侧链表段可能未遍历完
                while (f < i){
                    pre.next = first;
                    pre = pre.next;
                    first = first.next;
                    f++;
                }
                //右侧未遍历完
                while (s < i && second != null){
                    pre.next = second;
                    pre = pre.next;
                    second = second.next;
                    s++;
                }
                //归并后的尾结点指向下次归并起始节点
                pre.next = second;
            }
        }
        return dummy.next;
    }

}
