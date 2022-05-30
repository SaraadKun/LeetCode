package com.saraad.leetcode.contest;

import com.alibaba.fastjson.JSON;

public class BookMyShow {
    int row;
    int col;
    int curRow;
    int[] ptrs;

    public BookMyShow(int n, int m) {
        this.row = n;
        this.col = m;
        this.curRow = 0;
        this.ptrs = new int[n];
    }

    public int[] gather(int k, int maxRow) {
        for (int i = curRow; i <= maxRow; i++) {
            if (k > col) {
                break;
            }
            //当前行可分配座位
            if (col - ptrs[i] >= k) {
                int ptr = ptrs[i];
                ptrs[i] += k;
                return new int[]{i, ptr};
            }
        }
        return new int[]{};
    }

    public boolean scatter(int k, int maxRow) {
        int cnt = 0;
        for(int i = curRow; i <= maxRow; i++) {
            int n = cnt;
            cnt += col - ptrs[i];
            //当前行可分配座位，当前行之前的座位全部已被分配
            if (cnt < 0 || cnt >= k) {
                for (int j = 0; j < i; j++) {
                    ptrs[j] = col;
                }
                curRow = i;
                ptrs[i] += k - n;
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int n = 2;
        int m = 5;
        BookMyShow bms  = new BookMyShow(n, m);
        int[] res1 = bms.gather(4, 0);// 返回 [0, 0]
// 这一组安排第 0 排 [0, 3] 的座位。
        int[] res2 = bms.gather(2, 0);// 返回 []
// 第 0 排只剩下 1 个座位。
        // 所以无法安排 2 个连续座位。
        boolean res3 = bms.scatter(5, 1);// 返回 True
// 这一组安排第 0 排第 4 个座位和第 1 排 [0, 3] 的座位。
        boolean res4 = bms.scatter(5, 1);// 返回 False
// 总共只剩下 2 个座位。
        System.out.println(JSON.toJSONString(res1));
        System.out.println(JSON.toJSONString(res2));
        System.out.println(res3);
        System.out.println(res4);
    }

}
