package com.worldcup.adm.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArithmeticUtil {

    /**
     * 计算n的阶乘，即： n! = n * (n-1) * (n-2) * ... * 2 * 1
    * */
    public static long factorial(int n) {
        long nf = 1;
        while (n > 0) {
            nf = nf * n--;
        }
        return nf;
    }

    //排列 A(n,m) = n!/(n-m)!
    //组合 C(n,m) = A(n,m)/m!

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "a b c e a a a uiop");
        map.put("2", "a b c d e jhg");
        map.put("3", "e b c wqeqw");
        map.put("4", "d b c fasdf");
        String[] words = new String[]{"a","b","c","d","e"};
        String article;
        Set<Integer> countSet = new HashSet<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            //计数器count
            int count = 0;
            article = entry.getValue();
            //遍历单词，包含 ，计数器+ 1
            for (String word : words) {
                if (article.contains(word)) {
                    count++;
                    continue;
                }
            }
//            countSet.add(count);
            System.out.printf("文章 %s 包含了 %d 个高频词汇\n", entry.getKey(), count);
        }
    }
}
