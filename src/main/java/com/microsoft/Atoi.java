package com.microsoft;

import java.util.Arrays;
import java.util.List;

/**
 * @author ooo
 * @description 字符串转整数
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数。
 *
 * 函数 myAtoi(string s) 的算法如下：
 *
 * 空格：读入字符串并丢弃无用的前导空格（" "）
 * 符号：检查下一个字符（假设还未到字符末尾）为 '-' 还是 '+'。如果两者都不存在，则假定结果为正。
 * 转换：通过跳过前置零来读取该整数，直到遇到非数字字符或到达字符串的结尾。如果没有读取数字，则结果为0。
 * 舍入：如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被舍入为 −231 ，大于 231 − 1 的整数应该被舍入为 231 − 1 。
 * 返回整数作为最终结果。
 *
 * https://leetcode.cn/problems/string-to-integer-atoi/description/
 * @date 2024/9/14 14:42:28
 */
public class Atoi {

    public static void main(String[] args) {
        Atoi a = new Atoi();
        String s = "+-12";
        System.out.println("s=" + a.myAtoi(s));
    }
    /**
     * 此题考察基础的编程语法，和对边界条件的处理
     * 遵循如下步骤：
     *  1. 先去除前导空格；
     *  2. 判断第一个字符是否是数字或+-，如果不是则返回0；
     *  3. 判断第一个字符是否是符号；
     *      3.1. 如果是符号，则设置符号标识，走4；
     *      3.2. 如果是前导0，则移动到第一个非0的位置；并判断该字符是否为数字字符；如果是则走4，否则返回0；
     *  4. 已经清理了边界条件，从此位置往后遍历字符串，直到非数字字符或字符串结尾，在此过程中将字符加入 StringBuilder；
     *  5. 将StringBuilder的结果转为double，和Integer的最大最小值比较，得到结果
     * @param s
     * @return
     */
    public int myAtoi(String s){
        if (s == null || s.length() < 1){
            return 0;
        }
        List<Character> validCharList = Arrays.asList('0','1','2','3','4','5','6','7','8','9','+','-');
        List<Character> numCharList = Arrays.asList('0','1','2','3','4','5','6','7','8','9');
        // 1. 先去除前导空格；
        int startI = clearPrevSpace(s);
        System.out.println("起始位置：" + startI + "，字符串：" + s.substring(startI));
        // 2. 判断第一个字符是否是数字或+-，如果不是则返回0；
        if (!validCharList.contains(s.charAt(startI))){
            return 0;
        }
        // 3. 判断第一个字符是否是符号；
        int sign = 1;
        if (s.charAt(startI) == '-'){
            sign = -1;
            startI++;
        }else if (s.charAt(startI) == '+'){
            startI++;
        }else if (s.charAt(startI) == '0'){// 3.2. 如果是前导0，则移动到第一个非0的位置；并判断该字符是否为数字字符；如果是则走4，否则返回0；
            startI = clearPrevZero(s, startI);
            if (!numCharList.contains(s.charAt(startI))){
                return 0;
            }
            System.out.println("起始位置：" + startI + "，字符串：" + s.substring(startI));
        }

        // 4. 已经清理了边界条件，从此位置往后遍历字符串，直到非数字字符或字符串结尾，在此过程中将字符加入 StringBuilder；
        StringBuilder sb = new StringBuilder();
        while (startI < s.length() && numCharList.contains(s.charAt(startI))){
            sb.append(s.charAt(startI++));
        }
        if (sb.length() == 0){
            return 0;
        }
        // 5. 将StringBuilder的结果转为double，和Integer的最大最小值比较，得到结果
        Double d = sign * Double.valueOf(sb.toString());
        if (d.compareTo(Double.valueOf(Integer.MAX_VALUE)) > 0){
            return Integer.MAX_VALUE;
        }
        if (d.compareTo(Double.valueOf(Integer.MIN_VALUE)) < 0){
            return Integer.MIN_VALUE;
        }

        return d.intValue();
    }

    private int clearPrevZero(String s, int startI) {
        char first = s.charAt(startI);
        while (startI < s.length() - 1 && first == '0'){
            first = s.charAt(++startI);
        }

        return startI;
    }

    private int clearPrevSpace(String s){
        int index = 0;
        char first = s.charAt(index);
        while (index < s.length() - 1 && first == ' '){
            first = s.charAt(++index);
        }

        return index;
    }
}
