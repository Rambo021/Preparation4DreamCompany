package com.microsoft;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ooo
 * @description 寻找数据流中的特定位置的数(中位数 、 90%)
 *
 * 我们使用大顶堆 + 小顶堆 存储这些数字
 *
 * 如果是内存有限，则
 * @date 2024/9/3 13:48:15
 */
public class MedianInDataStream {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    int maxSize = 18, minSize = 101;
    int count = 0;
    public static void main(String[] args) {
        MedianInDataStream m = new MedianInDataStream();
        int k = 1000;
        int[] nums = new int[k];
        for (int i = 1; i <= k; i++) {
            nums[i - 1] = i;
        }
        for (int num : nums) {
            m.addNum4NinetiethInLimitMem(num);
//            m.addNum4Ninetieth(num);
//            m.addNum4Median(num);
        }

        System.out.printf("" + m.find90th());
//        System.out.printf("" + m.findMedian());
    }

    public void addNum4NinetiethInLimitMem(int num){
        // 将新数添加到小顶堆
        if (minHeap.size() < minSize){
            minHeap.offer(num);
        }else if (minHeap.peek() < num){
            minHeap.poll();
            minHeap.offer(num);
        }
        count++;

        // 小顶堆只存放后10%的元素，多余的元素放到大顶堆，最后可以得到：大顶堆降序的前90%元素，小顶堆存放升序的后10%的元素
        // 如 大顶堆[7,6,5,4,3,2,1], 小顶堆[8,9,10]
        if (minHeap.size() > (count * 1) / 10 + 1) {
            minHeap.poll();
        }

    }

    /**
     * 如果要寻找第90%的数，则在小顶堆存放当前元素中的前90%的元素
     * @param num
     */
    public void addNum4Ninetieth(int num){
        // 将新数添加到小顶堆
        minHeap.offer(num);
        count++;

        // 小顶堆只存放后10%的元素，多余的元素放到大顶堆，最后可以得到：大顶堆降序的前90%元素，小顶堆存放升序的后10%的元素
        // 如 大顶堆[7,6,5,4,3,2,1], 小顶堆[8,9,10]
        if (minHeap.size() > (count * 1) / 10 + 1) {
            maxHeap.offer(minHeap.poll());
        }

    }

    public double find90th(){
        if (!minHeap.isEmpty()){
            return minHeap.peek();
        }
        throw new RuntimeException("None");
    }

    /**
     * 如果是寻找中位数，则在大顶堆和小顶堆中各存储一半的数字
     * @param num
     */
    public void addNum4Median(int num){
        // 将新数据添加到大顶堆，然后将里面最大的元素移到小顶堆
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());

        // 平衡两个堆的元素数量
        if (minHeap.size() > maxHeap.size()){
            maxHeap.offer(minHeap.poll());
        }
    }

    /**
     * 获取中位数：如果两个堆元素数量一致说明是偶数个，则取两个堆顶元素的平均值；否则在大顶堆里
     * @return
     */
    public double findMedian(){
        if (maxHeap.size() > minHeap.size()){
            return maxHeap.peek();
        }else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }
}
