package com.microsoft;

/**
 * @author ooo
 * @description 岛屿数量，DFS
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *
 * @date 2024/9/19 10:23:47
 */
public class NumIslands {
    /**
     * DFS + 感染算法 深度遍历，遇到1则岛屿数量+1，并将相邻的陆地全部变成0
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid){
        int num = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1'){
                    num++;
                    infect(grid, i, j);
                }
            }
        }

        return num;
    }

    private void infect(char[][] grid, int i, int j) {
        // 当i,j在合理范围内且grid[i][j]为陆地，则将相邻的陆地全部变为0
        if(i < 0 || i >= grid.length || j < 0 || j >= grid.length || grid[i][j] != '1'){
            return;
        }
        grid[i][j] = '0';
        infect(grid, i + 1, j);
        infect(grid, i - 1, j);
        infect(grid, i, j + 1);
        infect(grid, i, j - 1);
    }
}
