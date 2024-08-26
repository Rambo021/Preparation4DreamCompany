package com.booking.onsite.dfs;

/**
 * @author ooo
 * @description 超难的TSP问题
 * @date 2024/8/21 14:02:58
 * https://www.geeksforgeeks.org/problems/travelling-salesman-problem2732/1
 *
 * Given a matrix cost of size n where cost[i][j] denotes the cost of moving from city i to city j. Your task is to complete a tour from city 0 (0-based index) to all other cities such that you visit each city exactly once and then at the end come back to city 0 at minimum cost.
 *
 *
 * Example 1:
 *
 * Input: cost = {{0,111},{112,0}}
 * Output: 223
 * Explanation: We can visit 0->1->0 and
 * cost = 111 + 112.
 * Example 2:
 *
 * Input: cost = {{0,1000,5000},{5000,0,1000},
 * {1000,5000,0}}
 * Output: 3000
 * Explanation: We can visit 0->1->2->0 and cost
 * = 1000+1000+1000 = 3000
 *
 *
 * Your Task:
 * You don't need to read or print anything. Your task is to complete the function total_cost() which takes cost as the input parameter and returns the total cost to visit each city exactly once starting from city 0 and again coming back to city 0.
 *
 *
 * Expected Time Complexity: O(2n * n2)
 * Expected Space Compelxity: O(2n * n)
 *
 *
 * Constraints:
 * 1 <= n <= 20
 * 1 <= cost[i][j] <= 103
 */
public class TravellingSalesmanProblem {
}
