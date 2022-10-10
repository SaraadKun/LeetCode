package com.saraad.leetcode.contest;

import com.saraad.util.JSONUtil;
import org.junit.Assert;

import java.util.*;

class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        Pair[] p = new Pair[n];
        for (int i = 0; i < names.length; i++) {
            p[i] = new Pair(names[i], heights[i]);
        }
        List<String> list = Arrays.stream(p).sorted((p1, p2) -> p2.getH() - p1.getH())
                .map(Pair::getName).toList();
        String[] ans = new String[n];
        for (int i = 0; i < names.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
    
    static class Pair {
        private String name;
        private Integer h;
        public String getName(){
            return name;
        }
        public Integer getH() {
            return h;
        }
        public Pair(String _name, Integer _h) {
            name = _name;
            h = _h;
        }
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
//        System.out.println(obj.canTransform("RXXLRXRXL", "XRLXXRRLX"));
//        System.out.println(obj.canTransform("LXXLXRLXXL", "XLLXRXLXLX"));
        Assert.assertEquals("fnohopzv", obj.robotWithString("vzhofnpo"));
        Assert.assertEquals("fnoppohzv", obj.robotWithString("vzhopfnpo"));
        Assert.assertEquals("abc", obj.robotWithString("bac"));


    }

    public String robotWithString(String s) {
        int n = s.length(), idx = 0;
        char[] chs = s.toCharArray(), ans = new char[n];
        int[] min = new int[n];
        for (int i = n - 1; i > 0; i--) {
            min[i - 1] = Math.min(chs[i], i == n - 1 ? 'z' : min[i]);
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (stack.push(chs[i]); !stack.isEmpty() && stack.peek() <= min[i]; ) {
                ans[idx++] = stack.pop();
            }
        }

        while (!stack.isEmpty()) {
            ans[idx++] = stack.pop();
        }
        return new String(ans);
    }


    public boolean canTransform(String start, String end) {
        //L 与 R相对位置不变,L只能右移,R只能左移
        int n = start.length(), s = 0, e = 0;
        while (s < n || e < n) {
            while (s < n && start.charAt(s) == 'X') {
                s++;
            }
            while (e < n && end.charAt(e) == 'X') {
                e++;
            }
            if (s < n && e < n && start.charAt(s) == end.charAt(e)) {
                s++;
                e++;
            } else if (s == n && e == n) {
                break;
            } else {
                return false;
            }
        }
        return true;
    }
}