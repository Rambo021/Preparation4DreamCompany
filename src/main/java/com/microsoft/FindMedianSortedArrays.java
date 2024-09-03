package com.microsoft;

/**
 * @author ooo
 * @description 虚招两个正序数组的中位数
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/description/
 *
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 * @date 2024/9/3 10:27:13
 */
public class FindMedianSortedArrays {
    public static void main(String[] args) {

    }

    public static double findMedianSortedArraysBinarySearch(int[] nums1, int[] nums2){
        return 0;
    }

    /**
     * 暴力解：双指针遍历两个数组，共移动 (l1 + l2) / 2 或 (l1 + l2) / 2 + 1 次
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2){
        int l1 = nums1.length, l2 = nums2.length;
        int left = 0, right = 0;
        int p1 = 0, p2 = 0;

        // 双指针移动：最多不超过 (l1 + l2) / 2 次
        // 判断当前right的值：当 nums1 还未遍历完，且 nums1 当前元素小于 nums2 的当前元素 或 num2 遍历完了
        // 则 right 为 nums1 的当前元素；否则 right 为 nums2 的当前元素
        for (int i = 0; i <= (l1 + l2) / 2; i++){
            // left 紧跟 right，当 (l1 + l2) 为偶数时，中位数为 left 和 right 的平均值
            left = right;
            if (p1 < l1 && (p2 >= l2 || nums1[p1] < nums2[p2])){
                right = nums1[p1++];
            }else {
                right = nums2[p2++];
            }
        }

        if ((l1 + l2) >> 1 == 0){
            return (left + right) / 2.0;
        }else {
            return right;
        }
    }
}
