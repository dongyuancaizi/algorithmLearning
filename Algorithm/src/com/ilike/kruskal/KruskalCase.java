package com.ilike.kruskal;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 克鲁斯卡尔算法
 */
public class KruskalCase {
    /**
     * 边的个数
     */
    private int edgeNum;
    /**
     * 顶点的个数
     */
    private char[] vertexs;
    /**
     * 领结矩阵
     */
    private int[][] matrix;
    /**
     * 表时两个顶点不能联通
     */
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G',};
        int[][] matrix = {
                {0,   12, INF, INF, INF, 16,  14},
                {12,  0,  10,  INF, INF,  7,  INF},
                {INF, 10,  0,   3,   5,   6,  INF},
                {INF, INF, 3,   0,   4,  INF, INF},
                {INF, INF, 5,   4,   0,   2,  8},
                {16,  7,   6,  INF,  2,   0,  9},
                {14, INF, INF, INF,  8,   9,  0}
        };
        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.print();
        Edata[] edges = kruskalCase.getEdges();
        kruskalCase.sortEdges(edges);
        System.out.println(Arrays.toString(edges));
        kruskalCase.kruskal();

    }

    /**
     * 构造方法
     *
     * @param vertexs
     * @param matrix
     */
    public KruskalCase(char[] vertexs, int[][] matrix) {
        //初始化顶点数和边的个数
        int vlen = vertexs.length;

        //初始化顶点
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }
        //初始化边
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边
        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {
                if (matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }

    }

    public void kruskal(){
        int index=0;//表示最后结果数组的索引
        int[] ends=new int[edgeNum];//用于保存已有最小生成树中每个顶点在生成树中的终点
        //创建结果数组，保存最后的最小生成树
        Edata [] rets=new Edata[edgeNum];
        //获取图中所有边的集合
        Edata [] edges=getEdges();
        System.out.println("边的条数："+edges.length);
        //按边的权值从小到大排序
        sortEdges(edges);
        //遍历edges，将边添加到最小生成树中，判断是否构成了回路，如果没有，就加入，否则，不加入
        for (int i = 0; i <edges.length ; i++) {
           //获取起点
            int p1=getPosition(edges[i].getStart());
            //获取终点
            int p2=getPosition(edges[i].getEnd());

            //获取p1这个顶点在已有最小生成树的终点
            int m=getEnd(ends,p1);
            //获取p2这个顶点在已有最小生成树的终点
            int n=getEnd(ends,p2);
            //是否构成回路
            if(m!=n){
                //没有构成回路
                ends[m]=n;//设置m在已有最小生成树中的终点
                rets[index++]=edges[i];//有一条边加入到rets中
            }
        }
        //统计并打印最小生成树
        System.out.println("最小生成树："+Arrays.toString(rets));
    }

    //打印领结矩阵
    public void print() {
        System.out.println("领结矩阵：");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 对边进行排序
     * @param edges
     */
    public void sortEdges(Edata[] edges){
        edges=Stream.of(edges).sorted(Comparator.comparing(Edata::getWeight)).collect(Collectors.toList()).toArray(edges);
    }

    /**
     *  获取顶点对应的下标
     * @param ch 顶点的值
     * @return
     */
    private int getPosition(char ch){
        for (int i = 0; i < vertexs.length; i++) {
            if(vertexs[i]==ch){
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图中的边，放到Edata[]数组中
     * @return
     */
    public Edata[] getEdges(){
        int index=0;
        Edata[] edata= new Edata[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i+1; j < vertexs.length; j++) {
                if(matrix[i][j]!=INF){
                    edata[index++]=new Edata(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
            
        }
        return edata;
    }

    /**
     * 获取下标为i的顶点的终点
     * @param ends 记录了各个顶点对应的终点是哪个
     * @param i 传入的顶点的下标
     * @return 下标为i的顶点对应的终点的下标
     */
    public int getEnd(int [] ends,int i){
       while (ends[i]!=0){
           i=ends[i];
       }
       return i;
    }
}

/**
 * 用Edata表示一条边
 */
 class Edata{
    /**
     * 边的起点
     */
   private char start;
    /**
     * 边的终点
     */
    private char end;
    /**
     * 边的权值
     */
    private  int weight;

    public Edata(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edata{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }

    public char getStart() {
        return start;
    }

    public void setStart(char start) {
        this.start = start;
    }

    public char getEnd() {
        return end;
    }

    public void setEnd(char end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}