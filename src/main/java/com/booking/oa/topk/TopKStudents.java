package com.booking.oa.topk;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ooo
 * @description
 * @date 2024/8/21 10:09:20
 */
public class TopKStudents {

    public static void main(String[] args) {
        String[] positive_feedback = {"smart", "brilliant", "studious"};
        String[] negative_feedback = {"not"};
        String[] report = {"this student is studious", "the student is smart"};
        int[] student_id = {1, 2};

        TopKStudents topKStudents = new TopKStudents();
        List<Integer> ret = topKStudents.topStudents(positive_feedback, negative_feedback, report, student_id, 2);
        System.out.println(ret);
    }

    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        // limit k not out of bounds
        k = Math.min(k, student_id.length);

        // Set the rule for sorting, score desc and id asc
        PriorityQueue<Pair<Integer, Integer>> minHeap = new PriorityQueue<>((p1, p2) -> {
            if(p1.getValue() > p2.getValue()){
                return 1;
            }else if(p1.getValue() < p2.getValue()){
                return -1;
            }else if(p1.getKey() < p2.getKey()){
                return 1;
            }else{
                return -1;
            }
        });

        // calculate score and add pair into minHeap
        // Pair: id + score
        Set<String> positives = Arrays.asList(positive_feedback).stream().collect(Collectors.toSet());
        Set<String> negatives = Arrays.asList(negative_feedback).stream().collect(Collectors.toSet());

        for(int i = 0; i < report.length; i++){
            int score = calScore(student_id[i], report[i], positives, negatives);

            Pair<Integer, Integer> pair = new Pair<>(student_id[i], score);

            // put pair into minHeap
            if(minHeap.size() < k){
                minHeap.offer(pair);
            }else{
                Pair<Integer, Integer> minPair = minHeap.peek();
                if(pair.getValue() > minPair.getValue() || (pair.getValue().equals(minPair.getValue()) && pair.getKey() < minPair.getKey())){
                    minHeap.poll();
                    minHeap.offer(pair);
                }
            }
        }

        // pick up the sorted pairs
        int[] topK = new int[k];
        for(int i = k - 1; i >= 0; i--){
            topK[i] = minHeap.poll().getKey();
        }
        return Arrays.stream(topK).boxed().collect(Collectors.toList());
    }

    public static int calScore(int id, String report, Set<String> positives, Set<String> negatives){
        int score = 0;
        for(String r : report.split(" ")){
            if(positives.contains(r)){
                score += 3;
            }
            if(negatives.contains(r)){
                score -= 1;
            }
        }

        return score;
    }
}
