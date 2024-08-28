package com.microsoft;

import java.util.Stack;

/**
 * 最长有效括号
 * https://leetcode.cn/problems/longest-valid-parentheses/
 */
public class LongestValidParentheses {

    /**
     * Firstly，we use stack to cover parentheses to 0(valid) and 1(invalid)
     * Secondly, we go through the array to find the longest continuous sub-array with 0
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s){
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        // we set all the pos are valid(0)
        int[] ary = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            // we push the char '(' into stack with it's index
            if (s.charAt(i) == '('){
                stack.push(i);
            }else {// for ')', we need to check whether a matched '('
                // if not, the pos will be set to 1
                if (stack.isEmpty()){
                    ary[i] = 1;
                }else {// if so, pop the matched '('
                    stack.pop();
                }
            }
        }
        // process the left char in stack
        while (!stack.isEmpty()){
            ary[stack.pop()] = 1;
        }
        // for now, we have got the ary represents the valid or invalid by 0 and 1
        // go through the ary to find the longest continuous sub-array with 0
        int len = 0;
        for (int i = 0; i < ary.length; i++) {
            // if we meet 1, reset len into 0; otherwise, +1;
            len = ary[i] == 1 ? 0 : len + 1;
            max = Math.max(max, len);
        }
        return max;
    }
}
