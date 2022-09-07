package com.saraad.leetcode.dailycode2022.september;

import com.saraad.leetcode.utils.ArrayUtil;
import org.junit.Assert;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 05-09-2022 12:32
 */

public class MostBooked {

    public int mostBooked(int n, int[][] meetings) {
        int[] cnts = new int[n];
        PriorityQueue<int[]> mq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        PriorityQueue<long[]> rq = new PriorityQueue<>((o1, o2) -> (int)(o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0])),
                cq = new PriorityQueue<>(Comparator.comparingLong(o -> o[1]));
        for (int[] meeting : meetings) {
            mq.offer(meeting);
        }
        for (int i = 0; i < n; i++) {
            rq.offer(new long[]{0, i});
        }
        while (!mq.isEmpty()) {
            int[] m = mq.poll();
            while (!rq.isEmpty() && m[0] >= rq.peek()[0]) {
                cq.offer(rq.poll());
            }
            long[] r = !cq.isEmpty() ? cq.poll() : rq.poll();
            cnts[(int)r[1]]++;
            r[0] = r[0] >= m[0] ? r[0] + m[1] - m[0] : m[1];
            rq.offer(r);
        }
        int max = -1, ans = -1;
        for (int i = 0; i < n; i++) {
            if (cnts[i] > max) {
                max = cnts[i];
                ans = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        MostBooked obj = new MostBooked();
        Assert.assertEquals(1, obj.mostBooked(3, ArrayUtil.mock2d("[[1,20],[2,10],[3,5],[4,9],[6,8]]")));
        Assert.assertEquals(0, obj.mostBooked(4, ArrayUtil.mock2d("[[18,19],[3,12],[17,19],[2,13],[7,10]]")));
    }

}
