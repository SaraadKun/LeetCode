package com.saraad.leetcode.dailycode2022.february;

import java.util.Stack;

public class CQueue {

    private Stack<Integer> s1;
    private Stack<Integer> s2;

    public CQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void appendTail(int value) {
        s1.push(value);
    }

    public int deleteHead() {
        if (!s2.isEmpty()) {
            return s2.pop();
        }
        int res = -1;
        while (!s1.isEmpty()) {
            int tmp = s1.pop();
            if (s1.isEmpty()) {
                res = tmp;
                break;
            }
            s2.push(tmp);
        }
        return res;
    }

    public static void main(String[] args) {
        CQueue queue = new CQueue();
        int i = queue.deleteHead();
        assert i == -1;
    }
}
