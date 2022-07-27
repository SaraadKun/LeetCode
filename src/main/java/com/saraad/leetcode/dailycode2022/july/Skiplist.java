package com.saraad.leetcode.dailycode2022.july;

import com.alibaba.fastjson.JSON;
import com.saraad.leetcode.dailycode.may.ArrayDecode;
import com.saraad.util.JSONUtil;

import java.util.*;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/design-skiplist/
 * @Date: 26-07-2022 22:54
 */
class Skiplist {

    static class Node {
        int val; //data
        int cnt; //data计数,用于保存相同的值,若cnt > 1,则删除时,原始列表中对应节点cnt-1;否则,删除节点及索引
        Node next; //同级链表右侧节点
        Node down; //下级链表节点,原始数据down==null

        public Node(int val) {
            this.val = val;
            this.cnt = 1;
        }

        public Node(int val, Node down) {
            this.val = val;
            this.cnt = 1;
            this.down = down;
        }
    }

    private Node head; //head指向最高层的索引头结点
    private Random random;

    public Skiplist() {
        //初始化头结点
        head = new Node(Integer.MIN_VALUE);
        random = new Random();
    }
    
    public boolean search(int target) {
        Node p = head;
        while (p != null) {
            if (p.val == target) {
                return true;
            } else if (p.next == null || p.next.val > target) { //p为当前层链表尾结点或者target在[p, p.next)之间时,向下查找
                p = p.down;
            } else {
                p = p.next; //其他情况说明target > p.next.val,继续向右查找
            }
        }
        return false;
    }
    
    public void add(int num) {
        boolean exists = search(num);
        Node p = head;
        if (exists) { //num在跳表中存在,不新增节点,将已有的数据节点和索引节点计数 + 1;
            while (p != null) {
                if (p.val == num) {
                    //更新索引及数据节点cnt
                    p.cnt++;
                    p = p.down;
                } else if (p.next == null || p.next.val > num) { //p为当前层链表尾结点或者target在[p, p.next)之间时,向下查找
                    p = p.down;
                } else {
                    p = p.next; //其他情况说明target > p.next.val,继续向右查找
                }
            }
            return;
        }
        //num在跳表中不存在,需新增节点
        Deque<Node> stack = new ArrayDeque<Node>();
        //查找到待插入数据节点的前置节点
        while (p != null) {
            if (p.next == null || p.next.val > num) { //p为当前层链表尾结点或者target在[p, p.next)之间时,向下查找
                stack.push(p);
                p = p.down;
            } else {
                p = p.next; //其他情况说明target > p.next.val,继续向右查找
            }
        }
        //插入数据节点,通过随机数判断索引链表是否要新增节点
        Node cur = null, down = null;
        while (!stack.isEmpty()) {
            cur = stack.pop();
            if (random.nextInt(10) < 5 || cur.down == null) {
                Node newNode = new Node(num);
                newNode.next = cur.next;
                cur.next = newNode;
                newNode.down = down;
                down = newNode;
            } else { //随机数>5,当前层索引不需新增节点,则上层所有索引均不需新增节点,直接返回
                return;
            }
        }
        //最后判断是否需要新增一级索引
        if (random.nextInt(10) < 5) {
            Node newHead = new Node(Integer.MIN_VALUE, head);
            Node newNode = new Node(num, cur.next);
            newHead.next = newNode;
            head = newHead;
        }
    }
    
    public boolean erase(int num) {
        //判断num是否存在
        if (!search(num)) {
            return false;
        }
        //找到最上层值为num的节点p,以及p的前置节点pre,若p.cnt > 1,则递归向下将cnt - 1;否则,通过pre向下递归删除所有值为num的节点
        Node p = head, pre = head;
        while (p != null) {
            if (p.val == num) {
                break;
            } else if (p.next == null || p.next.val > num) {
                p = p.down;
                pre = p;
            } else {
                pre = p;
                p = p.next;
            }
        }
        if (p.cnt > 1) { //p.cnt > 1,则递归向下修改cnt;
            while (p != null) {
                p.cnt--;
                p = p.down;
            }
        } else { //通过pre向下查找并删除所有值为num的节点
            while (pre != null) {
                while (pre.next != null && pre.next.val != num) {
                    pre = pre.next;
                }
                pre.next = pre.next.next;
                pre = pre.down;
            }
        }
        return true;
    }

    public String toString() {
        Node p = head;
        StringBuilder sb = new StringBuilder();
        while (p != null) {
            Node cur = p.next;
            List<Integer> list = new ArrayList<Integer>();
            while (cur != null) {
                list.add(cur.val);
                cur = cur.next;
            }
            sb.append(list.toString()).append("\n");
            p = p.down;
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static void main(String[] args) {
        //["Skiplist","add","add","add","search","add","search","erase","erase","search"]
        //[[],[1],[2],[3],              [0],[4],       [1],      [0],   [1],[1]]
        Skiplist sl = new Skiplist();
        sl.add(9);
        sl.add(4);
        sl.add(5);
        sl.add(6);
        sl.add(9);
        print(sl);
//        System.out.println(sl.search(0));
//        sl.add(4);
//        print(sl);
//        System.out.println(sl.search(1));
        System.out.println(sl.erase(2));
        print(sl);
        System.out.println(sl.erase(1));
        print(sl);
        sl.add(2);
        sl.add(5);
        print(sl);
        System.out.println(sl.erase(6));
//        System.out.println(sl.search(1));
//        print(sl);

    }

    private static void print(Skiplist sl) {
        System.out.println("================================");
        System.out.println(sl.toString());
        System.out.println("================================");
    }

}
/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */