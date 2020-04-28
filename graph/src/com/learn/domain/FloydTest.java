package com.learn.domain;

import java.util.Arrays;

/**
 * @author ZixiangHu
 * @create 2020-04-27  21:51
 * @description
 */
public class FloydTest {
    private static final int INFINITY = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 5, INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, INFINITY},
                {1, 0, 3, 7, 5, INFINITY, INFINITY, INFINITY, INFINITY},
                {5, 3, 0, INFINITY, 1, 7, INFINITY, INFINITY, INFINITY},
                {INFINITY, 7, INFINITY, 0, 2, INFINITY, 3, INFINITY, INFINITY},
                {INFINITY, 5, 1, 2, 0, 3, 6, 9, INFINITY},
                {INFINITY, INFINITY, 7, INFINITY, 3, 0, INFINITY, 5, INFINITY},
                {INFINITY, INFINITY, INFINITY, 3, 6, INFINITY, 0, 2, 7},
                {INFINITY, INFINITY, INFINITY, INFINITY, 9, 5, 2, 0, 4},
                {INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, 7, 4, 0}
        };
        int[][] distance = graph.clone();
        int[][] trace = new int[graph.length][graph.length];
        floyd(distance, trace);
        for (int i = 0; i < distance.length; i++) {
            System.out.println(Arrays.toString(distance[i]));
        }

    }

    private static void floyd(int[][] distance, int[][] trace) {
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance.length; j++) {
                trace[i][j] = j;
            }
        }

        /**
         * 中转节点为i；起点为j；终点为k
         */
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance.length; j++) {
                for (int k = 0; k < distance.length; k++) {
                    if (distance[j][i] != Integer.MAX_VALUE && distance[i][k] != Integer.MAX_VALUE &&
                            distance[j][k] > distance[j][i] + distance[i][k]) {
                        distance[j][k] = distance[j][i] + distance[i][k];
                        trace[j][k] = trace[j][i];
                    }
                }
            }
        }
    }
}
