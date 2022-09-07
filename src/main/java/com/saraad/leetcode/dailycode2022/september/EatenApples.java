package com.saraad.leetcode.dailycode2022.september;

import com.saraad.leetcode.utils.ArrayUtil;
import com.saraad.util.JSONUtil;
import org.junit.Assert;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 07-09-2022 14:23
 */

public class EatenApples {
    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->a[0]-b[0]);
        int n = apples.length, time = 0, ans = 0;
        while (time < n || !q.isEmpty()) {
            if (time < n && apples[time] > 0) q.add(new int[]{time + days[time] - 1, apples[time]});
            while (!q.isEmpty() && q.peek()[0] < time) q.poll();
            if (!q.isEmpty()) {
                int[] cur = q.poll();
                if (--cur[1] > 0 && cur[0] > time) q.add(cur);
                ans++;
            }
            time++;
        }
        return ans;
    }

    public static void main(String[] args) {
        EatenApples obj = new EatenApples();
        Assert.assertEquals(4, obj.eatenApples(ArrayUtil.mock1d("[2,1,10]"), ArrayUtil.mock1d("[2,10,1]")));
        Assert.assertEquals(7, obj.eatenApples(ArrayUtil.mock1d("[1,2,3,5,2]"), ArrayUtil.mock1d("[3,2,1,4,2]")));
    }
}
