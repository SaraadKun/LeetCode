package com.saraad.leetcode.dailycode;



/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: Code200429_2
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * @author: saraad
 * @date: 2020/4/29 1:27
 * @Copyright: 2020  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class Code200429_2 {

    public static void main(String[] args) {
            int[] arr = {1,2,3,4,5,3,1};
            MountainArrayImpl mountainArray = new MountainArrayImpl(arr);
            int index = findInMountainArray(3, mountainArray);
            System.out.println(index);
    }

    public static int findInMountainArray(int target, MountainArray mountainArr) {
        int len;
        //边界条件
        if ((len = mountainArr.length()) < 1){return -1;}
        if (target > mountainArr.get(len / 2) || (target < mountainArr.get(0) && target < mountainArr.get(len - 1))) {
            return -1;
        }
        //特殊情况 若target < mountainArr.get(0) 直接搜索右半边
        if (target < mountainArr.get(0)){
            return findInMountainArrayRight(target, mountainArr, len / 2, len - 1).invoke().intValue();
        }else {
            //左半边未找到,搜索右半边
            int index = findInMountainArrayLeft(target, mountainArr, 0, len / 2).invoke().intValue();
            return index == -1 ? findInMountainArrayRight(target, mountainArr, len / 2, len - 1).invoke().intValue() : index;
        }
    }

    static TailRecursion<Integer> findInMountainArrayLeft(int target, MountainArray mountainArr, int lo, int hi){
        if (lo == hi){
            return target == mountainArr.get(lo) ? TailInvoke.done(lo) : TailInvoke.done(-1);
        }
        int mid = lo + (hi - lo) / 2;
        if (target < mountainArr.get(mid)){
            return TailInvoke.call(() -> findInMountainArrayLeft(target, mountainArr, lo, mid));
        }else if (target >mountainArr.get(mid)){
            return TailInvoke.call(() -> findInMountainArrayLeft(target, mountainArr, mid, hi));
        }else {
            return TailInvoke.done(mid);
        }
    }
    static TailRecursion<Integer> findInMountainArrayRight(int target, MountainArray mountainArr, int lo, int hi){
        if (lo == hi){
            return target == mountainArr.get(lo) ? TailInvoke.done(lo) : TailInvoke.done(-1);
        }
        int mid = lo + (hi - lo) / 2;
        if (target < mountainArr.get(mid)){
            return TailInvoke.call(() -> findInMountainArrayRight(target, mountainArr, mid, hi));
        }else if (target > mountainArr.get(mid)){
            return TailInvoke.call(() -> findInMountainArrayRight(target, mountainArr, lo, mid));
        }else {
            return TailInvoke.done(mid);
        }
    }



//interface MountainArray {
//    public int get(int index);
//    public int length();
//}
static class TailInvoke {

    public static <T> TailRecursion<T> call(final TailRecursion<T> next){
        return next;
    }

    public static <T> TailRecursion<T> done(T value){
        return new TailRecursion<T>() {
            @Override
            public TailRecursion<T> apply() {
                throw new RuntimeException("递归已结束!");
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
@FunctionalInterface
interface TailRecursion<T> {

    TailRecursion<T> apply();

    default boolean isFinished(){
        return false;
    }

    default T getResult(){
        throw new RuntimeException("递归未结束!");
    }

    default T invoke(){
        return java.util.stream.Stream.iterate(this, TailRecursion::apply)
                .filter(TailRecursion::isFinished)
                .findFirst()
                .get()
                .getResult();
    }

}
}