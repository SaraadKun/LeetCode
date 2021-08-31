package com.saraad.leetcode.group01;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PromotionDM {

    private Node head = new Node();

    {
        Node start = new Node(0L, 100, 100);
        Node end = new Node(253402271999000L, 100, 100);//9999-12-31 23:59:59
        head.setNext(start);
        start.setNext(end);
    }

    public static void main(String[] args) {
        PromotionDM instance = new PromotionDM();
        List<PromotionTuple> dataList = mockData();
        dataList.forEach(instance::mergePromotion);
        List<PromotionTuple> list = instance.toArray();
        list.stream().map(PromotionTuple::toString).forEach(System.out::println);
    }

    private static List<PromotionTuple> mockData() {
        List<PromotionTuple> res = new ArrayList<>();
        res.add(new PromotionTuple(1630425600000L, 1633017599000L, 5));  // 2021-09-01 00:00:00   2021-09-30 23:59:59
        res.add(new PromotionTuple(1631203200000L, 1632153599000L, 6));  // 2021-09-10 00:00:00   2021-09-20 23:59:59
        res.add(new PromotionTuple(1631635200000L, 1632585599000L, 3));  // 2021-09-15 00:00:00   2021-09-25 23:59:59
        return res;
    }

    public void mergePromotion(PromotionTuple promotion) {
        mergePromotion(promotion.t1, promotion.t2, promotion.price);
    }

    public void mergePromotion(long tl, long tr, double val) {
        Node pre = head;
        //设置当前促销起始时间节点
        while (pre.next != null) {
            Node curNode = pre.next;
            if (curNode.timestamp == tl) {
                curNode.priceStart = Math.min(curNode.priceStart, val);
                pre = curNode;
                break;
            }
            if (curNode.timestamp > tl) {
                double curPrice = Math.min(pre.priceStart, val);
                Node node = new Node(tl, curPrice, pre.priceStart);
                pre.setNext(node);
                node.setNext(curNode);
                pre = node;
                break;
            }
            pre = curNode;
        }
        //更新促销有效时间内的节点价格
        while (pre.next != null && pre.next.timestamp < tr) {
            Node curNode = pre.next;
            curNode.priceStart = Math.min(curNode.priceStart, val);
            curNode.priceEnd = Math.min(curNode.priceEnd, val);
            pre = curNode;
        }
        //设置当前促销结束时间节点
        Node cur = pre.next;
        // cur 节点的时间戳必然大于等于当前促销结束时间戳
        if (cur.timestamp == tr) {
            cur.priceEnd = Math.min(cur.priceEnd, val);
        } else {
            double curPrice = Math.min(cur.priceEnd, val);
            Node node = new Node(tr, cur.priceEnd,curPrice);
            pre.setNext(node);
            node.setNext(cur);
        }
    }

    public List<PromotionTuple> toArray() {
        List<PromotionTuple> list = new ArrayList<>();
        Node pre = this.head;
        while (pre.next != null) {
            Node cur = pre.next;
            Long t2 = cur.next == null ? null : cur.next.timestamp;
            list.add(new PromotionTuple(cur.timestamp, t2, cur.priceStart));
            pre = cur;
        }
        return list;
    }

    static class Node {

        long timestamp;
        double priceStart;
        double priceEnd;
        Node pre;
        Node next;

        public void setNext(Node next) {
            this.next = next;
            next.pre = this;
        }

        public Node(long timestamp, double priceStart) {
            this.timestamp = timestamp;
            this.priceStart = priceStart;
        }
        public Node(long timestamp, double priceStart, double priceEnd) {
            this.timestamp = timestamp;
            this.priceStart = priceStart;
            this.priceEnd = priceEnd;
        }

        public Node() {
        }

    }

}

class PromotionTuple {

    Long t1;
    Long t2;
    double price;

    public PromotionTuple(Long t1, Long t2, double price) {
        this.t1 = t1;
        this.t2 = t2;
        this.price = price;
    }

    public PromotionTuple() {
    }

    public String toString() {
        String format = "{%s, %s, %f}";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = sdf.format(new Date(t1));
        String date2 = t2 == null ? null : sdf.format(new Date(t2));
        return String.format(format, date1, date2, price);
    }
}
