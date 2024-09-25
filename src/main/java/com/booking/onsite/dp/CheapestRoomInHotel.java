package com.booking.onsite.dp;

import java.util.ArrayList;
import java.util.List;

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
    /**
     * 典型的01背包问题，使用dp求解
     * dp[i] 表示 使 i 个人入住所需的最小花费
     * dp[0] = 0
     * 初始化：dp[i] = Integer.MAX_VALUE
     * transfer formula：dp[i] =
     * @param rooms
     * @param guestAmount
     * @return
     */
    public int cheapestPrice(List<Room> rooms, int guestAmount){
        int[] dp = new int[guestAmount + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (Room room : rooms) {
            // 对于每个room，检查能够住几个人，比如room最多住5个人而旅行团有12个人，那么dp[12]就可以由dp[7] + room.price得到；
            // 同理，dp[11]可以由dp[6] + room.price得到
            // 条件是 dp[7],dp[6]已经计算出来了
            for (int i = guestAmount; i >= room.guests; i--) {
                if(dp[i - room.guests] != Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i], dp[i - room.guests] + room.price);
                }
            }
        }

        return dp[guestAmount] == Integer.MAX_VALUE ? -1 : dp[guestAmount];
    }

    /**
     * follow up需要求解最小花费的房间列表，那么需要再增加一个二维数组存储已经选择的房间
     * @param rooms
     * @param guestAmount
     * @return
     */
    public List<Room> cheapestRooms(List<Room> rooms, int guestAmount){
        int[] dp = new int[guestAmount + 1];
        // [i, room]表示安排i个人入住时选择的Room id
        List<Room> selectedRooms = new ArrayList<>();

        for (int i = 0; i < guestAmount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (Room room : rooms) {
            for (int i = guestAmount; i >= room.guests; i--){
                if (dp[i - room.guests] != Integer.MAX_VALUE){
                    int newCost = dp[i - room.guests] + room.price;
                    if (newCost < dp[i]){
                        dp[i] = newCost;
                        selectedRooms.add(i, room);
                    }
                }
            }
        }
        if (dp[guestAmount] == Integer.MAX_VALUE){
            return null;
        }
        List<Room> ans = new ArrayList<>();
        int remaining = guestAmount;
        while (remaining > 0 && selectedRooms.get(remaining) != null){
            Room room = selectedRooms.get(remaining);
            ans.add(room);
            remaining -= room.guests;
        }

        return ans;
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
    }
}
