package com.booking.oa;

import javafx.util.Pair;

import java.util.*;

public class OrderScore {

    public static void main(String[] args) {
        int[] score = {10,3,8,9,4};
        System.out.printf(Arrays.toString(findRelativeRanksPQ(score)));;
    }
    public static String[] findRelativeRanksPQ(int[] score) {
        int L = score.length;
        String[] ret = new String[L];

        PriorityQueue<Pair<Integer, Integer>> maxHeap = new PriorityQueue<>((p1, p2) -> p2.getKey() - p1.getKey());
        for (int i = 0; i < score.length; i++) {
            maxHeap.offer(new Pair<>(score[i], i));
        }

        int place = 1;
        while (!maxHeap.isEmpty()){
            Pair<Integer, Integer> pair = maxHeap.poll();
            switch (place){
                case 1:
                    ret[pair.getValue()] = "Gold Medal";
                    break;
                case 2:
                    ret[pair.getValue()] = "Silver Medal";
                    break;
                case 3:
                    ret[pair.getValue()] = "Bronze Medal";
                    break;
                default:
                    ret[pair.getValue()] = String.valueOf(place);
            }
            place++;
        }

        return ret;
    }
    /**
     * 1. record the index for socres;
     * 2. sort the scores;
     * 3. find index of scores;
     * 4. get index with order;
     */
    public static String[] findRelativeRanks(int[] score) {
        List<IndexOfScore> list = new ArrayList<>();
        for(int i = 0; i < score.length; i++){
            IndexOfScore is = new IndexOfScore(i, score[i]);
            list.add(is);
        }

        list.sort((l1, l2) -> l2.score - l1.score);

        for (int i = 0; i < list.size(); i++) {
            list.get(i).order = i + 1;
        }

        list.sort(Comparator.comparingInt(l -> l.order));

        String[] ret = new String[score.length];

        list.forEach(l -> {
            switch (l.order){
                case 1:
                    ret[l.index] = "Gold Medal";
                    break;
                case 2:
                    ret[l.index] = "Silver Medal";
                    break;
                case 3:
                    ret[l.index] = "Bronze Medal";
                    break;
                default:
                    ret[l.index] = String.valueOf(l.order);
            }
        });

        return ret;
    }

    static class IndexOfScore{
        int index;
        int score;

        int order;
        IndexOfScore(int index, int score){
            this.index = index;
            this.score = score;
        }
    }
}
