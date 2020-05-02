package com.learn.domain;

import com.learn.tree.BitNode;

import javax.lang.model.type.NullType;

/**
 * @author ZixiangHu
 * @create 2020-04-29  21:00
 * @description
 */
public class DeleteBST {
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
        if (bitNode == null) {
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


    public static boolean deleteBST(BitNode node, int val) {
        if (node == null)
            return false;
        if (node.getData() == val) {
            return delete(node);
        } else if (val < node.getData()) {
            p = node;
            return deleteBST(node.getLeft(), val);
        } else {
            p = node;
            return deleteBST(node.getRight(), val);
        }
    }

    private static boolean delete(BitNode delNode) {
        if (delNode.getLeft() == null) {
            //如果待删除的节点的左节点为空
            if (p == null) {
                //如果删除的是根节点
                delNode.setData(delNode.getRight().getData());
                delNode.setLeft(delNode.getRight().getLeft());
                delNode.setRight(delNode.getRight().getRight());
            } else if (p.getRight() == delNode) {
                p.setRight(delNode.getRight());
            } else {
                p.setLeft(delNode.getRight());
            }
        } else if (delNode.getRight() == null) {
            //如果待删除的节点的右节点为空
            if (p == null) {
                delNode.setData(delNode.getLeft().getData());
                delNode.setLeft(delNode.getLeft().getLeft());
                delNode.setRight(delNode.getLeft().getRight());
            } else if (p.getRight() == delNode) {
                p.setRight(delNode.getLeft());
            } else {
                p.setLeft(delNode.getLeft());
            }
        } else {
            //如果待删除的节点的左右节点都存在
            BitNode q = delNode;
            BitNode s = delNode.getLeft();
            while (s.getRight() != null) {
                q = s;
                s = s.getRight();
            }
            delNode.setData(s.getData());
            if (q != delNode) {
                q.setRight(s.getLeft());
            } else {
                q.setLeft(s.getLeft());
            }
        }
        return true;
    }

    public static int getHeight(BitNode node) {
        if (node != null) {
            int l = getHeight(node.getLeft());
            int r = getHeight(node.getRight());
            return Math.max(l, r) + 1;
        } else return 0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{58, 88, 47, 73, 99, 35, 51, 93, 29, 37, 49, 56, 36, 48, 50};
        BitNode root = new BitNode(62);
        for (int num : nums) {
            insertBST(root, num);
        }
        p = null;
        System.out.println(root);
        deleteBST(root, 62);
        System.out.println(root);
        System.out.println(getHeight(root));
    }
}
