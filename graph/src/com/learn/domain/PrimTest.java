package com.learn.domain;

import com.learn.bean.MGraph;
import com.learn.utils.MGraphUtils;

import java.util.Arrays;

/**
 * @author ZixiangHu
 * @create 2020-04-26  12:37
 * @description
 */
public class PrimTest {
    private static final int INFINITY = Integer.MAX_VALUE;

    public static void main(String[] args) {

        int[][] graph = {
                {0, 10, INFINITY, INFINITY, INFINITY, 11, INFINITY, INFINITY, INFINITY},
                {10, 0, 18, INFINITY, INFINITY, INFINITY, 16, INFINITY, 12},
                {INFINITY, INFINITY, 0, 22, INFINITY, INFINITY, INFINITY, INFINITY, 8},
                {INFINITY, INFINITY, 22, 0, 20, INFINITY, INFINITY, 16, 21},
                {INFINITY, INFINITY, INFINITY, 20, 0, 26, INFINITY, 7, INFINITY},
                {11, INFINITY, INFINITY, INFINITY, 26, 0, 17, INFINITY, INFINITY},
                {INFINITY, 16, INFINITY, INFINITY, INFINITY, 17, 0, 19, INFINITY},
                {INFINITY, INFINITY, INFINITY, 16, 7, INFINITY, 19, 0, INFINITY},
                {INFINITY, 12, 8, 21, INFINITY, INFINITY, INFINITY, INFINITY, 0}
        };
        //初始化全部为0
        int[] adjvex = new int[graph.length];
        int[] lowcost = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            lowcost[i] = graph[0][i];
        }

        MGraphUtils.prim(graph, lowcost, adjvex);
        System.out.println(Arrays.toString(lowcost));
        System.out.println(Arrays.toString(adjvex));
    }
}
