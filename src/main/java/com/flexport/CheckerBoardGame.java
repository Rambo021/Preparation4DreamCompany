package com.flexport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 棋盘游戏
 * 在 8x8的棋盘上，有棋子黑(用'B'表示)和白(用'W'表示)以及空白格(用'.'表示)；棋子只能沿着对角线方向移动2格(中间必须有对方的棋子，不吃子)；
 * 当棋子移动到边界或可移动的方向上有棋子了，棋子不能再移动。
 * 你需要自定义棋盘、棋子以及相应的方法签名
 *
 * Step 1:
 * 给出一个具体的棋子的坐标，输出该棋子可以移动的所有位置的坐标
 *
 * Step 2:
 * 如果棋子增加 王 的角色且可以吃子如何处理？
 * 1. 不考虑如何成为王；
 * 2. 王可以对角线走N格吃子；对于王，将directions增加到2、4、6、8
 * 3. 吃子后可以继续移动。由于可以连吃，将是否可以移动的逻辑抽成一个方法，然后使用递归的方式进行移动
 */
public class CheckerBoardGame {

    public static void main(String[] args) {
        String[][] boards = {
                {".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".","."},
                {".",".","B",".","B",".",".","."},
                {".",".",".","W",".",".",".","."},
                {".",".","B",".","B",".",".","."},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".","."},
        };

        List<List<Integer>> nextMoveList = nextMoves(boards, 3, 3, "W");
        System.out.printf("nextMoveList: " + nextMoveList);
    }

    public static List<List<Integer>> nextMoves(String[][] boards, int x, int y, String color){
        if (boards[x][y].equals(".") || !boards[x][y].equals(color)){
            System.out.println("Error Input: " + boards[x][y]);
            return Collections.emptyList();
        }
        List<List<Integer>> nextMoveList = new ArrayList<>();

        // 定义棋子可以移动的四个方向：左下、左上、右下、右上
        int[][] directions = {{-2,-2}, {-2, 2}, {2, -2}, {2, 2}};
        // 遍历棋子可以移动的四个方向
        for (int i = 0; i < 4; i++) {
            int[] direction = directions[i];
            // 移动到一个方向，得到新的坐标
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            // 判断新坐标是否在棋盘内
            if (nextX >= 0 && nextX < 8 && nextY >= 0 && nextY < 8){
                // 计算路径中间的位置的坐标
                int midX = x + direction[0] / 2;
                int midY = y + direction[1] / 2;
                // 如果在棋盘内，则判断新坐标上是否有棋子且中间的位置是否有对方棋子
                if (boards[nextX][nextY].equalsIgnoreCase(".") && boards[midX][midY].equals(getOpponent(color))){
                    nextMoveList.add(Arrays.asList(nextX, nextY));
                    System.out.println(String.format("Moved from (%d,%d) to (%d,%d)", x, y, nextX, nextY));
                }
            }

        }
        return nextMoveList;
    }

    private static String getOpponent(String color) {
        return color.equals("B") ? "W" : "B";
    }
}
