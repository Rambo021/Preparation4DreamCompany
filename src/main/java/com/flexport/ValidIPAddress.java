package com.flexport;

import java.util.ArrayList;
import java.util.List;

/**
 * 有效的IPv4地址从0.0.0.0到255.255.255.255，其中每段数字不能有前导0
 * 给定一个有数字组成的字符串，判断该字符串是否是有效的IPv4地址
 */
public class ValidIPAddress {

    static final int SEG_COUNT = 4;
    List<String> ans = new ArrayList<>();
    int[] segments = new int[SEG_COUNT];
    public static void main(String[] args) {
        String s = "255255255255";
        int n = s.length();

//        List<String> ans = new ArrayList<>();

//        backtrack(s, 0, n - 1, 1, "", ans);

//        ans = restoreIPInLoop(s);
        if (s.length() < 4 || s.length() > 12){
            System.out.printf("false");
        }
        ValidIPAddress validIPAddress = new ValidIPAddress();

        System.out.printf("" + validIPAddress.isValidIP(s, 0, 0));
    }

    public boolean isValidIP(String s, int segId, int segStart){
        if (segId == 3){
            return valid(s.substring(segStart));
        }

        /*if (segStart == s.length()){
            return false;
        }

        if (s.charAt(segStart) == '0'){
            return isValidIP(s, segId + 1, segStart + 1);
        }*/

        for (int segEnd = segStart; segEnd < segStart + 3 && segEnd < s.length(); segEnd++){
           if (valid(s.substring(segStart, segEnd + 1)) && isValidIP(s, segId + 1, segEnd + 1)){
               return true;
           }
        }

        return false;
    }
    /**
     * 深度搜索
     * 每次从索引segStart开始搜索第 segId(1/2/3/4) 个IP段，如果IP段满足则继续往下搜索，否则返回
     * @param s
     * @param segId
     * @param segStart
     */
    public void dfs(String s, int segId, int segStart){
        // 如果IP段的标识segId已经达到了4，说明此IP已经完成复原，则拼接成完整IP，且本次分支已完成，返回
        if (segId == SEG_COUNT){
            if (segStart == s.length()){
                ans.add(String.format("%s.%s.%s.%s", segments[0], segments[1], segments[2], segments[3]));
            }
            return;
        }

        // IP段还没有到第4个时，已经遍历完字符串了，返回
        if (segStart == s.length()){
            return;
        }

        // 如果本次分支的第一个字符是 '0'，那么此IP段只能是 0，继续往下搜索
        if(s.charAt(segStart) == '0'){
            segments[segId] = 0;
            dfs(s, segId + 1, segStart + 1);
            return;
        }

        // 特殊情况和递归出口已完成，剩下一般情况；枚举每一种可能性
        // 检查 子串 [segStart, segEnd] 是否为有效IP段
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); segEnd++) {
            // 每次循环加入一个字符，计算[segStart, segEnd]所表示的数字 addr
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            // 如果当前IP段满足 [0,255]，则搜索下一段；否则结束当前IP段的搜索
            if (addr > 0 && addr <= 0xFF){
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
            }else {
                break;
            }
        }
    }

    // 有效的IP地址：A.B.C.D，ABCD in [0,255]，其不含前导0
    public static List<String> restoreIPInLoop(String s){
        List<String> ipList = new ArrayList<>();
        int l = s.length();
        // 有效的IP地址长度为 [4,12]
        if (l > 12 || l < 4){
            return ipList;
        }

        //A 从 1位到3位
        for (int i = 0; i < 3 && (l - i - 1) >= 3; i++) {
            String A = s.substring(0, i + 1);
            if (!valid(A)){
                continue;
            }
            // B 从 1位到3位
            for (int j = i + 1; j < i + 4 && (l - j - 1) >= 2; j++) {
                String B = s.substring(i + 1, j + 1);
                if (!valid(B)){
                    continue;
                }
                // C 从 1位到3位
                for (int k = j + 1; k < j + 4 && (l - k - 1) >= 1; k++) {
                    String C = s.substring(j + 1, k + 1);
                    String D = s.substring(k + 1);
                    if (!valid(C) || !valid(D)){
                        continue;
                    }

                    ipList.add(String.format("%s.%s.%s.%s", A, B, C, D));
                }
            }
        }

        return ipList;
    }

    public static boolean valid(String s){
        System.out.println("s:" + s);
        if (s.length() < 1 || s.length() > 3 || (s.length() > 1 && s.charAt(0) == '0')){
            return false;
        }

        Integer i = Integer.valueOf(s);
        return i >= 0 && i <= 255;
    }

    public static void backtrack(String s, int i, int j, int level, String temp, List<String> retList){
        // 满足条件，则将结果加入
        if (i == (j + 1) && level == 5){
            retList.add(temp.substring(1));
        }

        for (int k = 0; k < i + 3 && k <= j; k++) {
            String ad = s.substring(i, k + 1);

            if ((s.charAt(0) == '0' && ad.length() > 1) || Integer.valueOf(ad) > 255){
                return;
            }

            backtrack(s, k + 1, j, level + 1, temp + "." + ad, retList);
        }
    }
}
