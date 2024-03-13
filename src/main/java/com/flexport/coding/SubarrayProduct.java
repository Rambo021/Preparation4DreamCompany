package com.flexport.coding;

/**
 * 计算连续子数组的数量，其元素乘积小于等于k
 */
public class SubarrayProduct {
    public static void main(String[] args) {
        int[] array = {1,1,1,1,1};
        System.out.printf("count:" + subarray(array, 1));
    }

    /**
     * 滑动窗口
     * 窗口长度从1到N，即[left, right]，向右移动时将arr[right]算到乘积P中，如果此时P<k，则说明此时窗口中有 right - left + 1个子数组满足条件，即：
     * {arr[left], arr[left+1],...,arr[right]}, {arr[right - n],...,arr[right]}
     *
     * @param arr
     * @param k
     * @return
     */
    public static int subarray(int[] arr, int k){
        if (k <= 1) return 0;

        int n = arr.length, count = 0, left = 0, right = 0, p = 1;
        while(right < n){
            p *= arr[right];
            // 如果此时窗口中元素的乘积 p 已经大于等于 k，则将窗口左侧右移
            while (left < n && p > k){
                p /= arr[left++];
            }
            // 如果 p < k，则说明此时窗口中与arr[right]组成的子数组都满足此条件
            // 同时将窗口右侧向右移动
            if(p < k){
                count += right - left + 1;
            }
            right++;
        }

        return count;
    }

}
