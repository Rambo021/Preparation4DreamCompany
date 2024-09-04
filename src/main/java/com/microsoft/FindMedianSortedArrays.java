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
     * 二分法排除不符合的元素列表：寻找第K个元素--> 寻找第K/2个元素，递归
     * 合并奇数和偶数个元素的中位数：查询第 (m+n+1)/2 和第 (m+n+2)/2 的元素，求和再÷2即可
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2){
        int m = nums1.length, n = nums2.length;
        int left = (m + n + 1) / 2, right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    /**
     * 寻找 nums1 和 nums2 中的第 K 个元素
     * 分类讨论(discuss solution In different situations)：边界条件和一般情况
     * 1. nums1 或 nums2 为空(即起始点i/j已经超过了其长度)，则取nums2 或 nums1的第K个元素(从i/j开始，故 i/j + k - 1)；
     * 2. k = 1, 取nums1 和 nums2当前的第1个元素的最小值；
     * 3. 一般情况：分别在nums1 和 nums2 中查找 第 k / 2 个元素k1,k2，然后比较k1和k2，
     *      如果 k1 < k2，则舍弃nums1的 前k / 2个元素，去nums2中寻找 第 k - k / 2个元素(k - k / 2是为了统一k为奇偶数的情况)；反之亦然
     *      形成尾递归
     * @param nums1
     * @param i
     * @param nums2
     * @param j
     * @param k
     * @return
     */
    private static int findKth(int[] nums1, int i, int[] nums2, int j, int k){
        if (i >= nums1.length){
            return nums2[j + k - 1];
        }
        if (j >= nums2.length){
            return nums1[i + k - 1];
        }
        if (k == 1){
            return Math.min(nums1[i], nums2[j]);
        }
        int h = k / 2;
        int mid1 = (i + h - 1) < nums1.length ? nums1[i + h - 1] : Integer.MAX_VALUE;
        int mid2 = (j + h - 1) < nums2.length ? nums2[j + h - 1] : Integer.MAX_VALUE;

        return mid1 < mid2 ? findKth(nums1, i + h, nums2, j, k - h) : findKth(nums1, i, nums2, j + h, k - h);
    }
    /**
     * 暴力解：双指针遍历两个数组，共移动 (l1 + l2) / 2 或 (l1 + l2) / 2 + 1 次
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArraysBruteForce(int[] nums1, int[] nums2){
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
