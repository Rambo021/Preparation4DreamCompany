package com.microsoft;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ooo
 * @description 三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * @date 2024/9/14 15:54:09
 */
public class ThreeSum {
    public static void main(String[] args) {
//        int[] nums = {-1,0,1,2,-1,-4};
//        int[] nums = {0,0,0};
        int[] nums = {3,0,-2,-1,1,2};

        ThreeSum ts = new ThreeSum();
        System.out.println("xx:" + ts.threeSum(nums));
    }

    /**
     * 排序后，遍历数组，每次使用双指针分别从两边取元素：
     * 1. 计算三数之和 sum；
     * 2. 判断 sum 是否等于0，是则得到当前结果；
     *    2.1. < 0，说明左边的数小了，需要右移；
     *    2.2. > 0，说明右边的数大了，需要左移
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> temp = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            // 左右指针，从左右两边取值
            int left = i + 1, right = nums.length - 1;
            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0){
                    temp.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 去重
                    while (left < right && nums[left] == nums[left + 1]){
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]){
                        right--;
                    }
                    left++;
                    right--;
                }else if (sum < 0){
                    left++;
                }else {
                    right--;
                }
            }
        }

        // 去重
        List<List<Integer>> ans = new ArrayList<>();
        Set<String> threeNumSet = new HashSet<>();
        for (List<Integer> list : temp) {
            list.sort(Comparator.naturalOrder());
            String str = list.stream().map(String::valueOf).collect(Collectors.joining(","));
            threeNumSet.add(str);
        }
        for (String str : threeNumSet) {
            List<Integer> group = Arrays.stream(str.split(",")).map(Integer::valueOf).collect(Collectors.toList());
            ans.add(group);
        }

        return ans;
    }

    /**
     * 暴力破解：转化为TwoSum求解
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSumBruteForce(int[] nums) {
        List<List<Integer>> temp = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                int twoSum = nums[i] + nums[j];
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[k] == 0 - twoSum){
                        temp.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }

        // 去重
        List<List<Integer>> ans = new ArrayList<>();
        Set<String> threeNumSet = new HashSet<>();
        for (List<Integer> list : temp) {
            list.sort(Comparator.naturalOrder());
            String str = list.stream().map(String::valueOf).collect(Collectors.joining(","));
            threeNumSet.add(str);
        }
        for (String str : threeNumSet) {
            List<Integer> group = Arrays.stream(str.split(",")).map(Integer::valueOf).collect(Collectors.toList());
            ans.add(group);
        }

        return ans;
    }

    private Set<String> process(int num, int num1, Set<Integer> ret) {
        Set<String> ss = new HashSet<>();
        for (Integer integer : ret) {
            List<Integer> list = Arrays.asList(num, num1, integer);
            list.sort(Comparator.naturalOrder());
            ss.add(list.stream().map(String::valueOf).collect(Collectors.joining(",")));
        }
        return ss;
    }

    private Set<Integer> twoSum(int[] nums, int index, int a){
        Set<Integer> set = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (nums[i] == a){
                set.add(nums[i]);
            }
        }

        return set;
    }
}
