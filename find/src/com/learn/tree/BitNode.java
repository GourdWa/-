package com.learn.tree;

/**
 * @author ZixiangHu
 * @create 2020-04-29  16:17
 * @description
 */
public class BitNode {
    private int data;
    private BitNode left;
    private BitNode right;

    public BitNode() {
    }

    public BitNode(int data) {
        this.data = data;
    }

    public BitNode(int data, BitNode left, BitNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BitNode getLeft() {
        return left;
    }

    public void setLeft(BitNode left) {
        this.left = left;
    }

    public BitNode getRight() {
        return right;
    }

    public void setRight(BitNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BitNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
