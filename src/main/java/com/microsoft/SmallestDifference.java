package com.microsoft;

import java.util.Arrays;

/**
 * 给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
 *
 * 示例：
 *
 * 输入：{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
 * 输出：3，即数值对(11, 8)
 * 提示：
 *
 * 1 <= a.length, b.length <= 100000
 * -2147483648 <= a[i], b[i] <= 2147483647
 * 正确结果在区间 [0, 2147483647] 内
 * Related Topics
 * 数组
 * 双指针
 * 二分查找
 * 排序
 */
public class SmallestDifference {

    public static void main(String[] args) {
        int[] a = {1,5,6,7,8,9,1034,56074,64397,1565,37663,8737,48,56,188,701,9278,64,66,68,838,2638,336,4818,85,4439,90,223,7788};
        int[] b = {62577,4977,5363,122};
//        int[] b = {23, 127, 235, 19, 8};

        smallestDifference(a,b);
//        Arrays.sort(b);
//        System.out.printf("" + binarySearch(1111, b));
    }

    /**
     * [1,2,3,11,15]
     * [8,19,23]
     * 排序后，双指针计算两个数的差
     * 此题的问题在于，注意int会超精度，所以使用long保存两数之差
     * 如果两数之差等于0则返回0；如果小于0，说明p1的数小于p2的数，移动p1；反之移动p2
     * @param a
     * @param b
     * @return
     */
    public static long smallestDifference2(int[] a, int[] b){
        long minDif = Long.MAX_VALUE;
        Arrays.sort(a);
        Arrays.sort(b);

        int p1 = 0, p2 = 0;
        while(p1 < a.length && p2 < b.length){
            if(a[p1] == b[p2]){
                return 0;
            }
            // calculate the diff with a[p1] and b[p2]
            long dif = a[p1] - b[p2];
            minDif = Math.min(Math.abs(dif), minDif);

            // let's see which is bigger between a[p1] and b[p2], it decides the movement of the two pointers
            if(dif <= 0){// a[p1] <= b[p2], so the dif is the min in a[p1] with all the else in b, we move p1
                p1++;
            }else{ // a[p1] > b[p2], we should find the bigger num in b, so move p2; but must limit p2 in index
                p2++;
            }
        }

        return (int)minDif;
    }
    /**
     * {1,2,3,11,15,22}
     * {8,19,23,127,235}
     * @param a
     * @param b
     * @return
     */
    public static long smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);

        long min = Long.MAX_VALUE;;
        for (int i : a) {
            int index = binarySearch(i, b);
            long dif = Long.MAX_VALUE;
            if(index == 0){
                dif = b[index] - i;
                if (dif < 0){
                    dif = -dif;
                }
            }else if(index == b.length){
                dif = b[index - 1] - i;
                if (dif < 0){
                    dif = -dif;
                }
            }else {
                long d1 = b[index] - i;
                long d2 = b[index - 1] - i;
                if (d1 < 0){
                    d1 = -d1;
                }
                if (d2 < 0){
                    d2 = -d2;
                }

                dif = Math.min(d1, d2);
            }
            min = Math.min(min, dif);
        }

        return min;
    }

    public static int binarySearch(int target, int[] nums){
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}
