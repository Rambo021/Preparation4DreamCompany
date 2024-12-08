package com.booking.onsite;

import javafx.util.Pair;

import java.util.*;

/**
 * @author ooo
 * @description 给定一组房价的范围，当某个房价的最高值+1等于其他房价的最低值时合并这两个房价范围
 * Merge two hotel objects based on their high and low price range. Hotel objects were not in order.
 *
 *
 *   Merge HotelRoom object when highPrice + 1 = lowPrice of other object
 *   Ex.1  [1,2], [3,4] ==> [1,4]
 *   Ex.2  [1,5],[6,9] ==> [1,9]
 *   Ex.3 [1,5], [14, 17], [6,9], [10,13] ==> [1,17]
 *   Ex.3 [1,5], [14, 17], [6,9], [10,13], [4,7], [8,12] ==> [1,17], [4,12]
 *
 *   after sort:
 *   [1,5], [4,7], [6,9], [8,12], [10,13], [14,17]
 *
 * @date 2024/10/14 14:46:12
 */
public class MergeHotelPrices {

    public static void main(String[] args) {
        MergeHotelPrices m = new MergeHotelPrices();

//        int[][] prices = {{1,5}, {14, 17}, {6,9}, {10,13}, {4,7}, {8,12}};
//        int[][] prices = {{1,5}, {14, 17}, {6,9}, {10,13}};
        int[][] prices = {{1,5},{7,9}};

        List<int[]> ans = m.mergePrices(prices);

        for (int[] item : ans) {
            System.out.print("[" + item[0] + "," + item[1] + "], ");
        }
    }

    /**
     * Questions:
     * 1. is every interval unique？ if so, we can put pair into map;
     *
     *  Solution 1:
     * 1. sort list by lowPrice and highPrice asc;
     * 2. put highPrice and price pair into a map;
     * 3. go over the list to find pair from the map by the lowPrice + 1
     *
     *  Solution 2:
     *  1. we could go over the list no sort, if so we need 2 maps to store [lowPrice, pair] and [highPrice, pair],
     *  then just extend the interval from both side.
     * @param prices
     * @return
     */
    public List<int[]> mergePrices(int[][] prices){
        List<int[]> ans = new ArrayList<>();

        // sort: lowest TC: NlogN with quick sort or heap sort
        Arrays.sort(prices, (a ,b) -> {
            if (a[0] < b[0]){
                return -1;
            }else if (a[0] > b[0]){
                return 1;
            }else if (a[1] < b[1]){
                return -1;
            }else if (a[1] > b[1]){
                return 1;
            }else {
                return 0;
            }
        });

        // put highPrice and pair into a map
        Map<Integer, Pair<int[], Integer>> map = new HashMap<>();
        for (int i = 0; i < prices.length; i++) {
            int[] price = prices[i];
            map.put(price[0], new Pair<>(price, i));
            // print prices and map to check data
            System.out.print("[" + price[0] + "," + price[1] + "], ");
        }
        System.out.println("");

        int[] used = new int[prices.length];
        // go over the list
        for (int i = 0; i < prices.length && used[i] == 0; i++) {
            int[] interval = prices[i];
            while (interval != null){
                Pair<int[], Integer> value = map.get(interval[1] + 1);
                // not match, just add into ans and init interval
                if (value == null){
                    ans.add(interval);
                    interval = null;
                }else {
                    // matched, deal with the normal situation, and remove the matched interval from prices
                    interval[1] = value.getKey()[1];
                    // tag the pair was used
                    used[value.getValue()] = 1;
                }
            }
        }
        return ans;
    }
}
