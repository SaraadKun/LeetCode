package com.saraad.leetcode.dailycode;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: 739. 每日温度
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 *
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: saraad
 * @date: 2020/6/12 0:13
 * @Copyright: 2020  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class Code200611 {


    /**
     * 采用类似滑动窗口的做法,窗口左侧位置移动后,初始化窗口大小,固定窗口左侧,移动窗口右侧指针,
     * 找到符合要求的元素或移动至数组末端后计算当前窗口左侧位置元素的结果
     * 空间复杂度O(n),n为数组长度,时间复杂度O(nlogn)
     *
     * 菜鸡的自我修养:做完提交后发现20.86%,6.45%,果断看题解= =
     *
     */
    //非最优解法
    public int[] dailyTemperaturesOLD(int[] T) {
        if(T == null || T.length < 1){
            return null;
        }
        int[] arr = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            int j;
            for (j = i; j < T.length; j++) {
                if (T[j] > T[i])
                    break;
            }
            if (j >= T.length) {
                arr[i] = 0;
            }else {
                arr[i] = j-i;
            }
        }
        return arr;

    }
}
