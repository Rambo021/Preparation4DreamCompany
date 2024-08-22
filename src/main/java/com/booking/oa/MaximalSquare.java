package com.booking.oa;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ooo
 * @description https://leetcode.com/problems/maximal-square/description/
 * @date 2024/8/21 20:56:17
 */
public class MaximalSquare {
    Set<int[]> contain0Set = new HashSet<>();
    public static void main(String[] args) {
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};

        MaximalSquare m = new MaximalSquare();
        System.out.println(m.maximalSquare(matrix) + "");
    }


    /**
     * Dynamic Programming
     * dp[i][j]: the side length of the largest square(only '1') with the bottom-left point (i,j)
     * Transition Formula: if matrix[i - 1][j - 1] == '1' dp[i][j] = min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1]) + 1
     * dp[1][1] = 0;
     * @param matrix
     * @return
     */
    public int maximalSquareDP(char[][] matrix){
        int m = matrix.length, n = matrix[0].length, maxSide = 0;

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(matrix[i - 1][j - 1] == '1'){
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        return maxSide * maxSide;
    }
    /**
     * 暴力解(Brute Force) 超时
     * assuming the smaller length of side in matrix is n,
     * we could go through n -- 1 as the side for a square, then to check all the elements containing 0
     * if true, ignore it; otherwise return n * n
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        initSet(matrix);

        int side = Math.min(m,n);
        while (side > 0){
            for (int i = 0; i <= m - side; i++) {
                for (int j = 0; j <= n - side; j++) {
                    if (!check0(i, j, side)){
                        return side * side;
                    }
                }
            }
            side--;
        }

        return 0;
    }

    /**
     * check matrix[i, i + side][j, j + side] containing any '0'
     * @param i
     * @param j
     * @param side
     * @return
     */
    private boolean check0(int i, int j, int side) {
        int maxI = i + side - 1, maxJ = j + side - 1;
        for (int[] cor : contain0Set) {
            if (cor[0] >= i && cor[0] <=  maxI && cor[1] >= j && cor[1] <= maxJ){
                return true;
            }
        }
        return false;
    }

    private void initSet(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0'){
                    contain0Set.add(new int[]{i, j});
                }
            }
        }
    }
}
