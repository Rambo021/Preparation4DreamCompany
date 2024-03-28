package com.flexport.coding;

import java.util.Arrays;
import java.util.List;

/**
 * @description 判断凸多边形
 * 给定 X-Y 平面上的一组点 points ，其中 points[i] = [xi, yi] 。这些点按顺序连成一个多边形。
 *
 * 如果该多边形为 凸 多边形则返回 true ，否则返回 false 。
 */
public class LeetCode469ConvexPolygon {
    public static void main(String[] args) {
        List<List<Integer>> points = Arrays.asList(
                Arrays.asList(0,0),
                Arrays.asList(0,5),
                Arrays.asList(5,5),
                Arrays.asList(5,0)
        );

        List<List<Integer>> points2 = Arrays.asList(
                Arrays.asList(0,0),
                Arrays.asList(0,10),
                Arrays.asList(10,10),
                Arrays.asList(10,0),
                Arrays.asList(5,5)
        );
        System.out.printf("" + isConvex(points));
    }

    /**
     * 利用凸多边形和向量的外积性质来判断：
     * 1. 凸多边形内角都是小于180°的劣角；
     * 2. 所有相邻的两条边的外积的方向一致，根据右手定则我们假设劣角两条边的外积向上、优角两条边的外积向下
     *
     * 故：遍历连续的三个顶点组成的两条边的外积方向，如果和前面的外积方向一致则继续遍历；否则返回不是凸多边形
     * @param points
     * @return
     */
    public static boolean isConvex(List<List<Integer>> points){
        int n = points.size();
        long pre = 0, cur = 0;
        for (int i = 0; i < n; i++) {
            // 连续的三个顶点
            List<Integer> p1 = points.get(i);
            List<Integer> p2 = points.get((i + 1) % n);
            List<Integer> p3 = points.get((i + 2) % n);

            // 以p1为顶点，得到两条边：x1(p1-p2) 和 x2(p1-p3)；向量表示：(x1,y1)/(x2,y2)
            int x1 = p2.get(0) - p1.get(0);
            int y1 = p2.get(1) - p1.get(1);
            int x2 = p3.get(0) - p1.get(0);
            int y2 = p3.get(1) - p1.get(1);

            // 计算两条边x1和x2的外积
            cur = x1 * y2 - x2 * y1;
            // 如果两条边不平行，判断当前的外积和之前的外积是否相等
            if (cur != 0){
                // 如果当前外积和之前的外积和为负数，说明两个外积方向相反
                if (cur * pre < 0){
                    return false;
                }
                // 两个外积的方向相同，则继续判断
                pre = cur;
            }
        }
        return true;
    }
}
