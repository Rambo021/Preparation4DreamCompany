package com.booking.oa.bfs;

import java.util.*;

/**
 * @author ooo
 * @description 骑士的最小移动数
 * @date 2024/8/20 09:53:46
 *
 * 一个坐标可以从 -infinity 延伸到 +infinity 的 无限大的 棋盘上，你的 骑士 驻扎在坐标为 [0, 0] 的方格里。
 *
 * 骑士的走法和中国象棋中的马相似，走 “日” 字：即先向左（或右）走 1 格，再向上（或下）走 2 格；或先向左（或右）走 2 格，再向上（或下）走 1 格。
 *
 * 每次移动，他都可以按图示八个方向之一前进。
 *
 * 返回 骑士前去征服坐标为 [x, y] 的部落所需的最小移动次数 。本题确保答案是一定存在的。
 *
 * Example 2:
 *
 * Input: x = 5, y = 5
 * Output: 4
 * Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 *
 *
 * Constraints:
 *
 * -300 <= x, y <= 300
 * 0 <= |x| + |y| <= 300
 */
public class MinimumKnightMove {

    public static void main(String[] args) {
        System.out.println(minKnightMoves(2, 1));
        System.out.println(minKnightMoves(5, 5));

        List<int[]> path = minKnightMovesWithPath(5,5);
        for (int[] point : path) {
            System.out.println("(" + point[0] + "," + point[1] + ")");
        }
    }

    public static List<int[]> minKnightMovesWithPath(int x, int y) {
        int[][] directions = {{1,2}, {1,-2}, {-1, 2}, {-1,-2}, {2,1}, {2,-1}, {-2,1}, {-2,-1}};
        // 限制到第一象限
        x = Math.abs(x);
        y = Math.abs(y);

        // 使用队列存储八个方向的点
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});
        // 使用Set存储已经访问过的点
        Set<String> visited = new HashSet<>();
        visited.add("0,0");

        Map<String, int[]> path = new HashMap<>();

        // 遍历队列
        while (!queue.isEmpty()){
            // 当前队列里的点的数量
            int n = queue.size();
            // 只遍历n个点：计算八个方向的点是否包含(x,y)，如果没有 判断这些点是否访问过且未超限，如果没有则存到队列里下次访问
            for (int i = 0; i < n; i++) {
                int[] point = queue.poll();
                if (point[0] == x && point[1] == y){
                    return reconstructPath(path, x, y);
                }
                for (int[] direction : directions) {
                    int newX = point[0] + direction[0];
                    int newY = point[1] + direction[1];
                    int[] current = new int[]{newX, newY};

                    String key = String.format("%s,%s", newX, newY);
                    if (!visited.contains(key) && newX >= -1 && newY >= -1 && newX <= x + 2 && newY <= y + 2){
                        visited.add(key);
                        queue.offer(current);

                        // 把当前点指向前面的点
                        path.put(key, point);
                    }
                }
            }
        }

        return new ArrayList<>();
    }

    private static List<int[]> reconstructPath(Map<String, int[]> path, int x, int y) {
        List<int[]> ret = new ArrayList<>();

        int[] current = new int[]{x, y};
        while (current != null){
            ret.add(current);
            String key = current[0] + "," + current[1];
            current = path.get(key);
        }

        Collections.reverse(ret);

        return ret;
    }


    /**
     * 八个方向：{{1,2}, {1,-2}, {-1,2}, {-1,-2},{2,1},{2,-1},{-2,1},{-2,-1}}
     * 骑士从一个坐标可以往八个方向移动，判断移动后的坐标是否和(x,y)相同；如果不同则从这八个方向继续往前探索
     * 由此，可以考虑使用BFS进行搜索，同时记录已经访问过的点
     * 时间复杂度：O(8^n)
     *
     * 优化：
     * 1. 坐标具有对称性，因此将 (x,y)取绝对值，只需要考虑第一象限的情况即可；
     * 2. 限制骑士探索的范围：i >= -1 && j >= -1 意味着骑士最多只需要从第一象限往左和下多走一格；
     * i <= x + 2 && j <= y + 2 因为超过(x + 2, 0) 和 (0, y + 2)这个范围的点无意义(多走的步骤), 观察棋盘上能够到达(x,y)的八个点的性质即可得到此结论
     * @param x
     * @param y
     * @return
     */
    public static int minKnightMoves(int x, int y) {
        int[][] directions = {{1,2}, {1,-2}, {-1, 2}, {-1,-2}, {2,1}, {2,-1}, {-2,1}, {-2,-1}};
        // 限制到第一象限
        x = Math.abs(x);
        y = Math.abs(y);

        // 使用队列存储八个方向的点
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});
        // 使用Set存储已经访问过的点
        Set<String> visited = new HashSet<>();
        visited.add("0,0");

        int moves = 0;
        // 遍历队列
        while (!queue.isEmpty()){
            // 当前队列里的点的数量
            int n = queue.size();
            // 只遍历n个点：计算八个方向的点是否包含(x,y)，如果没有 判断这些点是否访问过且未超限，如果没有则存到队列里下次访问
            for (int i = 0; i < n; i++) {
                int[] point = queue.poll();
                if (point[0] == x && point[1] == y){
                    return moves;
                }
                for (int[] direction : directions) {
                    int newX = point[0] + direction[0];
                    int newY = point[1] + direction[1];

                    String key = String.format("%s,%s", newX, newY);
                    if (!visited.contains(key) && newX >= -1 && newY >= -1 && newX <= x + 2 && newY <= y + 2){
                        visited.add(key);
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}
