package com.saraad.leetcode.dailycode2022.may;

public class ValidIPAddress {

    public String validIPAddress(String queryIP) {
        if (queryIP.contains(".")) {
            return validIPv4(queryIP);
        } else if (queryIP.contains(":")) {
            return validIPv6(queryIP);
        }
        return "Neither";
    }

    private String validIPv4(String queryIP) {
        String[] nums = queryIP.split("\\.");
        if (nums.length != 4 || queryIP.endsWith(".")) {
            return "Neither";
        }
        for (String num : nums) {
            if (num.length() < 1 || num.length() > 3) {
                return "Neither";
            }
            if (num.charAt(0) == '0' && num.length() > 1) {
                return "Neither";
            }
            for (int i = 0; i < num.length(); i++) {
                if (!Character.isDigit(num.charAt(i))) {
                    return "Neither";
                }
            }
            int n = Integer.parseInt(num);
            if (n < 0 || n > 255) {
                return "Neither";
            }
        }
        return "IPv4";
    }

    private String validIPv6(String queryIP) {
        String[] nums = queryIP.split(":");
        if (nums.length != 8) {
            return "Neither";
        }
        for (String num : nums) {
            if (num.length() < 1 || num.length() > 4) {
                return "Neither";
            }
            for(int i = 0; i < num.length(); i++) {
                if (Character.isDigit(num.charAt(i))) {
                    continue;
                }
                if ((num.charAt(i) >= 'a' && num.charAt(i) <= 'f') || (num.charAt(i) >= 'A' && num.charAt(i) <= 'F')) {
                    continue;
                } else {
                    return "Neither";
                }
            }
        }
        return "IPv6";
    }

    public static void main(String[] args) {
        ValidIPAddress validIPAddress = new ValidIPAddress();
        System.out.println(validIPAddress.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:"));
    }

}
