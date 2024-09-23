package com.microsoft;

import java.util.Arrays;

/**
 * @author ooo
 * @description 最长上升子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的
 * 子序列
 * 。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 *
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 *
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 * @date 2024/9/20 09:46:06
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLISInBS(int[] nums) {
        int max = 0;

        int[] tails = new int[nums.length];
        for (int num : nums) {
            // 二分查找num在tails中的位置
            int left = 0, right = max;
            while (left < right){
                int mid = left + (right - left) / 2;
                if (tails[mid] < num){
                    left = mid + 1;
                }else {
                    right = mid;
                }
            }

            // 如果left等于tails当前有序元素的数量，那么说明left大于tails中所以元素，tails是有序上升
            tails[left] = num;
            if (left == max){
                max++;
            }
        }
        return max;
    }

    /**
     * 对于所以的 j < i，如果nums[i] > nums[j]，则有dp[i] = max(dp[i], dp[j] + 1)
     * @param nums
     * @return
     */
    public int lengthOfLISInDP(int[] nums){
        int max = 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }

        return max;
    }
}
