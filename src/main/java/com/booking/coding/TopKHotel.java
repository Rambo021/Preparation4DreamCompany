package com.booking.coding;

import java.util.*;

/**
 * Given positive and negative words, hotels and their reviews, return top K hotel based on the reviews.
 * 问题分析
 *
 * 给定一组酒店及其评论，评论中包含正面和负面词汇，要求返回评分最高的前K家酒店。
 *
 * 输入
 *
 * 一个酒店列表，每个酒店包含其名称和评论列表
 * 一个正面词汇列表
 * 一个负面词汇列表
 * 一个整数K，表示返回的酒店数量
 * 输出
 *
 * 评分最高的前K家酒店的名称列表
 * 例子
 *
 * 酒店列表：[{"name": "Hotel A", "reviews": ["good", "bad", "good"]}, {"name": "Hotel B", "reviews": ["good", "good", "good"]}, {"name": "Hotel C", "reviews": ["bad", "bad", "bad"]}]
 * 正面词汇列表：["good"]
 * 负面词汇列表：["bad"]
 * K：2
 * 输出：["Hotel B", "Hotel A"]
 *
 * 首先给Hotel进行评分，得到 {Name: a, Score: 1}的列表；
 * 然后使用小顶堆(Min Heap)存放这些列表
 * 取出小顶堆的数据即可
 */
public class TopKHotel {
    public static List<String> rankHotels(List<Hotel> hotels, List<String> positiveWords, List<String> negativeWords, int k) {
        // 创建小顶堆
        PriorityQueue<Hotel> queue = new PriorityQueue<>((o1, o2) -> Double.compare(o1.getScore(), o2.getScore()));

        // 计算每个酒店的评分并插入小顶堆
        for (Hotel hotel : hotels) {
            double score = 0;
            for (String review : hotel.getReviews()) {
                if (positiveWords.contains(review)) {
                    score += 1;
                } else if (negativeWords.contains(review)) {
                    score -= 1;
                }
            }
            hotel.setScore(score);
            if (queue.size() < k) {
                queue.add(hotel);
            } else if (score > queue.peek().getScore()) {
                // 如果当前分数大于小顶堆的堆顶的分数(即最小分数)，则将堆顶移除，并将当前分数加入
                queue.poll();
                queue.add(hotel);
            }
        }

        // 输出Top K的Hotel
        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll().getName());
        }
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        List<Hotel> hotels = Arrays.asList(
                new Hotel("Hotel A", Arrays.asList("good", "bad", "good")),
                new Hotel("Hotel B", Arrays.asList("good", "good", "good")),
                new Hotel("Hotel C", Arrays.asList("bad", "bad", "bad"))
        );
        List<String> positiveWords = Arrays.asList("good");
        List<String> negativeWords = Arrays.asList("bad");
        int k = 2;
        List<String> result = rankHotels(hotels, positiveWords, negativeWords, k);
        System.out.println(result);
    }

    static class Hotel {
        private String name;
        private List<String> reviews;
        private double score;

        public Hotel(String name, List<String> reviews) {
            this.name = name;
            this.reviews = reviews;
        }

        public String getName() {
            return name;
        }

        public List<String> getReviews() {
            return reviews;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }
    }
}
