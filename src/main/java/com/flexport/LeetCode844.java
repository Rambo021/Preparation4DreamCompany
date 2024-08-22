package com.flexport;

public class LeetCode844 {

    public static void main(String[] args) {
        LeetCode844 l = new LeetCode844();

        l.backspaceCompare("xywrrmp", "xywrrmu#p");
    }
    /**
     * 双指针
     * 计算字符串的数值，比较数值是否相等
     * 计算方式：从后向前遍历，将慢指针指向的字符加入数字
     * 快指针遇到#则慢指针向后，直到快指针指向字母
     */
    public boolean backspaceCompare(String s, String t) {
        int sc = count(s), tc = count(t);
        System.out.println("sc:" + sc + "tc:" + tc);
        return sc == tc;
    }

    public int count(String str){
        char[] chars = str.toCharArray();
        int c = 0;
        int slow = str.length() - 1, fast = slow;

        for(; slow >= 0 && fast >= 0;){
            if(chars[fast] == '#'){
                if(fast == slow){
                    slow--;
                }else{
                    chars[slow--] = '#';
                    fast -= 2;
                }

            }else{
                c += chars[slow] - 'a';
                fast--; slow--;
            }
        }

        return c;
    }
}
