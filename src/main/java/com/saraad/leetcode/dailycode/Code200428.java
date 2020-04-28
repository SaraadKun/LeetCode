package com.saraad.leetcode.dailycode;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: 数组中数字出现的次数
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。

 * 示例 1：
 *
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 *
 *限制：
 *
 * 2 <= nums <= 10000
 *
 * @author: saraad
 * @date: 2020/4/28 23:41
 * @Copyright: 2020  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class Code200428 {

    public static int[] singleNumbers(int[] nums) {
        if (nums == null || nums.length < 1){return null;}
        int n1=0,n2=0;
        int k = 0;
        int mask=1;
        //取异或结果,即为两个不同数的结果
        for (int i = 0; i < nums.length; i++) {
            k ^= nums[i];
        }
//        while ((k&mask) == 0){
//            mask <<= 1;
//        }
        //取k的最低位1作为分组标志数
        mask = k&(-k);
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & mask) == 0){
                n1 ^= nums[i];
            }else {
                n2 ^= nums[i];
            }
        }
        return new int[]{n1,n2};
    }

}
