package com.booking.oa;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * @author ooo
 * @description Given an array of integers, perform some number k of operations. Each operation consists of removing an element from the array,
 * dividing it by 2 and inserting the ceiling of that result back into the array. Minimize the sum of the elements in the final array.
 *
 * https://www.hackerrank.com/challenges/minimum-sum-11/problem
 * @date 2024/8/21 20:41:56
 */
public class MinSumWithKOperations {
    public static void main(String[] args) {
        int[] nums = {2,2,3};
        List<Integer> numList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        System.out.printf("" + minSum(numList, 1));
    }
    public static int minSum(List<Integer> num, int k) {
        // Write your code here
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i : num) {
            pq.offer(i);
        }

        for (int i = 0; i < k; i++) {
            int max = pq.poll();
            pq.offer((int)Math.ceil(max / 2.0));
        }

        int sum = 0;
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }
        return sum;
    }
}
