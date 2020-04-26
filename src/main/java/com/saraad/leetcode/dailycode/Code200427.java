package com.saraad.leetcode.dailycode;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: 33. 搜索旋转排序数组
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: saraad
 * @date: 2020/4/27 1:20
 * @Copyright: 2020  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class Code200427 {

    public static void main(String[] args) {
        int[] arr = {4,5,6,7,0,1,2};
        int target = 7;
//        System.out.println(search(arr,7));
        System.out.println(search666(arr,7));
    }

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length < 1){return -1;}
        int start = 0;
        int end = nums.length -1;
        int mid;
        for (;start <= end;){
            mid = start + (end - start)/2;//防止超界不适用(start+end)/2
            if (nums[mid] == target){
                return mid;
            }
            //二分后的俩数组 前半部分有序
            if (nums[start] <= nums[mid]){
                if (target >= nums[start] && target < nums[mid]){
                    end = mid - 1;
                }else {
                    start = mid + 1;
                }
            }else {
                if (target > nums[mid] && target <= nums[end]){
                    start = mid + 1;
                }else {
                    end = mid - 1;
                }
            }
        }

        return -1;
    }

    public static int search666(int[] nums, int target) {
        int start = 0,end=nums.length-1,mid;
        while (start < end){
            mid = (start + end)/2;
            if (nums[mid] < nums[0] ^ nums[0] > target ^ nums[mid] < target){
                start = mid + 1;
            }else {
                end = mid;
            }
        }
        return start == end && nums[start] == target ? start : -1;
    }
    /**
     * nums[0] <= nums[mid]（0 - mid不包含旋转）且nums[0] <= target <= nums[mid] 时 high 向前规约；
     *
     * nums[mid] < nums[0]（0 - mid包含旋转），target <= nums[mid] < nums[0] 时向前规约（target 在旋转位置到 mid 之间）
     *
     * nums[mid] < nums[0]，nums[mid] < nums[0] <= target 时向前规约（target 在 0 到旋转位置之间）
     *
     * 其他情况向后规约
     *
     * 也就是说nums[mid] < nums[0]，nums[0] > target，target > nums[mid] 三项均为真或者只有一项为真时向后规约。
     *
     *
     *
     * if nums[0] <= nums[I] 那么 nums[0] 到 nums[i] 为有序数组,那么当 nums[0] <= target <= nums[i] 时我们应该在 0-i0−i 范围内查找；
     *
     * if nums[i] < nums[0] 那么在 0-i0−i 区间的某个点处发生了下降（旋转），那么 I+1I+1 到最后一个数字的区间为有序数组，并且所有的数字都是小于 nums[0] 且大于 nums[i]，当target不属于 nums[0] 到 nums[i] 时（target <= nums[i] < nums[0] or nums[i] < nums[0] <= target），我们应该在 0-i0−i 区间内查找。
     * 上述三种情况可以总结如下：
     *
     *     nums[0] <= target <= nums[i]
     *                target <= nums[i] < nums[0]
     *                          nums[i] < nums[0] <= target
     * 所以我们进行三项判断：
     *
     * (nums[0] <= target)， (target <= nums[i]) ，(nums[i] < nums[0])，现在我们想知道这三项中有哪两项为真（明显这三项不可能均为真或均为假（因为这三项可能已经包含了所有情况））
     *
     * 所以我们现在只需要区别出这三项中有两项为真还是只有一项为真。
     *
     * 使用 “异或” 操作可以轻松的得到上述结果（两项为真时异或结果为假，一项为真时异或结果为真，可以画真值表进行验证）
     *
     * 之后我们通过二分查找不断做小 target 可能位于的区间直到 low==high，此时如果 nums[low]==target 则找到了，如果不等则说明该数组里没有此项。
     *
     */

}
