package com.microsoft;

/**
 * @author ooo
 * @description 山脉数组中查找目标值
 * https://leetcode.cn/problems/find-in-mountain-array/description/
 * （这是一个 交互式问题 ）
 *
 * 你可以将一个数组 arr 称为 山脉数组 当且仅当：
 *
 * arr.length >= 3
 * 存在一些 0 < i < arr.length - 1 的 i 使得：
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * 给定一个山脉数组 mountainArr ，返回 最小 的 index 使得 mountainArr.get(index) == target。如果不存在这样的 index，返回 -1 。
 *
 * 你无法直接访问山脉数组。你只能使用 MountainArray 接口来访问数组：
 *
 * MountainArray.get(k) 返回数组中下标为 k 的元素（从 0 开始）。
 * MountainArray.length() 返回数组的长度。
 * 调用 MountainArray.get 超过 100 次的提交会被判定为错误答案。此外，任何试图绕过在线评测的解决方案都将导致取消资格。
 *
 *
 *
 * 示例 1：
 *
 * 输入：array = [1,2,3,4,5,3,1], target = 3
 * 输出：2
 * 解释：3 在数组中出现了两次，下标分别为 2 和 5，我们返回最小的下标 2。
 * 示例 2：
 *
 * 输入：array = [0,1,2,4,2,1], target = 3
 * 输出：-1
 * 解释：3 在数组中没有出现，返回 -1。
 *
 *
 * 提示：
 *
 * 3 <= mountain_arr.length() <= 104
 * 0 <= target <= 109
 * 0 <= mountain_arr.get(index) <= 109
 * @date 2024/9/23 09:48:32
 */
public class FindInMountainArray {
    public static void main(String[] args) {
        int[] ary = {1,2,3,4,5,3,1};
        FindInMountainArray f = new FindInMountainArray();
        f.findInMountainArray(3, new MountainArray(ary));
    }
    /**
     * 先用二分法查找到山脉数组的峰值的位置index，将该山脉数组分为[0,index]的升序数组和[index + 1, len)的降序数组
     * 然后在升序数组中查找target，如果查找到了则返回，否则查询降序数组
     * @param target
     * @param mountainArr
     * @return
     */
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int peekIndex = findPeek(mountainArr);
        int ans = binarySearchAsc(mountainArr, peekIndex, target);
        if (ans < 0){
            ans = binarySearchDesc(mountainArr, peekIndex, target);
        }
        return ans;
    }

    private int binarySearchDesc(MountainArray mountainArr, int peekIndex, int target) {
        int l = peekIndex, r = mountainArr.length() - 1;
        while (l <= r){
            int m = l + (r - l) / 2;
            if (mountainArr.get(m) == target){
                return m;
            }
            if (mountainArr.get(m) > target){
                l = m + 1;
            }else {
                r = m - 1;
            }
        }

        return -1;
    }

    /**
     * 升序数组中二分查找target
     * @param mountainArr
     * @param peekIndex
     * @param target
     * @return
     */
    private int binarySearchAsc(MountainArray mountainArr, int peekIndex, int target) {
        int l = 0, r = peekIndex;
        while (l <= r){
            int m = l + (r - l) / 2;
            if (mountainArr.get(m) == target){
                return m;
            }
            if (mountainArr.get(m) < target){
                l = m + 1;
            }else {
                r = m - 1;
            }
        }

        return -1;
    }

    /**
     * 二分法查找峰值位置
     * @param mountainArr
     * @return
     */
    private int findPeek(MountainArray mountainArr) {
        int l = 0, r = mountainArr.length() - 1;
        while (l < r){
            // 当 m 落在升序段时，l左移；反之r右移
            int m = l + (r - l) / 2;
            if (m + 1 < mountainArr.length() && mountainArr.get(m) < mountainArr.get(m + 1)){
                l = m + 1;
            }else {
                r = m;
            }
        }
        return l;
    }

    private static class MountainArray {
        public MountainArray(int[] ary) {
            this.ary = ary;
        }

        private int[] ary = null;
        public int get(int index) {
            return ary[index];
        }
        public int length() {
            return ary.length;
        }
    }
}
