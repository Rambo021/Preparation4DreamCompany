package com.flexport.coding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字词配对游戏
 * 输入一个目标字符串guess和待匹配的字符串target，如果guess中的字符：
 * 1. 在target中且位置一致，则此位置填入字符 G-->green；
 * 2. 在target中但位置不同，则此位置填入字符 Y-->yellow;
 * 3. 不在target中，       则此位置填入字符 B-->black.
 * 输出填入的字符组成的字符串
 * eg: SMILE CLOSE => BYBYG
 *
 * step 2：
 * target和guess可能有重复的字符，那么这些字符在匹配过程中优先满足green再满足yellow，同时被匹配过的单词不可再次使用
 * eg:
 * HAPPY PUPPY => BBGGG
 * HPAPY PUPPY => YBBGG
 *
 * step 3:
 * 输入target和对应的guest list，即BBYYG。。。，输出所有可能得guess组合
 * eg:
 *   HAPPY  BBGGG =>
 *                  第一个字符：除去HAPY的所有大写字符；
 *                  第二个字符：除去HAPY的所有大写字符；
 *                  第三个字符：P
 *                  第四个字符：P
 *                  第五个字符：Y
 *   HPAPY  YBBGG =>
 *                  第一个字符：P or A；
 *                  第二个字符：除去HAPY的所有大写字符；
 *                  第三个字符：除去HAPY的所有大写字符
 *                  第四个字符：P
 *                  第五个字符：Y
 * 即：
 *   G：就是对应位置的字符；
 *   B：就是除了已出现的字符外的所有大写字符；
 *   Y：比较麻烦，需要在已出现的字符中删除已经被G对应的字符和不等于当前位置的字符
 */
public class WordGame {
    public static void main(String[] args) {

        WordGame wordGame = new WordGame();
//        System.out.printf(wordGame.matchWords("HAPPY", "PUPPY"));
        System.out.printf(wordGame.matchWords2("HPAPY", "PUPPY"));
    }

    /**
     * Step 1
     * 将target的字符和对应的index存在map中
     * 然后遍历guess寻找字符所在的index
     * @param target
     * @param guess
     * @return
     */
    public String matchWords(String target, String guess){
        StringBuilder ans = new StringBuilder();
        if (target == null){
            target = "";
        }
        if (guess == null || guess.length() == 0){
            return ans.toString();
        }
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            List<Integer> indexList = map.getOrDefault(c, new ArrayList<>());
            indexList.add(i);
            map.put(c, indexList);
        }

        for (int i = 0; i < guess.length(); i++) {
            List<Integer> indexList = map.get(guess.charAt(i));
            if (indexList == null){
                ans.append("B");
            }else if (indexList.contains(i)){
                ans.append("G");
            }else {
                ans.append("Y");
            }
        }

        return ans.toString();
    }

    public String matchWords2(String target, String guess){
        StringBuilder ans = new StringBuilder();
        if (target.length() != guess.length()){
            return ans.toString();
        }
        int l = guess.length();
        // 先遍历一遍，将两个位置字符一样的标识处理
        int[] matchArr = new int[l];
        for (int i = l - 1; i >= 0; i--){
            matchArr[i] = guess.charAt(i) == target.charAt(i) ? 1 : 0;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length(); i++) {
            // 如果这个位置的字符一样，跳过
            if (matchArr[i] == 1){
                continue;
            }
            // 不一样的话加入map，交由后续进行 yellow 匹配
            char c = target.charAt(i);
            Integer count = map.getOrDefault(c, 0);
            map.put(c, ++count);
        }

        for (int i = 0; i < guess.length(); i++) {
            if (matchArr[i] == 1){
                ans.append("G");
                continue;
            }
            Integer count = map.getOrDefault(guess.charAt(i), 0);
            System.out.printf("" + count);
            if (count == 0){
                ans.append("B");
            }else {
                ans.append("Y");
                map.put(guess.charAt(i), --count);
            }
        }

        return ans.toString();
    }
}
