package com.saraad.leetcode.bean;

/**
 * All rights Reserved
 *
 * @version V1.0
 * @Title: TailInvoke
 * @Package:com.leetcode.recursion
 * @Description: 使用尾递归的类,目的是对外统一方法
 * @author: saraad
 * @date: 2018/12/12 16:23
 * @Copyright:  All rights reserved.
 *
 */
public class TailInvoke {

    /**
     * 统一结构的方法,获得当前递归的下一个递归
     *
     * @param next
     * @param <T>
     * @return
     */
    public static <T> TailRecursion<T> call(final TailRecursion<T> next){
        return next;
    }

    /**
     * 结束当前递归，重写对应的默认方法的值,完成状态改为true,设置最终返回结果,设置非法递归调用
     *
     * @param value 最终递归值
     * @param <T>   T
     * @return 一个isFinished状态true的尾递归, 外部通过调用接口的invoke方法及早求值, 启动递归求值。
     */
    public static <T> TailRecursion<T> done(T value){
        return new TailRecursion<T>() {
            @Override
            public TailRecursion<T> apply() {
                throw new RuntimeException("递归调用已结束!");
            }

            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public T getResult() {
                return value;
            }
        };
    }


}
