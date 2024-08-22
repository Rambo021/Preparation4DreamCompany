package com.flexport;

import java.util.*;

public class IntervalOverlap {
    public static List<String> overlapIntervals(List<Interval> intervals) {
        List<String> result = new ArrayList<>();
        Map<Integer, Set<Interval>> pointToIntervals = new HashMap<>();

        // 将每个区间的起始点和终点都记录到 Map 中
        for (Interval interval : intervals) {
            addIntervalToMap(pointToIntervals, interval);
        }

        // 遍历 Map,找到重叠的区间
        for (Map.Entry<Integer, Set<Interval>> entry : pointToIntervals.entrySet()) {
            Set<Interval> intervalsAtPoint = entry.getValue();
            if (intervalsAtPoint.size() > 1) {
                StringBuilder sb = new StringBuilder();
                for (Interval interval : intervalsAtPoint) {
                    sb.append(interval.toString());
                }
                result.add(sb.toString());
            }
        }

        return result;
    }

    private static void addIntervalToMap(Map<Integer, Set<Interval>> pointToIntervals, Interval interval) {
        for (int i = interval.start; i <= interval.end; i++) {
            Set<Interval> intervalsAtPoint = pointToIntervals.getOrDefault(i, new TreeSet<>((a, b) -> a.start - b.start));
            intervalsAtPoint.add(interval);
            pointToIntervals.put(i, intervalsAtPoint);
        }
    }
    private static boolean overlap(Interval a, Interval b) {
        return a.start <= b.end && b.start <= a.end;
    }
    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(0, 4));
        intervals.add(new Interval(3, 6));
        intervals.add(new Interval(6, 7));
        intervals.add(new Interval(9, 13));
        intervals.add(new Interval(10, 11));

        List<String> result = overlapIntervals(intervals);
        System.out.println(result); // Output: ["[0,4][3,6]", "[3,6][6,7]", "[9,13][10,11]"]
    }
    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }
}