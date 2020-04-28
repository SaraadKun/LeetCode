package com.saraad.leetcode.dailycode;

import java.util.stream.Stream;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: 山脉数组中查找目标值
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 *  给你一个 山脉数组 mountainArr，请你返回能够使得 mountainArr.get(index) 等于 target 最小 的下标 index 值。
 *
 * 如果不存在这样的下标 index，就请返回 -1。
 *
 *
 * 何为山脉数组？如果数组 A 是一个山脉数组的话，那它满足如下条件：
 *
 * 首先，A.length >= 3
 *
 * 其次，在 0 < i < A.length - 1 条件下，存在 i 使得：
 *
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 *  
 *
 * 你将 不能直接访问该山脉数组，必须通过 MountainArray 接口来获取数据：
 *
 * MountainArray.get(k) - 会返回数组中索引为k 的元素（下标从 0 开始）
 * MountainArray.length() - 会返回该数组的长度
 *  
 *
 * 注意：
 *
 * 对 MountainArray.get 发起超过 100 次调用的提交将被视为错误答案。此外，任何试图规避判题系统的解决方案都将会导致比赛资格被取消。
 *
 * 为了帮助大家更好地理解交互式问题，我们准备了一个样例 “答案”：https://leetcode-cn.com/playground/RKhe3ave，请注意这 不是一个正确答案。
 *
 *
 * @author: saraad
 * @date: 2020/4/29 0:32
 * @Copyright: 2020  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class Code200429 {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,3,1};
        MountainArrayImpl mountainArray = new MountainArrayImpl(arr);
        int index = findInMountainArray(3, mountainArray);
        System.out.println(index);

    }

    private static int findInMountainArray(int target, MountainArray mountainArr) {
        int len;
        //边界条件
        if ((len = mountainArr.length()) < 1){return -1;}
        if (target > mountainArr.get(len / 2) || (target < mountainArr.get(0) && target < mountainArr.get(len - 1))) {
            return -1;
        }
        //特殊情况 若target < mountainArr.get(0) 直接搜索右半边
        if (target < mountainArr.get(0)){
            return findInMountainArrayRight(target, mountainArr);
        }else {
            //左半边未找到,搜索右半边
            int index = findInMountainArrayLeft(target, mountainArr);
            return index == -1 ? findInMountainArrayRight(target, mountainArr) : index;
        }
    }

    static int findInMountainArrayLeft(int target, MountainArray mountainArr){
        int lo = 0;
        int hi = mountainArr.length() / 2;
        while (lo != hi) {
            int mid = lo + (hi - lo) / 2;
            if (target < mountainArr.get(mid)){
                hi = mid;
            }else if (target >mountainArr.get(mid)){
                lo = mid;
            }else {
                return mid;
            }
        }
        return target == mountainArr.get(lo) ? lo : -1;
    }

    static int findInMountainArrayRight(int target, MountainArray mountainArr){
        int lo = mountainArr.length() / 2;
        int hi = mountainArr.length() - 1;
        while (lo != hi) {
            int mid = lo + (hi - lo) / 2;
            if (target > mountainArr.get(mid)){
                hi = mid;
            }else if (target < mountainArr.get(mid)){
                lo = mid;
            }else {
                return mid;
            }
        }
        return target == mountainArr.get(lo) ? lo : -1;
    }

}


  interface MountainArray {
      public int get(int index);
      public int length();
  }
  class MountainArrayImpl implements MountainArray{
    int[] arr;
    int len;
      @Override
      public int get(int index) {
          return arr[index];
      }

      @Override
      public int length() {
          return len;
      }

      public MountainArrayImpl(int[] arr) {
          this.arr=arr;
          this.len = arr.length;
      }
  }