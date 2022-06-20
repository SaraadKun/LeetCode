package com.saraad.leetcode.dailycode2022.june;

import java.util.Map;
import java.util.TreeMap;

class RangeModule {

    //key 为left, value为right
    TreeMap<Integer, Integer> map;

    public RangeModule() {
        map = new TreeMap<>();
    }
    
    public void addRange(int left, int right) {
        //1.插入left :
        //  找到第一个key小于等于left的节点,若为null,则插入left到最左侧;
        //  若为lastEntry,则判断value与left大小,若大于等于,则left = key;
        //  其他情况,判断value与left大小,若大于等于,则left = key
        //2.插入right:
        //  确定好left后,向右查找第一个value大于等于right的节点,
        //  若entry.key大于等于right,取节点的value作为right,同时删除当前节点,否则取right
        //  删除中间遍历到的其他节点
        Map.Entry<Integer, Integer> entry = map.higherEntry(left);
        entry = entry == null ? map.lastEntry() : map.lowerEntry(entry.getKey());
        if (entry != null) {
            if (entry.getValue() >= left) {
                left = entry.getKey();
            }
        }
        entry = entry == null ? map.firstEntry() : entry; //left在最左侧
        while(entry != null && entry.getValue() < right) {
            if (entry.getKey() > left)
                map.remove(entry.getKey());
            entry = map.higherEntry(entry.getKey());
        }
        if (entry != null && entry.getKey() <= right) {
            right = entry.getValue();
            map.remove(entry.getKey());
        }
        map.put(left, right);
    }
    
    public boolean queryRange(int left, int right) {
        //找到第一个小于等于left的节点,若节点的value大于等于right,则返回true,否则返回false
        Map.Entry<Integer, Integer> entry = map.higherEntry(left);
        entry = entry == null ? map.lastEntry() : map.lowerEntry(entry.getKey());
        if (entry != null && entry.getValue() >= right)
            return true;
        return false;
    }
    
    public void removeRange(int left, int right) {
        //1.找到第一个key小于left的entry
        //  若为null,无需操作;若entry的value<=left,则当前节点无需操作,
        //  否则,若entry.value > right, 则当前节点分裂为[key,left)和[right,value)2个节点
        //  其他情况,当前节点改为[key,left)并向右遍历进行right的操作
        //2.向右查找第一个value大于等于right的entry
        //  若不为null,且key < right,当前节点设为[right, entry.value),同时删除中间遍历到的节点
        Map.Entry<Integer, Integer> first = map.lowerEntry(left);
        if (first != null && first.getValue() > left) {
            if (first.getValue() > right) {
                map.put(right, first.getValue());
                map.put(first.getKey(), left);
                return;
            } else {
                map.put(first.getKey(), left);
            }
        }
        first = first == null ? map.firstEntry() : map.higherEntry(first.getKey());
        while (first != null && first.getValue() < right) {
            map.remove(first.getKey());
            first = map.higherEntry(first.getKey());
        }
        if (first != null && first.getKey() < right) {
            map.put(right, first.getValue());
            map.remove(first.getKey());
        }
    }

    public static void main(String[] args) {
        RangeModule rm = new RangeModule();
        rm.addRange(6, 8); //[6,8)
        rm.removeRange(7, 8);//[6,7)
        rm.removeRange(8, 9);//[6,7)
        rm.addRange(8, 9);//[6,7) [8,9)
        rm.removeRange(1, 3);//[6,7) [8,9)
        rm.addRange(1, 8);//[1,9)
        System.out.println(rm.queryRange(2,4));
        System.out.println(rm.queryRange(2,9));
        System.out.println(rm.queryRange(4,6));

        //["RangeModule","addRange","removeRange","removeRange","addRange","removeRange","addRange","queryRange","queryRange","queryRange"]
        //[[],[6,8],[7,8],[8,9],[8,9],[1,3],[1,8],[2,4],[2,9],[4,6]]
    }
}