package com.flexport.coding;

/**
 * @description 设计井字棋
 *
 * 请在 n × n 的棋盘上，实现一个判定井字棋（Tic-Tac-Toe）胜负的神器，判断每一次玩家落子后，是否有胜出的玩家。
 *
 * 在这个井字棋游戏中，会有 2 名玩家，他们将轮流在棋盘上放置自己的棋子。
 *
 * 在实现这个判定器的过程中，你可以假设以下这些规则一定成立：
 *
 *       1. 每一步棋都是在棋盘内的，并且只能被放置在一个空的格子里；
 *
 *       2. 一旦游戏中有一名玩家胜出的话，游戏将不能再继续；
 *
 *       3. 一个玩家如果在同一行、同一列或者同一斜对角线上都放置了自己的棋子，那么他便获得胜利。
 *
 *  输入 move(x, y, playerNO), 输出 playerNO(分出胜负) 或 0(未分出胜负)
 */
public class DesignTicTacToe {
    int[][] boarder;

    public DesignTicTacToe(int n) {
        this.boarder = new int[n][n];
    }

    public static void main(String[] args) {
        DesignTicTacToe ticTacToe = new DesignTicTacToe(3);
        System.out.println("" + ticTacToe.move(0, 0, 1));
        System.out.println("" + ticTacToe.move(0, 2, 2));
        System.out.println("" + ticTacToe.move(2, 2, 1));
        System.out.println("" + ticTacToe.move(1, 1, 2));
        System.out.println("" + ticTacToe.move(2, 0, 1));
        System.out.println("" + ticTacToe.move(1, 0, 2));
        System.out.println("" + ticTacToe.move(2, 1, 1));
    }

    /**
     * 下棋
     * @param x
     * @param y
     * @param player 1：棋手1；2：棋手2
     * @return
     */
    public int move(int x, int y, int player){
        int n = this.boarder.length;
        if (x < 0 || x >= n || y < 0 || y >= n){
            return 0;
        }
        boarder[x][y] = player;

        // 判断 (x,y) 所在坐标的横线上(x,[0,n-1])、竖线上([0,n-1], y)以及两条对角线(只有(x,y)在对角线上时才需要判断)的所有棋子是否是同一个颜色
        boolean flag = checkH(x, player) || checkV(y, player) || checkD1(x, y, player) || checkD2(x, y, player);

        for (int i = 0; i < n; i++) {

        }
        return flag ? player : 0;
    }

    // 判断 (x,y) 所在坐标的横线上(x,[0,n-1])的所有棋子是否是同一个颜色
    public boolean checkH(int x, int player){
        int n = boarder.length;
        for (int i = 0; i < n; i++) {
            if (boarder[x][i] != player){
                return false;
            }
        }

        return true;
    }

    // 判断 (x,y) 所在坐标的竖线上([0,n-1], y)的所有棋子是否是同一个颜色
    public boolean checkV(int y, int player){
        int n = boarder.length;
        for (int i = 0; i < n; i++) {
            if (boarder[i][y] != player){
                return false;
            }
        }

        return true;
    }

    // 判断 (x,y) 所在坐标的两条对角线(只有(x,y)在对角线上时才需要判断)的所有棋子是否是同一个颜色
    public boolean checkD1(int x, int y, int player){
        int n = boarder.length;

        // 先判断是否是对角线上的坐标；需满足 x == y || (x + y) = n - 1
        if (x != y && (x + y) != n - 1){
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (boarder[i][i] != player){
                return false;
            }
        }
        return true;
    }

    // 判断 (x,y) 所在坐标的两条对角线(只有(x,y)在对角线上时才需要判断)的所有棋子是否是同一个颜色
    public boolean checkD2(int x, int y, int player){
        int n = boarder.length;

        // 先判断是否是对角线上的坐标；需满足 x == y || (x + y) = n - 1
        if (x != y && (x + y) != n - 1){
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (boarder[i][n - 1 - i] != player){
                return false;
            }
        }
        return true;
    }


}
