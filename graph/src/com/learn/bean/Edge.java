package com.learn.bean;

/**
 * @author ZixiangHu
 * @create 2020-04-26  14:01
 * @description
 */
public class Edge {
    private int begin;
    private int end;
    private int weight;

    public Edge() {
    }

    public Edge(int begin, int end, int weight) {
        this.begin = begin;
        this.end = end;
        this.weight = weight;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "begin=" + begin +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
