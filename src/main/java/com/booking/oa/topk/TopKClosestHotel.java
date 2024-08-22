package com.booking.oa.topk;

import javafx.util.Pair;

import java.util.PriorityQueue;

/**
 * @author ooo
 * @description
 * @date 2024/8/21 14:15:22
 *
 * https://interviewing.io/questions/k-closest-points-to-origin
 */
public class TopKClosestHotel {
    public static void main(String[] args) {
        int[][] points = {{3,3},{5,-1},{-2,4}};
        int k = 2;
    }

    /**
     * If for the Hotels, just change the pair.key by the name of hotel, then output the names
     *
     * cal the distance of all the points to (0,0)
     * Create a Pair with point and the distance
     * sort the pair in maxHeap
     */
    public int[][] kClosest(int[][] points, int k) {
        // ret
        int[][] ret = new int[k][2];

        // maxHeap to store and sort the pair
        PriorityQueue<Pair<int[], Integer>> maxHeap = new PriorityQueue<>(k, (p1, p2) -> p2.getValue() - p1.getValue());

        for(int i = 0; i < points.length; i++){
            int[] point = points[i];

            Integer distanceWithSquare = point[0] * point[0] + point[1] * point[1];

            Pair<int[],Integer> pair = new Pair<>(point, distanceWithSquare);

            if(maxHeap.size() < k){
                maxHeap.offer(pair);
            }else if(pair.getValue() < maxHeap.peek().getValue()){
                maxHeap.poll();
                maxHeap.offer(pair);
            }
        }

        for(int i = k - 1; i >= 0; i--){
            ret[i] = maxHeap.poll().getKey();
        }

        return ret;

    }
}
