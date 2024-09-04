package com.microsoft;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ooo
 * @description 括号生成
 * https://leetcode.cn/problems/generate-parentheses/description/
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 *
 * @date 2024/9/4 09:51:01
 */
public class GenerateParenthesis {
    List<String> ans = new ArrayList<>();

    public static void main(String[] args) {
        GenerateParenthesis g = new GenerateParenthesis();
        System.out.println(g.generateParenthesis(8));
    }
    public List<String> generateParenthesis(int n){
        backtracking(n,n,"");
        return ans;
    }

    public void backtracking(int left, int right, String cur){
        // 递归出口，收集结果并退出
        if (left == 0 && right == 0){
            ans.add(cur);
            return;
        }

        // 左括号还剩下，那就加左括号
        if (left > 0){
            backtracking(left - 1, right, cur + "(");
        }
        // 当右括号比左括号多的情况下，可以合法地添加右括号
        // 该块代码可以分别在 左括号数量为 [1, n - 1] 的情况下被执行
        if (right > left){
            backtracking(left, right - 1, cur + ")");
        }
    }
}
