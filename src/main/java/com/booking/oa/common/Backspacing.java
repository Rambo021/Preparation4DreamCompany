package com.booking.oa.common;

/**
 * @author ooo
 * @description 实现空格删除字符
 * i/p -> "abc#def##"
 * o/p -> "abd"
 *
 * 可以使用StringBuffer简单实现
 * 输入字符非 # 则 append；否则删除最后一个字符 deleteAt()
 * @date 2024/8/21 14:55:15
 */
public class Backspacing {

    public static void main(String[] args) {
        String str = "abc##def#";
        System.out.printf(backspacing(str));
    }

    public static String backspacing(String str){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '#'){
                if (sb.length() != 0){
                    sb.deleteCharAt(sb.length() - 1);
                }
            }else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
