package com.saraad.leetcode.dailycode;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: 234. 回文链表
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: saraad
 * @date: 2020/6/10 22:18
 * @Copyright: 2020  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class Code200610 {

    public boolean isPalindrome(ListNode head) {
        if(head == null){
            return true;
        }
        int len = 0;
        int i = 0;
        ListNode p = head;
        while (p != null){
            len ++;
            i ^= p.val;
            p = p.next;
        }
        if (len % 2 == 0){
            if (i == 0){
                return true;
            }
        }else {
            int j = 0;
            p = head;
            while (j < len/2){
                p = p.next;
                j++;
            }
            if (i == p.val){
                return true;
            }
        }
        return false;
    }



    //O2n时间,On空间
    public boolean isPalindromeslow(ListNode head) {
        if(head == null){
            return true;
        }
        //反转链表
        ListNode reverse = new ListNode(-1);
        ListNode next = reverse.next;
        ListNode p = head;
        while (p != null){
            reverse.next = new ListNode(p.val);
            reverse.next.next = next;
            next = reverse.next;
            p = p.next;
        }
        //遍历对比2链表
        p = head;
        while (p != null){
            if (p.val != next.val){
                return false;
            }
            p = p.next;
            next = next.next;
        }
        return true;
    }

}
