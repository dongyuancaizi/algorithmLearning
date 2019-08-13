package com.ilike.horse;

import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;

/**
 * 马踏棋盘（骑士周游）算法
 */
public class HorseChessBoard {
    /**
     * 棋盘的列数
     */
    private final static int X = 8;
    /**
     * 棋盘的行数
     */
    private final static int Y = 8;

    //标记棋盘各个位置是否被访问过
    private static boolean[] visited;
    //标记棋盘的所有位置都被访问过了
    private static boolean finished;

    public static void main(String[] args) {
        //测试骑士周游算法
        int row = 1;
        int column = 1;
        //创建棋盘
        int[][] chessBoard = new int[X][Y];
        visited = new boolean[X * Y];
        long start = Instant.now().getEpochSecond();
        traversalChessBoard(chessBoard, row - 1, column - 1, 1);
        long end = Instant.now().getEpochSecond();
        System.out.println("时间：" + (end - start));
        for (int[] rows : chessBoard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 骑士周游算法
     *
     * @param chessBoard 棋盘
     * @param row        马儿所在位置的行
     * @param cloumn     马儿所在位置的列
     * @param step       马儿是第几步，初始是1
     */
    public static void traversalChessBoard(int[][] chessBoard, int row, int cloumn, int step) {
        chessBoard[row][cloumn] = step;
        //标记该位置已经访问
        visited[row * X + cloumn] = true;
        //获取该位置可以访问的下一个位置的集合
        ArrayList<Point> ps = next(new Point(cloumn, row));
        //对ps所有Point对象的下一个可选的节点进行非递减排序
        sort(ps);
        //遍历ps
        while (!ps.isEmpty()) {
            //取出下一个可以走的位置
            Point p = ps.remove(0);
            //判断该点是否已经访问
            if (!visited[p.y * X + p.x]) {
                //说明换没有访问
                traversalChessBoard(chessBoard, p.y, p.x, step + 1);
            }
        }
        //判断马儿是否完成任务
        if (step < X * Y && !finished) {
            //没有完成
            chessBoard[row][cloumn] = 0;
            visited[row * X + cloumn] = false;
        } else {
            finished = true;
        }
    }


    /**
     * 根据当前的位置，计算马儿换能走那些位置
     *
     * @param curPoint 当前的位置
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<>();
        //创建一个Point
        Point p1 = new Point();
        //位置5
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //位置6
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //位置7
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //位置0
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //位置1
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        } //位置2
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //位置3
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //位置4
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    /**
     * 根据当前这一步的所有下一步的选择位置，进行非递减排序
     */
    public static void sort(ArrayList<Point> ps) {
        ps.sort((x, y) -> Integer.compare(next(x).size(), next(y).size()));
    }

}
