package com.booking.onsite.recursion;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ooo
 * @description 酒店花费最小的房间组合
 * 给定一个酒店的房间信息列表，房间信息包含该房间的编号ID、价格和可以入住的人数上限，表示为 {id: 0, price: 180, guests: 1}；
 * 现在有一个旅行团需要在该酒店入住，请你使用Java实现一个算法，求出满足旅行团入住需求的最便宜价格。
 *
 *
 * follow up：求出满足旅行团入住需求的最便宜的房间列表。
 * @date 2024/9/25 10:24:52
 */
public class CheapestRoomInHotel {

    private static List<Room> bestRooms = new ArrayList<>();
    private static int minCost = Integer.MAX_VALUE;

    public static void main(String[] args) {
        CheapestRoomInHotel c = new CheapestRoomInHotel();
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1, 180, 1));
        rooms.add(new Room(2, 200, 2));
        rooms.add(new Room(3, 300, 3));
        rooms.add(new Room(4, 160, 2));
        rooms.add(new Room(5, 150, 5));
        /*rooms.add(new Room(1, 180, 1)); // 1 guest
        rooms.add(new Room(2, 150, 2)); // 2 guests
        rooms.add(new Room(3, 200, 2)); // 2 guests
        rooms.add(new Room(4, 100, 1)); // 1 guest
        rooms.add(new Room(5, 120, 3)); // 3 guests*/

        int requiredGuests = 10;
        List<Room> roomList = c.findCheapestRooms(rooms, requiredGuests);
        System.out.println("" + roomList.stream().mapToInt(p -> p.price).sum());
        System.out.println("" + roomList.stream().map(Objects::toString).collect(Collectors.joining(",")));
    }

    public List<Room> findCheapestRooms(List<Room> rooms, int requiredGuests){
        rooms.sort(Comparator.comparingInt(a -> a.price));
        List<Room> currentRooms = new ArrayList<>();
        backtracking(rooms, requiredGuests, currentRooms, 0, 0);
        return bestRooms;
    }

    private void backtracking(List<Room> rooms, int remainingGuests, List<Room> currentRooms, int currentCost, int start) {
        // 出口：走完一个组合路程，获得了一个当前的花费，如果该花费小于最小花费，则更新
        if (remainingGuests <= 0){
            if (currentCost < minCost){
                minCost = currentCost;
                bestRooms = new ArrayList<>(currentRooms);
            }
            return;
        }

        // 从第i个房间开始遍历房间列表，查找所有的房间组合
        for (int i = start; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            // 剪枝：如果当前花费 大于了 已经查找的最小花费，说明当前路径不可行
            if (currentCost + room.price > minCost){
                continue;
            }

            // 将当前房间加入路径，并继续往下查找
            currentRooms.add(room);
            backtracking(rooms, remainingGuests - room.guests, currentRooms, currentCost + room.price, i + 1);
            // 回溯
            currentRooms.remove(currentRooms.size() - 1);
        }
    }

    public static List<Room> findCheapestRooms3(List<Room> rooms, int totalGuests) {
        // Sort the rooms by price (ascending)
        Collections.sort(rooms, Comparator.comparingInt(room -> room.price));

        // Backtrack to find the best combination of rooms
        backtrack(rooms, totalGuests, new ArrayList<>(), 0, 0);
        return bestRooms;
    }

    private static void backtrack(List<Room> rooms, int remainingGuests, List<Room> currentRooms, int currentCost, int start) {
        // If we have met the guest requirement, check if we have a new minimum cost
        if (remainingGuests <= 0) {
            if (currentCost < minCost) {
                minCost = currentCost;
                bestRooms = new ArrayList<>(currentRooms);
            }
            return;
        }

        // Start from the current room index to avoid rechecking previous rooms
        for (int i = start; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            // If adding this room exceeds the current minimum cost, stop further exploration
            if (currentCost + room.price >= minCost) {
                continue;
            }

            currentRooms.add(room);
            backtrack(rooms, remainingGuests - room.guests, currentRooms, currentCost + room.price, i + 1);
            currentRooms.remove(currentRooms.size() - 1); // Backtrack
        }
    }

    public List<Room> findCheapestRoomsDP(List<Room> rooms, int totalGuests) {
        // 按价格升序排序房间
//        Collections.sort(rooms, Comparator.comparingInt(r -> r.price));

        int n = rooms.size();
        int[][] dp = new int[n + 1][totalGuests + 1];

        // 初始化 dp 数组
        for (int i = 0; i <= totalGuests; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }
        dp[0][0] = 0;

        // 动态规划填充 dp 数组
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= totalGuests; j++) {
                dp[i][j] = dp[i-1][j];
                if (j >= rooms.get(i-1).guests && dp[i-1][j - rooms.get(i-1).guests] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j - rooms.get(i-1).guests] + rooms.get(i-1).price);
                }
            }
        }

        // 如果无法容纳所有客人，返回空列表
        if (dp[n][totalGuests] == Integer.MAX_VALUE) {
            return new ArrayList<>();
        }

        // 回溯找出选择的房间
        List<Room> selectedRooms = new ArrayList<>();
        int i = n, j = totalGuests;
        while (i > 0 && j > 0) {
            if (dp[i][j] != dp[i-1][j]) {
                selectedRooms.add(rooms.get(i-1));
                j -= rooms.get(i-1).guests;
            }
            i--;
        }

        return selectedRooms;
    }

    static class Room{
        int id;
        int price;
        int guests;

        public Room(int id, int price, int guests) {
            this.id = id;
            this.price = price;
            this.guests = guests;
        }

        @Override
        public String toString() {
            return "Room{" +
                    "id=" + id +
                    ", price=" + price +
                    ", guests=" + guests +
                    '}';
        }
    }
}
