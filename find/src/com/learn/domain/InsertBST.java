package com.learn.domain;

import com.learn.tree.BitNode;
import com.learn.utils.CreateTree;

/**
 * @author ZixiangHu
 * @create 2020-04-29  17:21
 * @description
 */
public class InsertBST {
    //p始终指向查找节点的父节点
    private static BitNode p = null;

    /**
     * 返回要查找的节点，如果存在则返回，不存在则返回null
     *
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

    public static boolean insertBST(BitNode root, int val) {
        //如果原有的二叉搜索树中不存在val值节点
        BitNode bitNode = searchBst(root, val);
        if (searchBst(root, val) == null) {
            //如果p等于null说明原本传入的二叉树就是空树
            if (p == null)
                p = new BitNode(val);
            else if (p.getData() > val)
                p.setLeft(new BitNode(val));
            else
                p.setRight(new BitNode(val));
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
/*        int[] nums = new int[]{62, 58, 47, 35, -1, 37, -1, -1, 51, -1, -1, -1, 88, 73, -1, -1, 99, 93, -1, -1, -1};
        BitNode root = null;
        root = CreateTree.create(root, nums);
        System.out.println(insertBST(root,46));
        System.out.println(root);*/

        int[] nums = new int[]{88, 58, 47, 35, 73, 51, 99, 37, 93};
        BitNode root = new BitNode(62);
        for (int num : nums) {
            insertBST(root,num);
        }
        System.out.println(root);
    }
}
