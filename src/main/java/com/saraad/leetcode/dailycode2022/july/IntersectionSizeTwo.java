package com.saraad.leetcode.dailycode2022.july;

import java.util.Arrays;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: url
 * @Date: 22-07-2022 18:50
 */

public class IntersectionSizeTwo {
    public int intersectionSizeTwo(int[][] intervals) {
        //对intervals元素进行排序,对每个[a, b]按b升序 + a降序排序,维护一个集合S作为结果数组
        //遍历排序后的intervals,对于每一组[a, b],集合S存在下述集中情况
        //  0.集合为空,取b, b - 1加入到S中,这样集合与当前的[a, b]有2个点相交(+2)
        //  1.集合最大值max >= a && submax >= a(max <= b),此时满足S交[a,b]等于2,无需操作(+0)
        //  2.max >= a && submax < a,此时有1个交点,添加b作为max,submax = max,max = b;(+1)
        //  3.max < a: 此时S与[a,b]无交点(同0),max = b, submax = b - 1;(+2)
        //遍历完成后统计S的长度,更进一步,可以进行状态压缩,取集合max, submax以及当前长度ans替代集合.
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] == b[1]) {
                return b[0] - a[0];
            }
            return a[1] - b[1];
        });
        int ans = 0, max = -1, submax = -1;
        for (int[] arr: intervals) {
            if (ans == 0 || max < arr[0]) {
                max = arr[1];
                submax = max - 1;
                ans += 2;
            } else if (max >= arr[0]) {
                if (submax < arr[0]) {
                    submax = max;
                    max = arr[1];
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        IntersectionSizeTwo intersectionSizeTwo = new IntersectionSizeTwo();
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println(intersectionSizeTwo.intersectionSizeTwo(intervals));
    }
}
