package com.ilike.floyd;

import java.util.Arrays;

/**
 * 弗洛伊德算法
 */
public class FloydAlgorithm {

    public static void main(String[] args) {
        //测试创建图
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //创建领结矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};
        Graph graph = new Graph(vertex.length,matrix,vertex);
        graph.floyd();
        graph.show();
    }
}

/**
 * 图
 */
class Graph {
    /**
     * 顶点
     */
    private char[] vertex;
    /**
     * 记录各个顶点出发到各个顶点的距离
     */
    private int[][] dis;
    /**
     * 保存到达目标节点的前驱节点
     */
    private int[][] pre;

    /**
     * 构造方法
     *
     * @param length 大小
     * @param matrix 领结矩阵
     * @param vertex 顶点
     */
    public Graph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        //初始化pre，存放的是前驱节点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    /**
     * 显示dis，pre
     */
    public void show() {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int i = 0; i < dis.length; i++) {
            //现将pre数组输出
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[pre[i][j]] + " ");
            }
            System.out.println();
            //输出dis
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[i]+"到"+vertex[j] +"的最短路径"+dis[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 弗洛伊德算法
     */
    public void floyd(){
      int len=0;
      //中间顶点的遍历
        for (int k = 0; k <dis.length ; k++) {
            //出发节点的遍历
            for (int i = 0; i < dis.length; i++) {
                //终点的遍历
                for (int j = 0; j <dis.length ; j++) {
                    len=dis[i][k]+dis[k][j];
                    if(len<dis[i][j]){
                        //更新距离
                        dis[i][j]=len;
                        //更新前驱节点
                        pre[i][j]= pre[k][j];
                    }
                }
            }
        }
    }
}
