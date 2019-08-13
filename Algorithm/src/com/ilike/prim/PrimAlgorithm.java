package com.ilike.prim;

import java.util.Arrays;

/**
 * 普利姆算法
 */
public class PrimAlgorithm {

    public static void main(String[] args) {
        //测试图的创建
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        int[][] weight = {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}};
        //创建MGraph
        MGraph mGraph = new MGraph(verxs);
        //创建MinTree
        MinTree minTree = new MinTree();
        minTree.createMGraph(mGraph, verxs, data, weight);
        //输出
        minTree.showMGraph(mGraph);
        //测试普利姆算法
        minTree.prim(mGraph,1);
    }


}

/**
 * 创建最小生成树,村庄的图
 */
class MinTree {
    //创建图的领结矩阵

    /**
     * @param mGraph 图对象
     * @param verxs  图的顶点个数
     * @param data   图的顶点的值
     * @param weight 图的领结矩阵
     */
    public void createMGraph(MGraph mGraph, int verxs, char[] data, int[][] weight) {

        for (int i = 0; i < verxs; i++) {
            mGraph.data[i] = data[i];
            for (int j = 0; j < verxs; j++) {
                mGraph.weight[i][j] = weight[i][j];
            }
        }
    }

    /**
     * 显示图
     *
     * @param mGraph
     */
    public void showMGraph(MGraph mGraph) {
        for (int[] link : mGraph.weight) {
            System.out.println(Arrays.toString(link));

        }
    }

    /**
     * prim算法，得到生成树
     *
     * @param mGraph 图
     * @param v      图的第几个顶点开始生成
     */
    public void prim(MGraph mGraph, int v) {
        //visited标记顶点是否被访问过，默认都是0,表示没有访问过
        int[] visited = new int[mGraph.verxs];
        //把当前这个节点标记为已访问
        visited[v] = 1;
        //h1和h2标记两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        for (int i = 1; i < mGraph.verxs; i++) {
            //每一次生成的子图和哪个节点的距离最近
            for (int j = 0; j < mGraph.verxs; j++) {//j节点表示被访问过的节点
                for (int k = 0; k < mGraph.verxs; k++) {//k节点表示没有被访问过的节点
                    if (visited[j] == 1 && visited[k] == 0 && mGraph.weight[j][k] < minWeight) {
                        //替换minWeight
                        minWeight = mGraph.weight[j][k];
                        h1 = j;
                        h2 = k;
                    }
                }
            }
            //找到了一条边是最小的
            System.out.println("边<"+mGraph.data[h1]+","+mGraph.data[h2]+">"+"权值:"+minWeight);
            //将当前这个节点标记为一访问
            visited[h2]=1;
            //minWeight重新设置最大值
            minWeight = 10000;
        }


    }

}


/**
 * 图
 */
class MGraph {
    /**
     * 表示图的节点个数
     */
    int verxs;
    /**
     * 存放节点数据
     */
    char[] data;
    /**
     * 领结矩阵，存放边
     */
    int[][] weight;

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
