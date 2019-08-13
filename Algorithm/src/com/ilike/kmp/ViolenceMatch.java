package com.ilike.kmp;

/**
 * 暴力匹配算法
 */
public class ViolenceMatch {

    public static void main(String[] args) {
        //测试暴力匹配算法
        String s1 = "超强台风利奇马（英语：Super Typhoon Lekima，国际编号：1909，联合台风警报中心：10W，菲律宾大气地球物理和天文管理局：Hanna）为2019年太平洋台风季第9个被命名的风暴。“利奇马”一名由越南提供，意为一种水果";
        String s2 = "联合台风警报中国";
        int i = violenceMatch(s1, s2);
        System.out.println(i);
    }

    /**
     * 暴力匹配算法
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1Length = str1.length();
        int s2Length = str2.length();
        int i = 0;//i指向s1
        int j = 0;//j指向s2
        while (i < s1Length && j < s2Length) {//保证匹配时不越界
            if (s1[i] == s2[j]) {//匹配成功
                i++;
                j++;
            } else {
                //没有匹配成功
                i = i - (j - 1);
                j=0;
            }
        }
        //判断是否匹配成功
        if (j == s2Length) {
            return i - j;
        } else {
            return -1;
        }
    }
}
