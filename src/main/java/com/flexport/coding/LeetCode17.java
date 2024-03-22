package com.flexport.coding;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 九键数字的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能够表示的字母组合。答案可以按 任意顺序 返回。
 * 给出的数字到字母的映射如电话九键。
 *
 * 第一步先考虑数字到字母的映射的存储方式
 * 第二步使用回溯的方式找出所有的字母组合
 */
public class LeetCode17 {

    String[] letters;

    public LeetCode17() {
        this.letters = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    }

    public static void main(String[] args) {
        String digit = "";

        LeetCode17 leetCode17 = new LeetCode17();
        System.out.printf(leetCode17.digit2Letters(digit).toString());
    }

    /**
     *   递归 + 循环
     *          a                b              c
     *     d    e    f       d   e   f     d   e   f
     *    jkl jkl  jkl     jkl  jkl jkl   jkl jkl jkl
     */
    public List<String> digit2Letters(String digit){
        List<String> ans = new ArrayList<>();
        if (digit == null || digit.length() == 0){
            return ans;
        }
        backtrack(digit, 0, null, ans);
        return ans;
    }

    /**
     *
     * @param digit
     * @return
     */
    public void backtrack(String digit, int index, String word, List<String> ans){
        // 递归出口：当递归深度 == 数字的长度时，本分支已结束
        if (index == digit.length()){
            ans.add(word);
            return;
        }

        // 正常逻辑：取 index 所在的数字 对应 的字母，遍历
        String letter = this.letters[digit.charAt(index) - '0'];
        char[] letterArr = letter.toCharArray();
        for (char c : letterArr) {
            StringBuilder sb = new StringBuilder(word);
            // 隐含了回溯操作：index + 1 / word本身未发生变化
            backtrack(digit, index + 1, sb.append(c).toString(), ans);
        }
    }
}
