package com.flexport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 筹码和卡片游戏
 * 假设有Blue、Green、Red、Black、White的筹码和不同定价的卡片，如Card A需要 2B+3R
 * 玩家持有一定数量的筹码，而不同的卡片有相应的定价
 *
 * Step 1:
 * 实现玩家Player的canPurchase的功能，如果能购买返回true，否则返回false
 *
 * Step 2:
 * 实现玩家的purchase功能，完成购买，更新玩家手中的Token和Card
 *
 * Step 3:
 * 万能颜色，可以替代任意颜色
 */
public class TokenCardsGame {
    public static void main(String[] args) {
        Map<String, Integer> costMap = new HashMap<>();
        costMap.put("B", 8);
        costMap.put("U", 9);
        Card A = new Card(costMap);

        Map<String, Integer> tokenMap = new HashMap<>();
        tokenMap.put("B",5);
        tokenMap.put("U",8);
        tokenMap.put("G",3);
        tokenMap.put("R",5);
        tokenMap.put("W",8);
        Player rambo = new Player(tokenMap);
        rambo.goldToken = 5;

        System.out.printf("A:" + rambo.canPurchase(A) + ", so after purchase" + rambo.purchase(A) + ", player:" + rambo);

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
        public boolean canPurchase(Card card){
            if (card == null){
                return true;
            }

            Map<String, Integer> costMap = card.costMap;
            Integer goldTokenTmp = this.goldToken;
            for (Map.Entry<String, Integer> entry : costMap.entrySet()) {
                Integer colorToken = tokenMap.get(entry.getKey());
                if (colorToken + goldTokenTmp < entry.getValue()){
                    // can't purchase
                    return false;
                }else {
                    goldTokenTmp -= colorToken < entry.getValue() ? entry.getValue() - colorToken : 0;
                }
            }
            return true;
        }

        public String purchase(Card card){
            if (card == null || !canPurchase(card)){
                return "";
            }

            Map<String, Integer> costMap = card.costMap;
            // temp to store the tokens that minus the cost
            for (Map.Entry<String, Integer> entry : costMap.entrySet()) {
                Integer colorToken = tokenMap.get(entry.getKey());
                tokenMap.put(entry.getKey(), colorToken < entry.getValue() ? 0 : colorToken - entry.getValue());
                goldToken -= colorToken < entry.getValue() ? entry.getValue() - colorToken : 0;
            }

            // can purchase, so tokens minus the cost
            this.cards.add(card);
            return "";
        }

        /**
         * Key:U/G/R/B/W
         */
        Map<String, Integer> tokenMap;
        Integer goldToken;
        List<Card> cards;

        public Player(Map<String, Integer> tokenMap) {
            this.tokenMap = tokenMap;
            this.cards = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Player{" +
                    "tokenMap=" + tokenMap +
                    ", goldToken=" + goldToken +
                    ", cards=" + cards +
                    '}';
        }
    }
}
