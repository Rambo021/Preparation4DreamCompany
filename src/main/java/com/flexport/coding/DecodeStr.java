package com.flexport.coding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ooo
 * @description LC93
 * @date 2024/3/13 14:58:27
 *
 *
 */
public class DecodeStr {
    List<String> ans = new ArrayList<>();
    Map<String, String> map = new HashMap<String, String>(){
        {
            put("1", "A");put("2", "B");put("3", "C");put("4", "D");put("5", "E");put("6", "F");put("7", "G");
            put("8", "H");put("9", "I");put("10", "J");put("11", "K");put("12", "L");put("13", "M");put("14", "N");
            put("15", "O");put("16", "P");put("17", "Q");put("18", "R");put("19", "S");put("20", "T");put("21", "U");
            put("22", "V");put("23", "W");put("24", "X");put("25", "Y");put("26", "Z");
        }
    };
    public static void main(String[] args) {
        String s = "111111111111111111111111111111111111111111111";
        String a = "103";
        DecodeStr decoder = new DecodeStr();
        /*if (s == null || s.length() == 0 || s.charAt(0) == '0'){
            System.out.printf("ans:" + 0);
            return;
        }
        decoder.dfs(s, 0, "");
        System.out.printf("ans:" + decoder.ans);*/

        System.out.printf("ans:" + decoder.decode(a));
    }

    public List<String> decode(String s){
        List<String> ans = new ArrayList<>();
        int l = s.length();
        int[] dp = new int[l + 1];
        dp[0] = 1;

        for (int i = 1; i <= l; i++) {
            // 第一种情况：仅以第i个字符组成子串
            // 如果第i个字符(在Java中下标为i-1)不为0则可以解码，故dp[i] = dp[i - 1]；否则dp[i] = 0;
            if (s.charAt(i - 1) != '0'){
                dp[i] = dp[i - 1];
                ans = contact(map.get(s.substring(i - 1, i)), ans);
            }
            // 第二种情况：以第 i - 1个字符和第i个字符组成子串
            // 如果满足条件则此时dp[i] = dp[i - 2]，再加上前面第一种情况的个数
            if (i > 1 && s.charAt(i - 2) != '0' && Integer.valueOf(s.substring(i - 2, i)) <= 26){
                dp[i] += dp[i - 2];
                ans = contact(map.get(s.substring(i - 2, i)), ans);
            }
        }
        return dp[l] > 0 ? ans : new ArrayList<>();
    }

    public List<String> contact(String str, List<String> oldList){
        if (oldList.size() == 0){
            oldList.add(str);
            return oldList;
        }
        return oldList.stream().map(p -> p + str).collect(Collectors.toList());
    }

    /**
     * 动态规划
     * dp[i] 表示 从第 1 个字符到第 i 个字符能够组成的结果的个数
     * dp[i] += dp[i - 1] (子串为 s[i], 且s[i] != '0')；dp[i - 2] (子串为s[i-1, i]，其中s[i-1] != '0')
     * dp[0] = 1，即空串
     * @param s
     * @return
     */
    public int dp(String s){
        int l = s.length();
        int[] dp = new int[l + 1];
        dp[0] = 1;

        for (int i = 1; i <= l; i++) {
            // 第一种情况：仅以第i个字符组成子串
            // 如果第i个字符(在Java中下标为i-1)不为0则可以解码，故dp[i] = dp[i - 1]；否则dp[i] = 0;
            if (s.charAt(i - 1) != '0'){
                dp[i] = dp[i - 1];
            }
            // 第二种情况：以第 i - 1个字符和第i个字符组成子串
            // 如果满足条件则此时dp[i] = dp[i - 2]，再加上前面第一种情况的个数
            if (i > 1 && s.charAt(i - 2) != '0' && Integer.valueOf(s.substring(i - 2, i)) <= 26){
                dp[i] += dp[i - 2];
            }
        }
        return dp[l];
    }

    /**
     * 回溯超时，需剪枝
     * @param s
     * @param start
     * @param tmp
     */
    public void dfs(String s, int start, String tmp){
        // 已经遍历结束，则将本分支加入结果集
        if (start == s.length()){
            ans.add(tmp);
            return;
        }
        if (s.charAt(start) == '0'){
            return;
        }

        // 从start开始组合字符，得到字符串，其中字符串长度<=2
        for (int i = start; i < start + 2 && i < s.length(); i++) {
            String numStr = s.substring(start, i + 1);
            Integer num = Integer.valueOf(numStr);
            // 判断numStr是否为满足的数字
            if(num > 26){
                break;
            }else {
                dfs(s, i + 1, tmp + map.get(numStr));
            }
        }


    }
}
