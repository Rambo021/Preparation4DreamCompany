package com.microsoft;

import java.util.Arrays;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 提示：
 *
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 */
public class LongestSubstring {

    public static void main(String[] args) {
        longestSubstring("abcaabcbb");
    }

    /**
     * 滑动窗口存储当前无重复子串：[left, right]
     * 记录每个字符当前的最大索引index
     * 滑动窗口的left = max(left, 当前字符的maxIndex]
     * @param s
     * @return
     */
    public static int longestSubstring(String s){
        // 用来记录每个字符最新的index
        int[] latest = new int[128];
        Arrays.fill(latest, -1);
        // 滑动窗口 [left, i]
        int max = 0, left = 0;

        for (int i = 0; i < s.length(); i++) {
            System.out.println(s.substring(left, i));
            int index = latest[s.charAt(i)];
            // 如果字符s.charAt(i)出现过，则将窗口左侧移动到该字符的已经记录的最大index上
            left = Math.max(left, index + 1);
            max = Math.max(max, i - left + 1);

            latest[s.charAt(i)] = i;
        }
        return max;

    }
}
