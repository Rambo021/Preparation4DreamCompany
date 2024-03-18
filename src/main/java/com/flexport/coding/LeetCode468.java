package com.flexport.coding;

public class LeetCode468 {
    public static void main(String[] args) {
        String queryIP = "222.2f.22.1";
        if (queryIP == null || queryIP.length() < 7){
            System.out.printf("Neither");
        }

        LeetCode468 leetCode468 = new LeetCode468();

        System.out.printf("" +
                (leetCode468.validIPv4(queryIP) ? "IPv4" :
                        leetCode468.validIPv6(queryIP) ? "IPv6" : "Neither"));
    }

    public boolean validIPv4(String ipAddr){
        String[] arr = ipAddr.split("\\.");
        if (arr == null || arr.length != 4 || ipAddr.endsWith(".") || ipAddr.endsWith(":")){
            return false;
        }

        for (String s : arr) {
            if (!validIPv4Segment(s)){
                return false;
            }
        }
        return true;
    }

    public boolean validIPv4Segment(String seg){
        if (seg.length() == 0 || seg.length() > 1 && seg.charAt(0) == '0'){
            return false;
        }

        int num = 0;
        for (char c : seg.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
            num = num * 10 + (c - '0');
        }

        return num >= 0 && num <= 255;
    }

    public boolean validIPv6(String ipAddr){
        String[] arr = ipAddr.split(":");
        if (arr == null || arr.length != 8 || ipAddr.endsWith(".") || ipAddr.endsWith(":")){
            return false;
        }

        for (String s : arr) {
            if (!validIPv6Segment(s)){
                return false;
            }
        }
        return true;
    }

    public boolean validIPv6Segment(String seg){
        if (seg.length() < 1 || seg.length() > 4){
            return false;
        }

        for (char c : seg.toCharArray()) {
            if(!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'))){
                return false;
            }
        }

        return true;
    }
}
