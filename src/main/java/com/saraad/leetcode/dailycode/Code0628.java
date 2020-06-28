package com.saraad.leetcode.dailycode;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: 143 重排链表
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 *
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 *
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorder-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: saraad
 * @date: 2020/6/28 22:58
 * @Copyright: 2020  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class Code0628 {

    public static void main(String[] args) {
        ListNode head = mockData();
        reorderList(head);
        System.out.println("end");

    }


    public static void reorderList(ListNode head) {
        if(head == null || head.next == null){return;}
        ListNode pre = new ListNode(-1);
        pre.next = head;
        //快慢指针寻找中间节点
        ListNode fast = pre;
        ListNode slow = pre;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode tail = slow.next;
        slow.next = null;
        reorderRecursion(head, tail);
    }

    public static ListNode reorderRecursion(ListNode l, ListNode r){
        if(r.next != null){
            l = reorderRecursion(l, r.next);
        }
        if(l != null){
            ListNode next = l.next;
            l.next = r;
            r.next = next;
            l = next;;
        }
        return l;
    }

    static  ListNode mockData(){
        ListNode head = new ListNode(1);
        ListNode p = head;
        p.next = new ListNode(2);
        p = p.next;
        p.next = new ListNode(3);
        p = p.next;
        p.next = new ListNode(4);
        p = p.next;
        p.next = new ListNode(5);
        p = p.next;
        p.next = new ListNode(6);
        p = p.next;
        return head;
    }
}
