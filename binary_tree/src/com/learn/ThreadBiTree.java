package com.learn;

/**
 * @author ZixiangHu
 * @create 2020-04-22  20:27
 * @description 线索二叉树，如果lType为false代表指向左子树，否则指向前驱节点；rType同理
 */

public class ThreadBiTree {
    private int data;
    private ThreadBiTree lchild;
    private ThreadBiTree rchild;
    private boolean lType;
    private boolean rType;

    public ThreadBiTree() {
    }

    public ThreadBiTree(int data) {
        this.data = data;
        lchild = null;
        rchild = null;
    }

    public ThreadBiTree(int data, ThreadBiTree lchild, ThreadBiTree rchild, boolean lType, boolean rType) {
        this.data = data;
        this.lchild = lchild;
        this.rchild = rchild;
        this.lType = lType;
        this.rType = rType;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public ThreadBiTree getLchild() {
        return lchild;
    }

    public void setLchild(ThreadBiTree lchild) {
        this.lchild = lchild;
    }

    public ThreadBiTree getRchild() {
        return rchild;
    }

    public void setRchild(ThreadBiTree rchild) {
        this.rchild = rchild;
    }

    public boolean getlType() {
        return lType;
    }

    public void setlType(boolean lType) {
        this.lType = lType;
    }

    public boolean getrType() {
        return rType;
    }

    public void setrType(boolean rType) {
        this.rType = rType;
    }

}
