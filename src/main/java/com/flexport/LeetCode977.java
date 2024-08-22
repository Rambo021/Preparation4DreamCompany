package com.flexport;

public class LeetCode977 {

    public static void main(String[] args) {
        LeetCode977 l = new LeetCode977();
        int[] nums = new int[]{-7,-3,2,3,11};
        l.sortedSquares(nums);
    }
    public int[] sortedSquares(int[] nums) {
        // 首先遍历nums,确定绝对值最小的元素的位置；同时进行平方计算
        int index = 0, min = 0;
        for(int i = 0; i < nums.length;i++){
            if(Math.abs(nums[i]) < min){
                index = i;
                min = Math.abs(nums[index]);
            }
            nums[i] = sqrt(nums[i]);
        }
        // nums[0] = sqrt(nums[0]);

        System.out.println("index:" + index);

        for(int i = 0, j = index; i <= index && j < nums.length;){
            if(nums[i] <= nums[j]){
                i++;
            }else {
                int temp = nums[j];
                nums[j++] = nums[i];
                nums[i++] = temp;
            }
        }

        return nums;
    }

    public int sqrt(int i){
        return i * i;
    }
}
