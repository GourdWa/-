package com.learn.bean;

import java.util.Scanner;

/**
 * @author ZixiangHu
 * @create 2020-04-25  21:40
 * @description
 */
public class MGraph {
    //顶点数
    private int numVertexes;
    //边数
    private int numEdges;
    //顶点表
    private String[] vexs;
    //邻接矩阵
    private int[][] arc;

    public MGraph() {
    }

    public MGraph(int numVertexes, int numEdges) {
        this.numVertexes = numVertexes;
        this.numEdges = numEdges;
        this.vexs = new String[numVertexes];
        this.arc = new int[numVertexes][numVertexes];
    }


    public int getNumVertexes() {
        return numVertexes;
    }

    public void setNumVertexes(int numVertexes) {
        this.numVertexes = numVertexes;
    }

    public int getNumEdges() {
        return numEdges;
    }

    public void setNumEdges(int numEdges) {
        this.numEdges = numEdges;
    }

    public String[] getVexs() {
        return vexs;
    }
    public void setVexs(String[] vexs) {
        this.vexs = vexs;
    }

    public int[][] getArc() {
        return arc;
    }

    public void setArc(int[][] arc) {
        this.arc = arc;
    }
}
