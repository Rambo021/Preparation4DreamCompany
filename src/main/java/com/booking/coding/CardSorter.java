package com.booking.coding;

import java.util.*;

/**
 * 如何将一副扑克牌排序，限制条件是只能查看最上面的两张牌，交换最上面的两张牌，或是将最上面的一张牌放到这摞牌的最下面。不考虑牌的花色，请用Java实现
 */
public class CardSorter {
    private Deque<Integer> deck;

    public CardSorter(List<Integer> cards) {
        this.deck = new LinkedList<>(cards);
    }

    public void sort() {
        int n = deck.size();
        for (int i = 0; i < n; i++) {
            // 每次比较上面两张牌，将大的放到最下面；第i次比较得到第i小的数
            // 其中 [n - i - 1, n - 1]是有序的
            for (int j = 0; j < n - 1 - i; j++) {
                swapTop2Cards();
            }
            System.out.println("遍历第" + (i+1) + "次后: " + deck);
            // 上面遍历一轮后，有序的[n - i - 1, n - 1]被移动到了最前面：[0, i)
            // 所以需要将其移动到牌底
            for (int k = 0; k < i; k++) {
                swapTop2Cards();
            }
            System.out.println("遍历第" + (i+1) + "次移动后: " + deck);
            // 将最后一张牌放到最下面
            deck.add(deck.poll());
            System.out.println("遍历第" + (i+1) + "次将最后一张牌放到最下面后: " + deck);
            System.out.println("--------");
        }
    }

    public void swapTop2Cards(){
        int first = deck.poll();
        int second = deck.peek();
        if (first > second) {
            deck.add(first);
        } else {
            deck.poll();
            deck.add(second);
            deck.addFirst(first);
        }
    }
    public List<Integer> getSortedDeck() {
        return new ArrayList<>(deck);
    }

    public static void main(String[] args) {
        List<Integer> cards = Arrays.asList(7, 2, 5, 1, 8, 3, 9, 4, 6);
        CardSorter sorter = new CardSorter(cards);
        System.out.println("原始牌组: " + cards);
        sorter.sort();
        System.out.println("排序后牌组: " + sorter.getSortedDeck());
    }
}