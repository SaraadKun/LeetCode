package com.saraad.leetcode.dailycode2022.february;

public class SingleNonDuplicate {
    public static void main(String[] args) {
        SingleNonDuplicate instance = new SingleNonDuplicate();
        int[] nums = {3,3,7,7,10,11,11};
        int i = instance.singleNonDuplicate(nums);
        System.out.println(i);
    }
    public int singleNonDuplicate(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (hi - lo)/2 + lo;
            if (mid - 1 < 0 || mid + 1 >= nums.length ||
                    (nums[mid -1] != nums[mid] && nums[mid + 1] != nums[mid])) {
                return nums[mid];
            }
            if (nums[mid - 1] == nums[mid]) {
                if ((mid - lo & 1) == 0) {
                    hi = mid - 2;
                } else {
                    lo = mid + 1;
                }
            } else {
                if ((mid - lo & 1) == 0) {
                    lo = mid + 2;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return lo;
    }
}
