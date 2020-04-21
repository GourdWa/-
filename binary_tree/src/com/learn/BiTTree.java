package com.learn;

/**
 * @author ZixiangHu
 * @create 2020-04-21  20:48
 * @description
 */
public class BiTTree {
    private int data;
    private BiTTree lchild;
    private BiTTree rchild;

    public BiTTree() {
    }

    public BiTTree(int data) {
        this.data = data;
        this.lchild = null;
        this.rchild = null;
    }

    public BiTTree(int data, BiTTree lchild, BiTTree rchild) {
        this.data = data;
        this.lchild = lchild;
        this.rchild = rchild;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BiTTree getLchild() {
        return lchild;
    }

    public void setLchild(BiTTree lchild) {
        this.lchild = lchild;
    }

    public BiTTree getRchild() {
        return rchild;
    }

    public void setRchild(BiTTree rchild) {
        this.rchild = rchild;
    }

    @Override
    public String toString() {
        return "BiTNode{" +
                "data=" + data +
                ", lchild=" + lchild +
                ", rchild=" + rchild +
                '}';
    }
}
