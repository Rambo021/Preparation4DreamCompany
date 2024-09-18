package com.microsoft;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ooo
 * @description 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
 *
 * 你可以按任意顺序返回答案。
 * 只会存在一个有效答案
 * @date 2024/9/18 10:54:43
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int key = target - nums[i];
            if (map.get(key) == null){
                map.put(nums[i], i);
            }else {
                ans[0] = map.get(key);
                ans[1] = i;
            }
        }

        return ans;
    }
}
