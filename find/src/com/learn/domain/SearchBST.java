package com.learn.domain;

import com.learn.tree.BitNode;
import com.learn.utils.CreateTree;

/**
 * @author ZixiangHu
 * @create 2020-04-29  16:20
 * @description
 */
public class SearchBST {
    //p始终指向查找节点的父节点
    private static BitNode p = null;

    /**
     * 返回要查找的节点，如果存在则返回，不存在则返回null
     * @param node
     * @param val
     * @return
     */
    public static BitNode searchBst(BitNode node, int val) {
        if (node == null)
            return null;
        if (node.getData() == val)
            return node;
        if (node.getData() > val) {
            p = node;
            return searchBst(node.getLeft(), val);
        } else {
            p = node;
            return searchBst(node.getRight(), val);
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{62, 58, 47, 35, -1, 37, -1, -1, 51, -1, -1, -1, 88, 73, -1, -1, 99, 93, -1, -1, -1};
        BitNode root = null;
        root = CreateTree.create(root, nums);
        System.out.println(searchBst(root, 94));
        System.out.println(p);
    }
}
