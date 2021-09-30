package com.saraad.leetcode.dailycode.april;

/**
 * 33. 搜索旋转排序数组
 */
public class SearchSpinArray {

    public int search(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        if (len == 1) {
            return target == nums[0] ? 0 : -1;
        }

        //二分查找  left mid right
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            //左侧有序
            if (nums[left] < nums[mid]) {
                //target 在左侧空间内,right = mid,查找左侧空间,否则,left = mid查找右侧
                if (target >= nums[left] && target < nums[mid]){
                    right = mid;
                } else {
                    left = mid;
                }
            } else {
            //右侧有序
                //target在右侧空间内,left = mid,查找右侧空间,否则, right = mid查找左侧
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
        }
        return -1;
    }

}
