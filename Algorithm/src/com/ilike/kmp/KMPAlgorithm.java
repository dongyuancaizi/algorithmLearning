package com.ilike.kmp;

import java.util.Arrays;

/**
 * KMP算法
 */
public class KMPAlgorithm {

    public static void main(String[] args) {
        //测试暴力匹配算法
        String s1 = "BBC ABCDAB ABCDABCDABDE";
        String s2 = "ABCDABD";
        // String s2 = "ABC";

        int[] next = kmpNext("ABCDABD");
        System.out.println(Arrays.toString(next));
        int i = kmpSearch(s1, s2, next);
        System.out.println(i);

    }

    /**
     * kmp搜索算法
     *
     * @param str1 源字符串
     * @param str2 子串
     * @param next 部分匹配表，子串对应的部分匹配表
     * @return
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        //遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {

            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                //调整j的大小
               j=next[j-1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                //找到了
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获取一个字符串的部分匹配值表
     *
     * @param dest 目标字符串
     * @return
     */
    public static int[] kmpNext(String dest) {
        //创建一个next数组，保存部分匹配值表
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串长度为1，部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                //部分匹配+1；
                j++;
            }
            next[i] = j;
        }
        return next;

    }
}
