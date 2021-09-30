package com.saraad.leetcode.dailycode.july;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TimeMap {

    private Map<String, Node> map;

    /**
     * Initialize your data structure here.
     */
    public TimeMap() {
        this.map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        Node pre = map.get(key);
        if (pre == null) {
            pre = new Node(Integer.MAX_VALUE, value);
            pre.next = new Node(timestamp, value);
            map.put(key, pre);
            return;
        }
        Node cur = pre.next;
        while (cur != null && cur.timestamp > timestamp) {
            pre = cur;
            cur = cur.next;
        }
        if (cur == null) {
            pre.next = new Node(timestamp, value);
            return;
        }
        if (cur.timestamp == timestamp) {
            cur.value = value;
        } else {
            pre.next = new Node(timestamp, value);
            pre.next.next = cur;
        }
    }

    public String get(String key, int timestamp) {
        Node pre = map.get(key);
        if (pre == null) {
            return "";
        }
        Node cur = pre.next;
        while (cur != null && cur.timestamp > timestamp) {
            cur = cur.next;
        }
        return cur == null ? "" : cur.value;
    }

    static class Node {
        String value;
        int timestamp;
        Node next;

        public Node(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */