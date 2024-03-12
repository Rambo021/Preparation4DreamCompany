package com.flexport.coding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 筹码和卡片游戏
 * 假设有Blue、Green、Red、Black、White的筹码和不同定价的卡片，如Card A需要 2B+3R
 * 玩家持有一定数量的筹码，而不同的卡片有相应的定价
 * 实现玩家Player的purchase的功能，如果能购买返回true，否则返回false
 */
public class TokenCardsGame {
    public static void main(String[] args) {
        Map<String, Integer> costMap = new HashMap<>();
        costMap.put("B", 3);
        costMap.put("U", 3);
        Card A = new Card(costMap);

        Map<String, Integer> tokenMap = new HashMap<>();
        tokenMap.put("B",5);
        tokenMap.put("U",8);
        tokenMap.put("G",3);
        tokenMap.put("R",5);
        tokenMap.put("W",8);
        Player rambo = new Player(tokenMap);

        System.out.printf("A:" + rambo.purchase(A) + ",player:" + rambo.toString());

    }

    static class Card{
        /**
         * Key: U/G/R/B/W
         */
        Map<String, Integer> costMap;

        public Card(Map<String, Integer> costMap) {
            this.costMap = costMap;
        }

        @Override
        public String toString() {
            return "Card{" +
                    "costMap=" + costMap +
                    '}';
        }
    }

    static class Player{

        public boolean purchase(Card card){
            if (card == null){
                return true;
            }

            Map<String, Integer> costMap = card.costMap;
            // temp to store the tokens that minus the cost
            Map<String, Integer> tokenMapTemp = new HashMap<>(this.tokenMap);
            for (Map.Entry<String, Integer> entry : costMap.entrySet()) {
                if (tokenMapTemp.get(entry.getKey()) >= entry.getValue()){
                    tokenMapTemp.put(entry.getKey(), tokenMap.get(entry.getKey()) - entry.getValue());
                }else {
                    // can't purchase
                    return false;
                }
            }

            // can purchase, so tokens minus the cost
            this.tokenMap = tokenMapTemp;
            this.cards.add(card);
            return true;
        }

        /**
         * Key:U/G/R/B/W
         */
        Map<String, Integer> tokenMap;
        List<Card> cards;

        public Player(Map<String, Integer> tokenMap) {
            this.tokenMap = tokenMap;
            this.cards = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Player{" +
                    "tokenMap=" + tokenMap +
                    ", cards=" + cards +
                    '}';
        }
    }
}
