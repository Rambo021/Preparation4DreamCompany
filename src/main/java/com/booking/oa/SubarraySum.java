package com.booking.oa;

/**
 * @author ooo
 * @description 滑动窗口寻找连续子数组
 * Given an array, find the subarray which gives the given sum.
 * @date 2024/8/23 09:59:40
 */
public class SubarraySum {
    public static void main(String[] args) {
        int[] ary = {1,4,2,6,8,9};
        int sum = 11;

        int[] ret = findSubarrayWithSum(ary, sum);
        for (int i : ret) {
            System.out.printf("" + i + ",");
        }
    }

    public static int[] findSubarrayWithSum(int[] arr, int targetSum) {
        int start = 0, end = 0;
        int sum = arr[0];
        for (start = 0, end = 1; start < end && end < arr.length;) {
            if (sum > targetSum){
                sum -= arr[start++];
            }else if (sum < targetSum){
                sum += arr[end++];
            }else {
                break;
            }
        }
        if (sum != targetSum){
            return new int[]{-1};
        }

        int[] ret = new int[end - start];
        int index = 0;
        for (int i = start; i < end; i++) {
            ret[index++] = arr[i];
        }

        return ret;
    }
}
