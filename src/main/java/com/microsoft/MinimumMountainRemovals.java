package com.microsoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ooo
 * @description 得到山形数组的最小删除次数
 * @date 2024/9/20 10:54:41
 */
public class MinimumMountainRemovals {
    public static void main(String[] args) {
        int[] nums = {4,3,2,1,1,2,3,1};

        MinimumMountainRemovals m = new MinimumMountainRemovals();
        System.out.printf("" + m.minimumMountainRemovals(nums));
    }
    /**
     * 从左到右求[0,i]数组的最长上升子序列的长度列表pre[]和从左到右的suf[]
     * 遍历两个数组，求对于位置i来说，左右两部分LIS都大于1的长度，即当前位置i的山形数组的长度
     * 反过来即需要删除的次数
     * @param nums
     * @return
     */
    public int minimumMountainRemovals(int[] nums) {
        int[] pres = getLISAry(nums);
        int[] sufs = getLISAry(reverse(nums));
        sufs = reverse(sufs);

        int max = 2;
        for (int i = 0; i < nums.length; i++) {
            if (pres[i] > 1 && sufs[i] > 1){
                max = Math.max(max, pres[i] + sufs[i] - 1);
            }
        }

        return nums.length - max;
    }


    private int[] reverse(int[] nums){
        for (int left = 0, right = nums.length - 1; left <= right;) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++; right--;
        }

        return nums;
    }
    private int[] getLISAry(int nums[]){
        int n = nums.length;
        int[] dp = new int[n];
        List<Integer> seq = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int index = binarySearch(seq, nums[i]);
            if (index == seq.size()){
                seq.add(nums[i]);
                dp[i] = seq.size();
            }else {
                seq.set(index, nums[i]);
                dp[i] = index + 1;
            }
        }

        return dp;
    }
    /**
     * 求解[0, index]子数组中的最长上升子序列长度
     * @return
     */
    private int binarySearch(List<Integer> seq, int target){
        int left = 0, right = seq.size();
        while (left < right){
            int mid = left + (right - left) / 2;
            if (seq.get(mid) >= target){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }
}
