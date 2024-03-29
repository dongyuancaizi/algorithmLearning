package com.ilike.dac;

/**
 * 汉诺塔
 */
public class Hanoitower {

    public static void main(String[] args) {
        //测试
        hanoitower(5,'A','B','C');

    }

    /**
     * 汉诺塔的移动算法（使用分治算法）
     *
     * @param num 盘的数量
     * @param a   柱子a
     * @param b   柱子b
     * @param c   柱子c
     */
    public static void hanoitower(int num, char a, char b, char c) {
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从" + a + "->" + c);
        } else {
            //如果我们有num>2个盘，我们总是可以看做是两个盘  1.最下面的一个盘，2.上面的所有盘
            //1.先把上面的盘 A->B,移动过程会使用到c
            hanoitower(num - 1, a, c, b);
            //2.把最下面的盘 A->C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            //3.把B塔的所有盘从 B->C,移动过程会使用到a
            hanoitower(num-1,b,a,c);
        }
    }
}
