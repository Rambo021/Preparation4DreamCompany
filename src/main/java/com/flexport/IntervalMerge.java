package com.flexport;

import java.util.*;

public class IntervalMerge {

    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        // 检查当前区间是否与另一个区间重叠
        public boolean overlapsWith(Interval other) {
            return this.end > other.start && this.start < other.end;
        }

        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }

    public static void main(String[] args) {
        List<Interval> intervals = Arrays.asList(
                new Interval(0, 4),
                new Interval(3, 6),
                new Interval(6, 7),
                new Interval(9, 13),
                new Interval(10, 11),
                new Interval(15, 100)
        );

        List<String> result = findOverlappingIntervals3(intervals);
        System.out.println(result);
    }

    public static List<String> findOverlappingIntervals(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) return new ArrayList<>();

        // Sort intervals based on their start values
        intervals.sort(Comparator.comparingInt(i -> i.start));

        List<String> overlappingIntervals = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        // Start with the first interval and compare it with the next ones
        Interval prev = intervals.get(0);
        sb.append(prev.toString());

        for (int i = 1; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            if (prev.end >= current.start) { // Check for overlap
                // Append the current interval to the existing string
                sb.append(current);
            } else {
                // If the previous sb has more than one interval, it's an overlapping group
                if (sb.length() > prev.toString().length()) {
                    overlappingIntervals.add(sb.toString());
                }
                // Reset the StringBuilder for the next group of intervals
                sb.setLength(0);
                sb.append(current);
            }
            prev = current; // Move to the next interval
        }
        // Check for the last group of intervals
        if (sb.length() > prev.toString().length()) {
            overlappingIntervals.add(sb.toString());
        }

        return overlappingIntervals;
    }

    public static List<String> findOverlappingIntervals2(List<Interval> intervals) {
        List<String> overlappingIntervals = new ArrayList<>();

        for (int i = 0; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            StringBuilder sb = new StringBuilder();
            boolean hasOverlap = false;

            for (int j = 0; j < intervals.size(); j++) {
                if (i != j && current.overlapsWith(intervals.get(j))) {
                    // 如果还没有添加当前区间，先添加它
                    if (!hasOverlap) {
                        sb.append(current.toString());
                        hasOverlap = true;
                    }
                    // 然后添加重叠的区间
                    sb.append(intervals.get(j).toString());
                }
            }

            if (hasOverlap) {
                overlappingIntervals.add(sb.toString());
            }
        }

        return overlappingIntervals;
    }

    public static List<String> findOverlappingIntervals3(List<Interval> intervals) {
        List<String> overlappingIntervals = new ArrayList<>();
        List<Map<Integer, Interval>> intervalMaps = new ArrayList<>();

        // 为每个区间创建映射
        for (Interval interval : intervals) {
            Map<Integer, Interval> map = new HashMap<>();
            for (int i = interval.start; i < interval.end; i++) {
                map.put(i, interval);
            }
            intervalMaps.add(map);
        }

        // 遍历每个区间，去所有映射里查找，如果找到则说明区间重叠
        for (int i = 0; i < intervals.size(); i++) {
            Interval current = intervals.get(i);
            for (int j = i + 1; j < intervals.size(); j++) {
                Interval other = intervals.get(j);
                boolean overlapFound = false;
                for (int k = other.start; k < other.end && !overlapFound; k++) {
                    if (intervalMaps.get(i).get(k) != null) {
                        overlappingIntervals.add(current.toString() + other);
                        overlapFound = true;
                    }
                }
            }
        }

        return overlappingIntervals;
    }
}
