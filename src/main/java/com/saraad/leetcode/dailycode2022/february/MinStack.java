package com.saraad.leetcode.dailycode2022.february;

import java.util.PriorityQueue;
import java.util.Stack;

public class MinStack {

    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> min = new Stack<>();
    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        s1.push(x);
        if (min.isEmpty() || min.peek() > x) {
            min.push(x);
        }
    }

    public void pop() {
        Integer pop = s1.pop();
        if (pop.equals(min.peek())){
            min.pop();
        }
    }

    public int top() {
        return s1.peek();
    }

    public int min() {
        return min.peek();
    }
}
