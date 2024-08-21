package com.booking.coding;

import javafx.util.Pair;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ooo
 * @description 在大量日志中筛选出最近的半小时内的日志
 *
 * we got the black box from airoplace crach, There is huge set of log files with contents unsorted. We need to fetch the lastet half an hour log. desig an suitable algorithm to fetch lastest half an hour log statement. the formate of log statement would be
 * <DD-MM-YYYY HH:MM:SS:sss> <LogStatement>
 * example - 02-01-2014 10:02:01:001 abcd
 *
 * @date 2024/8/21 19:41:53
 */
public class LatestLogsInHugeSet {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SSS");
    public static void main(String[] args) {
        String[] logs = {"02-01-2014 10:02:01:001 abcd", "02-01-2015 10:02:01:001 cc", "02-01-2015 10:02:02:001 abcd", "02-01-2015 11:02:01:001 dd"};
        List<String> latestLogEntries = latestLogsInHalfHour(logs);
        for (String entry : latestLogEntries) {
            System.out.println(entry);
        }
    }


    /**
     * 只遍历一次日志，在遍历时实时更新最新时间 latestTimestamp；
     * 并用大顶堆存储Pair(ts, String)
     * 每次latestTimestamp更新时都需要将大顶堆里大于latestTimestamp的Pair移除，以保证堆里只有最近的半小时内的日志
     * @param logs
     * @return
     */
    public static List<String> latestLogsInHalfHour(String[] logs){
        List<String> retList = new ArrayList<>();

        PriorityQueue<Pair<Long, String>> maxHeap = new PriorityQueue<>((p1, p2) -> p2.getKey().compareTo(p1.getKey()));
        Long latestTS = null, halfHourTS = null;

        for (String log : logs) {
            String tsStr = log.substring(0, 23);
            try {
                Date date = DATE_FORMAT.parse(tsStr);
                Long ts = date.getTime();
                // 如果当前时间比latestDate新，则更新
                if (latestTS == null || ts.compareTo(latestTS) > 0){
                    latestTS = ts;
                    halfHourTS = latestTS - 30 * 60 * 1000;

                    // 移除大堆顶中超过最近半小时的数据
                    while (!maxHeap.isEmpty() && maxHeap.peek().getKey() < halfHourTS){
                        maxHeap.poll();
                    }
                }

                // 如果当前日志的时间在最近的半小时内，则入堆
                if (ts > halfHourTS){
                    maxHeap.offer(new Pair<>(ts, log));
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        // 头插法，保证最新的日志在最前面
        while (!maxHeap.isEmpty()){
            retList.add(0, maxHeap.poll().getValue());
        }

        return retList;
    }
}
