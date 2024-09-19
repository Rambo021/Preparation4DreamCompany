package com.microsoft;

/**
 * @author ooo
 * @description 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的
 * 回文
 *
 * 子串
 * 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 * @date 2024/9/19 09:56:36
 */
public class LongestPalindrome {
    /**
     * 从每个位置开始，向左右分别扩展子串，判断子串是否为回文串，是则计算长度并比较
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 1){
            return s;
        }

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 统一讨论s的奇偶性，分别以i为中心和(i,i+1)为中心
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            // 得到以i为中心扩展得到的回文子串的最大长度
            int length = Math.max(len1, len2);

            // 如果当前回文子串的长度，大于原来的长度，则更新start和end的位置
            if (length > end - start){
                // start 和 end 分别为从 i 向左和向右移动 length / 2个位置
                // (length - 1) / 2是为了适应偶数长度时以(i, i + 1)为中心时的start的位置
                start = i - (length - 1) / 2;
                end = i + length / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    /**
     * 从特定位置开始，向左右两边扩展子串，判断新的子串是否为回文串，即s.charAt(start) == s.charAt(end)
     * @param s
     * @param start
     * @param end
     * @return
     */
    private int expandAroundCenter(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)){
            start--; end++;
        }

        return end - start - 1;
    }
}
