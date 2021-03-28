package com.saraad.leetcode.dailycode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: RandomizedCollection
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * 381. O(1) 时间插入、删除和获取随机元素 - 允许重复
 * 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
 *
 * 注意: 允许出现重复元素。
 *
 * insert(val)：向集合中插入元素 val。
 * remove(val)：当 val 存在时，从集合中移除一个 val。
 * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 * @author: saraad
 * @date: 2020/10/31 23:10
 * @Copyright: 2020  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class RandomizedCollection {

    /** 使用数组存放具体元素,涉及到数组扩容,懒得写了,直接套用ArrayList */
    transient Object[] elem;

    /** 索引数组长度 */
    private volatile int hashSize;

    /** 索引数组扩容因子 */
    private final float DEFAULT_LOAD_FACTOR = 0.75f;

    /** 使用数组存放每个值,初始长度16,元素到达0.75扩容,每次扩容两倍 */
    private ArrayList<Integer>[] hashIndex;

    /** 存放总的元素个数 */
    private volatile int len = 0;

    /** elem数组当前使用到的长度 */
    private volatile int eindex = 0;


    /** Initialize your data structure here. */
    public RandomizedCollection() {
        elem = new Object[10];
        hashSize = 16;
        hashIndex = new ArrayList[hashSize];
    }

    /**
     *  Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     *  插入时
     *  1.校验插入后的len是否需要对elem数组扩容,需要先执行elem扩容逻辑
     *  2.校验插入后的len是否需要对hashIndex数组扩容,需要则先执行hashIndex扩容逻辑
     *  3.元素添加至elem末尾
     *  4.计算元素hash,根据hash%hashIndex计算索引存储位置,将len添加至对应位置数组中(hash冲突的数据在同一位置的数组中)
     *  5.更新len
     *
     */
    public boolean insert(int val) {
        boolean notex = false;
        //校验长度是否需要扩容elem数组
        if((eindex+1)>=elem.length){
            resizeElem();
        }
        //校验长度是否需要扩容索引数组
        if((len+1)>(hashSize*DEFAULT_LOAD_FACTOR)){
            resizeIndex();
        }
        elem[eindex] = val;
        //计算索引存放位置
        int hash = hash(val);
        int hi = hash%hashSize;
        ArrayList<Integer> his = hashIndex[hi];
        if (his == null || his.size() == 0){
            his = new ArrayList<>();
            notex = true;
        } else {
            notex = true;
            for (Integer e : his) {
                if (elem[e]!=null && val==(int)elem[e]) {
                    notex = false;
                    break;
                }
            }
        }
        his.add(eindex);
        hashIndex[hi] = his;
        eindex++;
        len++;
        return notex;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     * 根据val计算hash,获取元素索引列表,移除最后一个元素,更新len
     *
     * */
    public boolean remove(int val) {
        int hash = hash(val);
        int hi = hash%hashSize;
        ArrayList<Integer> his = hashIndex[hi];
        //元素不存在
        if (his == null || his.size() == 0){
           return false;
        }
        int rmi = -1;
        boolean exists = false;

        for (int i = 0; i < his.size(); i++) {
            rmi = his.get(i);
            if (elem[rmi]!=null && val == (int)elem[rmi]){
                exists = true;
                elem[rmi] = null;
                Object E = his.remove(i);
                len--;
                break;
            }
        }
        return exists;
    }

    /**
     * Get a random element from the collection.
     * 根据len,random一个索引返回
     * */
    public int getRandom() {
        if (len < 1){
            return -1;
        }
        int res = -1;
        boolean flag = true;
        do {
            int index = (int)(Math.random()*eindex);
            Object E = elem[index];
            if (E!=null){
                res = (int)E;
                flag = false;
            }
        }while (flag);
        return res;
    }

    /**
     *
     * 索引扩容逻辑
     *  1.创建新的索引数组,len为原来的一倍
     *  2.遍历elem,按照hashsize*2重新计算hashIndex,将计算结果存入新索引数组中
     *  3.更新hashIndex
     *  4.更新hashSize
     *
     */
    private void resizeIndex() {
        int newSize = hashSize<<1;
        if (newSize > Integer.MAX_VALUE - 8){
            newSize = Integer.MAX_VALUE - 8;
        }
        if (newSize < 0){
            throw new OutOfMemoryError();
        }
        ArrayList<Integer>[] newArr =  new ArrayList[newSize];
        for (int i = 0; i < eindex; i++) {
            if (elem[i] != null) {
                int hi = hash((int) elem[i]) % newSize;
                ArrayList<Integer> his = newArr[hi];
                if (his == null) {
                    his = new ArrayList<>();
                }
                his.add(hi);
                newArr[hi] = his;
            }
        }
        hashIndex = newArr;
        hashSize = newSize;
    }

    /**
     *
     * 数组扩容逻辑
     *  1.创建新的索引数组,len为原来的一倍
     *  2.将elem数组元素复制到新数组中
     *  3.更新elem
     *
     */
    private void resizeElem() {
        int newSize = elem.length<<1;
        if (newSize > Integer.MAX_VALUE - 8){
            newSize = Integer.MAX_VALUE - 8;
        }
        if (newSize < 0){
            throw new OutOfMemoryError();
        }
        Object[] newElem = new Object[newSize];
        for (int i = 0; i < elem.length; i++) {
            newElem[i]=elem[i];
        }
        elem = newElem;
    }

    /**
     * hash 方法,因为是int类型,偷懒直接返回输入值
     */
    private int hash(int val){
        return Math.abs(val);
    }

    public static void main(String[] args) {
//["RandomizedCollection","insert","insert","getRandom","insert","insert","remove","remove","remove",
// "insert","remove","getRandom","insert","remove","remove","insert","insert","insert","insert","insert","insert"]
//[[],[2],[0],[],[-2],[-2],[3],[3],[1],
// [-3],[3],[],[-3],[-1],[3],[1],[1],[1],[-2],[2],[-3]]
        RandomizedCollection coll = new RandomizedCollection();
        for (int i = 0; i < 9; i++) {
            System.out.println(coll.insert(i));
        }
        System.out.println(coll.remove(10));
        System.out.println(coll.insert(10));
        System.out.println(coll.insert(10));
        System.out.println(coll.insert(2));
        System.out.println(coll.remove(1));
//        System.out.println(coll.getRandom());
        System.out.println(coll.getRandom());
        System.out.println("end");

        //[null,true,true,true,true,true,2]

    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */