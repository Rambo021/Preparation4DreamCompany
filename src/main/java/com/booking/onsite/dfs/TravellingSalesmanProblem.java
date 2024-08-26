package com.booking.onsite.dfs;

/**
 * @author ooo
 * @description 超难的TSP问题：状态压缩DP
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

    public int total_cost(int[][] cost) {
        // Code here
        int n = cost.length;
        // init the dp with -1 to store the optimal cost
        int[][] dp = new int[1 << n ][n];

        return tsp(1, 0, n, cost, dp);
    }

    /**
     * mask: the flag represents visited city(bit i == 1 or 0)
     * pos: current position
     *
     */
    private int tsp(int mask, int pos, int n, int[][] cost, int[][] dp){
        // exit: if all the cities have been visited, return the cost from pos to starting city
        if(mask == (1 << n) - 1){
            return cost[pos][0];
        }

        if(dp[mask][pos] == 0){
            dp[mask][pos] = Integer.MAX_VALUE;

            for(int city = 0; city < n; city++){
                //
                if((mask & (1 << city)) == 0){
                    dp[mask][pos] = Math.min(dp[mask][pos], cost[pos][city] + tsp(mask | (1 << city), city, n, cost, dp));
                }
            }
        }

        return dp[mask][pos];
    }
}
