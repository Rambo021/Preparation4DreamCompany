package com.booking.oa;

/**
 * @author ooo
 * @description Flipping the 2n x 2n Matrix to calculate the upper-left n x n subMatrix maximize sum
 * https://www.geeksforgeeks.org/maximize-sum-n-x-n-upper-left-sub-matrix-given-2n-x-2n-matrix/
 * In deepseek
 * @date 2024/8/21 20:54:47
 */
public class MatrixFlip {
    public static void main(String[] args) {
        int n = 2;
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        int[][] result = maximizeUpperLeftSubMatrix(matrix);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 首先分析矩阵的性质：矩阵可以通过任意的行或列翻转，使每个元素到达平面坐标的任意象限
     * 因此，求解左上角n x n子矩阵的最大值，即转换为 计算该子矩阵中每个位置所有可能元素的最大值之和
     * 有矩阵的性质可知，每个位置的元素的可能值有四个：原元素 + 轴对称的两个 + 中心对称的一个，即：
     * (x,y), (x, 2n - y - 1), (2n - x - 1, y), (2n - x - 1, 2n - y - 1)
     * 由于矩阵是用二维数组表示，所以对称的转换为： 2n - x/y - 1
     * @param matrix
     * @return
     */
    public static int[][] maximizeUpperLeftSubMatrix(int[][] matrix){
        int n = matrix.length >> 1;
        int[][] subMatrix = new int[n][n];

        for(int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                int maxVal = Math.max(
                        Math.max(matrix[i][j], matrix[i][2 * n - j - 1]),
                        Math.max(matrix[2 * n - i - 1][j], matrix[2 * n - i - 1][2 * n - j - 1])
                );

                subMatrix[i][j] = maxVal;
            }
        }

        return subMatrix;
    }
}
