package com.saraad.leetcode.dailycode2022.july;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Description: desc
 * @Author: Saraad
 * @Link: https://leetcode.cn/problems/my-calendar-i/
 * @Date: 05-07-2022 16:31
 */
class MyCalendar {

    TreeMap<Integer, Integer> map;

    public MyCalendar() {
        map = new TreeMap<>();
    }

    /*
     使用有序集合+二分查找来实现, 空间复杂度O(n), 总的时间复杂度O(mlogn) n为总的数据集大小,m为查询次数
     TreeMap存储区间,key为start,value为end
     插入时找到第一个小于start的区间[lStart, lEnd)以及第一个大于等于start的区间[rStart, rEnd)
     1.若start < lEnd || end > rStart,则返回false
     2.否则 start >= lEnd && end<=rStart 可以执行添加操作
        2.1.若start == lEnd,更新[lStart, lEnd)为[lStart, end); curKey = lStart
        2.2.否则新增区间[start, end); curKey = start
        2.3.检查若end == rStart,更新curKey的区间为[curKey, rEnd), 同时删除rStart
     若[lStart, lEnd)不存在,1.中操作不需判断start < lEnd; 跳过2.1步
     若[rStart, rEnd)不存在,1.中操作不需判断end > rStart; 跳过2.3步
     */
    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> lEntry = map.lowerEntry(start);
        Map.Entry<Integer, Integer> rEntry = map.ceilingEntry(start);
        if ((lEntry != null && lEntry.getValue() > start) || (rEntry != null && end > rEntry.getKey())) {
            return false;
        }
        int curKey = start;
        if (lEntry != null && lEntry.getValue() == start) {
            curKey = lEntry.getKey();
        }
        map.put(curKey, end);
        if (rEntry != null && end == rEntry.getKey()) {
            map.put(curKey, rEntry.getValue());
            map.remove(rEntry.getKey());
        }
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */