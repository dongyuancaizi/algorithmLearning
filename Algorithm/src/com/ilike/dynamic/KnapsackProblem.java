package com.ilike.dynamic;

/**
 * 背包问题
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        int[] w = {1, 4, 3};//物品的重量
        int[] val = {1500, 3000, 2000};//物品的价值
        int m = 4;//背包的容量
        int n = val.length;//物品的个数


        //创建二维数组
        int[][] v = new int[n + 1][m + 1];

        //为了记录放入商品的情况，我们定义一个二维数组
        int[][] path = new int[n + 1][m + 1];
        //初始化第一行和第一列
        for (int i = 0; i < v.length; i++) {
            //第一列为0
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            //第一行为0
            v[0][i] = 0;
        }

        //动态规划处理
        for (int i = 1; i < v.length; i++) {//不处理第一行
            for (int j = 1; j < v[0].length; j++) {//不处理第一列
                //公式
                if (w[i - 1] > j) {  //因为我们的程序的i是从1开始的，因此原公式中的w[i]要修改成w[i-1]
                    v[i][j] = v[i - 1][j];
                } else {
                    //  v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    //为了记录商品存放背包的情况，我们不能简单的使用上面的公式，需要使用if,else来替代
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        //把当前的情况记录在path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }

            }
        }


        //输出
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
     /*   //输出我们最后放入的那些商品的值
        //遍历path
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                if (path[i][j] == 1) {
                    System.out.printf("第%d个商品放入了背包\n", i);
                }
            }
            System.out.println();
        }*/


        int i = path.length - 1;//行的最大下标
        int j = path[0].length - 1;//列的最大下标
        while (i > 0 && j > 0) {//从path的最后开始找
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入了背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
