package com.ilike.dijkstar;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法
 */
public class DijkstraAlgorithm {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 8, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        //创建图
        Graph graph = new Graph(vertex, matrix);

        graph.showGraop();
        graph.dsj(2);
        graph.showdsj();
    }
}

/**
 * 已访问顶点集合
 */
class VisitedVertex {
    /**
     * 记录各个顶点是否访问过，1表示访问过，0表示未访问
     */
    public int[] already_arr;
    /**
     * 每个下标对应的值为前一个顶点的下标，会动态更新
     */
    public int[] pre_visted;
    /**
     * 记录出发点到其他所有顶点的距离，
     */
    public int[] dis;

    /**
     * 构造器
     *
     * @param length 顶点的个数
     * @param index  出发点的下标
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visted = new int[length];
        this.dis = new int[length];
        //初始化dis数组
        Arrays.fill(dis, 65535);
        //设置出发顶点被访问过
        this.already_arr[index]=1;
        //设置出发顶点的访问距离为0
        this.dis[index] = 0;
    }

    /**
     * 判断index下标对应的顶点是否访问过
     *
     * @param index 下标
     * @return
     */
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    /**
     * 更新出发点到当前节点的距离
     *
     * @param index 节点的下标
     * @param len   距离
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新pre顶点的前驱为index节点
     *
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visted[pre] = index;
    }

    /**
     * 返回出发顶点到index节点的距离
     *
     * @param index
     * @return
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 继续访问并返回新的访问节点
     * @return
     */
    public int updateArray( ){
        int min=65535,index=0;
        for (int i = 0; i <already_arr.length ; i++) {
            if(already_arr[i]==0&&dis[i]<min){
                min=dis[i];
                index=i;
            }
        }
        //更新index被访问过
        already_arr[index]=1;
        return index;
    }
    /**
     * 显示最后的结果
     */
    public void show(){
        System.out.println("==================");
        for (int alerdy:already_arr) {
            System.out.print(alerdy+" ,");
        }
        System.out.println();
        //输出前驱节点
        for (int visted:pre_visted) {
            System.out.print(visted+" ,");
        }
        System.out.println();
        //输出dis
        for (int d:dis) {
            System.out.print(d+" ,");
        }
        System.out.println();
        //最后的最短距离
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count=0;
        for (int i:dis) {
            if(i!=65535){
                System.out.print(vertex[count]+"("+i+")");
            }else{
                System.out.print("N");
            }
            count++;
        }

    }
}

class Graph {
    /**
     * 顶点数组
     */
    private char[] vertex;
    /**
     * 领结矩阵
     */
    private int[][] matrix;
    /**
     * 已经访问的顶点的集合
     */
    private VisitedVertex vv;

    /**
     * 构造方法
     *
     * @param vertex
     * @param matrix
     */
    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showdsj(){
        vv.show();
    }


    /**
     * 显示图
     */
    public void showGraop() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 迪杰斯特拉算法实现
     *
     * @param index
     */
    public void dsj(int index) {
        vv = new VisitedVertex(vertex.length, index);
        //更新index顶点到周围顶点的距离和前驱顶点
        update(index);
        for (int i = 1; i <vertex.length ; i++) {
            //选择并返回新的访问节点
            index =vv.updateArray();
            update(index);
        }
    }

    /**
     * 更新index下标顶点到周围顶点的距离和周围节点的前驱节点
     *
     * @param index
     */
    private void update(int index) {
        int len = 0;
        for (int i = 0; i < matrix[index].length; i++) {
            //出发顶点到index顶点的距离+ 从index顶点到j这个顶点的距离和
            len = vv.getDis(index) + matrix[index][i];
            //如果j这个顶点没有被访问过，而且小于出发顶点到j这个顶点的距离
            if (!vv.in(i) && len < vv.getDis(i)) {
                vv.updatePre(i,index);//更新j这个顶点的前驱为index
                vv.updateDis(i,len);//更新出发顶点到j这个顶点的距离
            }
        }
    }


}
