package com.saraad.leetcode.dailycode.may;

public class XorOperation {

    public int xorOperation(int n, int start) {
        for (int i = 1; i < n; i++) {
            start ^= start + 2;
        }
        return start;
    }

}
