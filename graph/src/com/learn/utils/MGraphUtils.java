package com.learn.utils;

import com.learn.bean.Edge;
import com.learn.bean.MGraph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author ZixiangHu
 * @create 2020-04-25  21:51
 * @description
 */
public class MGraphUtils {
    private boolean[] visited;

    public static MGraph createMGraph() {
        Scanner sc = new Scanner(System.in);
        int nVexs, nEdges;
        System.out.println("请输入顶点数目和边的数目");
        nVexs = sc.nextInt();
        nEdges = sc.nextInt();
        MGraph mGraph = new MGraph(nVexs, nEdges);
        String[] vexs = new String[nVexs];
        System.out.println("请输入顶点信息");
        for (int i = 0; i < nVexs; i++) {
            vexs[i] = sc.nextLine();
        }
        mGraph.setVexs(vexs);
        //初始化邻接矩阵
        int[][] arc = new int[nVexs][nVexs];
        for (int i = 0; i < nVexs; i++) {
            for (int j = 0; j < nVexs; j++) {
                arc[i][j] = Integer.MAX_VALUE;
            }
        }
        int i, j, w;
        //读入每条边的数据
        for (int k = 0; k < nEdges; k++) {
            i = sc.nextInt();
            j = sc.nextInt();
            w = sc.nextInt();
            arc[i][j] = w;
            //因为是无向图，矩阵对称
            arc[j][i] = w;
        }
        mGraph.setArc(arc);
        return mGraph;
    }


    /**
     * DFS
     *
     * @param graph
     */
    public void dfsTraverse(MGraph graph) {
        //visited数组用来记录那些点被访问过了
        // 如果点i被访问了，那么visited[i]为true，否则为false
        visited = new boolean[graph.getNumVertexes()];
        //graph.getNumVertexes()获得图的顶点数目
        for (int i = 0; i < graph.getNumVertexes(); i++) {
            if (!visited[i]) {
                dfs(graph, i);
            }
        }
    }

    private void dfs(MGraph graph, int i) {
        visited[i] = true;
        System.out.println(graph.getVexs()[i]);//输出顶点，也可以做其他操作
        //graph.getArc()[i][j] ，邻接矩阵i点到j点是否相通
        for (int j = 0; j < graph.getNumVertexes(); j++) {
            if (graph.getArc()[i][j] == 1 && !visited[j]) {
                dfs(graph, j);
            }
        }
    }

    /**
     * BFS
     *
     * @param graph
     */
    public void bfsTraverse(MGraph graph) {
        visited = new boolean[graph.getNumVertexes()];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.getNumVertexes(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                System.out.println(graph.getVexs()[i]);
                queue.add(i);
                while (!queue.isEmpty()) {
                    Integer pollEle = queue.poll();
                    for (int j = 0; j < graph.getNumVertexes(); j++) {
                        //将与
                        if (graph.getArc()[pollEle][j] == 1 && !visited[j]) {
                            System.out.println(graph.getVexs()[j]);
                            queue.add(j);
                        }
                    }
                }
            }
        }
    }


    /**
     * lowcost[i]的值为0时代表，节点i已经加入生成树
     *
     * @param graph
     * @param lowcost
     * @param adjvex
     */
    public static void prim(int[][] graph, int[] lowcost, int[] adjvex) {
        int len = lowcost.length;
        for (int i = 1; i < len; i++) {
            int min = Integer.MAX_VALUE;
            int j = 1;
            int k = 0;
            while (j < len) {
                //如果节点j没有加入生成树
                if (lowcost[j] != 0 && min > lowcost[j]) {
                    k = j;
                    min = lowcost[j];
                }
                j++;
            }
            System.out.println(adjvex[k] + "==>" + k);
            lowcost[k] = 0;
            //将k节点加入生成树之后，更新距离
            for (j = 1; j < len; j++) {
                if (lowcost[j] != 0 && graph[k][j] < lowcost[j]) {
                    lowcost[j] = graph[k][j];
                    //代表从k到j的生成树，例如adjvex[3]=7,代表从节点7生成到节点3
                    adjvex[j] = k;
                }
            }
        }
    }


    /**
     * Kruskal算法实现
     * https://blog.csdn.net/junya_zhang/article/details/83584592
     *
     * @param edges
     * @param parent
     */
    public static void kruskal(List<Edge> edges, int[] parent) {
        for (int i = 0; i < edges.size(); i++) {
            int n = find(parent, edges.get(i).getBegin());//从该边的起点开始寻找parent数组中为0的元素的下标，
            int m = find(parent, edges.get(i).getEnd());//从该边的终点点开始寻找parent数组中为0的元素的下标
            if (n != m) {//如果m=n说明如果连接这条边，则从该边的起点和终点最终都会到达同一顶点，即有环
                parent[n] = m;
                System.out.println(edges.get(i).getBegin() + "==>" + edges.get(i).getEnd() + "  " + edges.get(i).getWeight());
            }
        }
    }

    private static int find(int[] parent, int end) {
        //说明end节点已经在生成树里了
        while (parent[end] > 0)
            end = parent[end];
        return end;
    }
}
