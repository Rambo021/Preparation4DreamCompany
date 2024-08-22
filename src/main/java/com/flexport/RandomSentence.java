package com.flexport;

import java.util.*;

/**
 * @description 随机字符串
 * 输入一个句子和整数k，输出一个随机的含有k个单词的句子
 * 句子中的单词用空格分隔，不含有其他字符
 *   随机选取一个单词作为生成的句子的第一个单词；
 *   随后的单词根据上一个单词后的第一个单词列表进行选择
 * 假设输入的句子是环形的，即最后一个单词的下个单词是第一个单词
 * eg:
 *     input: this is a good sentence and this is a bad sentence
 *     First word is “is”, then the second word can only be “a”， the third word can be either “good” or “bad”
 */
public class RandomSentence {

    public static void main(String[] args) {
        RandomSentence randomSentence = new RandomSentence();

        String sent1 = "hello this a is a flexport interview and this is a hello and that was cool";

        System.out.printf(randomSentence.randomSentence(sent1, 5));
    }


    /**
     * 先遍历sent，将每个单词与其后的第一个单词的存储到map中；value是List
     * 然后随机选取一个index作为第一个单词
     * 之后开始传染选择
     * @param sent
     * @param k
     * @return
     */
    public String randomSentence(String sent, int k){
        Map<String, Set<String>> map = new HashMap<>();

        String[] words = sent.split(" ");
        int l = words.length;
        for (int i = 0; i < l; i++) {
            int nextIndex = (i + 1) % l;
            Set<String> set = map.getOrDefault(words[i], new HashSet<>());
            set.add(words[nextIndex]);
            map.put(words[i], set);
        }

        int firstIndex = new Random().nextInt(l);
        String randomWord = words[firstIndex];
        StringBuffer sb = new StringBuffer(randomWord).append(" ");
        for (int i = 1; i < k; i++) {
            randomWord = randomWord(map.get(randomWord));
            sb.append(randomWord).append(" ");
        }

        return sb.substring(0, sb.length());
    }

    public Map<String, Set<String>> toMap(String sent){
        Map<String, Set<String>> map = new HashMap<>();

        String[] words = sent.split(" ");
        int l = words.length;
        for (int i = 0; i < l; i++) {
            int nextIndex = (i + 1) % l;
            Set<String> set = map.getOrDefault(words[i], new HashSet<>());
            set.add(words[nextIndex]);
        }
        return map;
    }

    public String randomWord(Set<String> words){
        int l = words.size();
        int randomIndex = new Random().nextInt(l);
        return new ArrayList<>(words).get(randomIndex);
    }
}
