package com.saraad.leetcode.dailycode2022.june;

/**
 * @Description: desc
 * @Author: saraadpeng
 * @Link: https://leetcode.cn/problems/defanging-an-ip-address/
 * @Date: 21-06-2022 16:28
 */

public class DefangingAnIPAddress {

    public String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        for (String str : address.split("\\.")){
            sb.append(str).append("[.]");
        }
        return sb.substring(0, sb.length() - 3);
    }

    public static void main(String[] args) {
        DefangingAnIPAddress obj = new DefangingAnIPAddress();
        System.out.println(obj.defangIPaddr("1.1.1.1"));
    }
}
