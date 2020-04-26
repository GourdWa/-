package com.learn.domain;

import com.learn.bean.Edge;
import com.learn.utils.MGraphUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author ZixiangHu
 * @create 2020-04-26  14:02
 * @description
 */
public class KruskalTest {
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
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            for (int j = i + 1; j < graph[i].length; j++) {
                if (graph[i][j] != INFINITY) {
                    edges.add(new Edge(i, j, graph[i][j]));
                }
            }
        }
        edges.sort(Comparator.comparingInt(Edge::getWeight));
        System.out.println(edges);

        int[] parent = new int[graph.length];

        MGraphUtils.kruskal(edges,parent);
        System.out.println(Arrays.toString(parent));
    }
}
