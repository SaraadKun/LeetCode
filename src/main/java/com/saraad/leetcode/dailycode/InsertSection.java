package com.saraad.leetcode.dailycode;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: InsertSection
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * 57. 插入区间
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * @author: saraad
 * @date: 2020/11/4 0:36
 * @Copyright: 2020  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class InsertSection {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals==null || intervals.length==0){
            return new int[][]{newInterval};
        }
        if (newInterval==null || newInterval.length==0){
            return intervals;
        }
        //边界条件,新区间右端小于最小值or左端大于最大值
//        if(newInterval[0]>intervals[intervals.length-1][1]){
//
//        }
        int l = newInterval[0];
        int r = newInterval[1];
        int li = -1;
        int ls = -1;
        int ri = -1;
        int rs = -1;
        for (int i = 0; i < intervals.length; i++) {
            if(l>intervals[i][1]){
               continue;
            }
            if(l<intervals[i][0]){
                li = l;
            }else {
                li = intervals[i][0];
            }
            for (int j = i; j < intervals.length; j++) {

            }
        }

        return  null;
    }

}
