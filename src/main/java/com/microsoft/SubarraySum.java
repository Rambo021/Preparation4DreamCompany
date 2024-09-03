package com.microsoft;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ooo
 * @description 和为K的子数组
 * https://leetcode.cn/problems/subarray-sum-equals-k/submissions/432720467/
 * @date 2024/9/3 10:01:15
 */
public class SubarraySum {
    public static void main(String[] args) {

    }

    public static int subarraySum(int[] nums, int k){
        // traverse The elements in the array and cal the sum of the subarrays, then put the count of the sum into map
        // eg. (1,1), (3,1), (5,2)
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);

        int sum = 0, count = 0;
        for (int num : nums) {
            sum += num;
            count += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
