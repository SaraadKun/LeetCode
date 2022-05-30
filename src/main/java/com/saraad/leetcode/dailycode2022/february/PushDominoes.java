package com.saraad.leetcode.dailycode2022.february;

public class PushDominoes {

    public static void main(String[] args) {
        PushDominoes instance = new PushDominoes();
//        String dominoes = "RR.L";
        String dominoes = ".L.R...LR..L..";
        String res = instance.pushDominoes(dominoes);
        System.out.println(res);
    }

    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        //用于记录每个位置的受力情况,正数代表→,负数←, 数值的绝对值代表受力时间(实际时间为 数值-1s)
        int[] status = new int[n];
        int t = 0;
        //第一遍从左向右遍历,受到向右的力则标记为正数+时间
        for (int i = 0; i < n; i++) {
            if (dominoes.charAt(i) == 'R') {
                t = 1;
                status[i] = t;
            } else if (dominoes.charAt(i) == 'L') {
                t = 0;
            } else if (t > 0) {
                status[i] = ++t;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (dominoes.charAt(i) == 'L') {
                t = -1;
                if (status[i] == Math.abs(t)) {
                    status[i] = 0;
                } else if (status[i] == 0 || status[i] > Math.abs(t)) {
                    status[i] = t--;
                }
            } else if (dominoes.charAt(i) == 'R') {
                t = 0;
            } else if (t < 0) {
                if (status[i] == Math.abs(t)) {
                    status[i] = 0;
                } else if (status[i] == 0 || status[i] > Math.abs(t)) {
                    status[i] = --t;
                }

            }
        }
        StringBuilder sb = new StringBuilder();
        for (int st : status) {
            if (st < 0) {
                sb.append("L");
            } else if (st > 0) {
                sb.append("R");
            } else {
                sb.append(".");
            }
        }
        return sb.toString();

    }

}
