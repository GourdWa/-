package com.learn.utils;

import com.learn.ThreadBiTree;

/**
 * @author ZixiangHu
 * @create 2020-04-22  20:31
 * @description 二叉树中序线索化
 */
public class ThreadBiTreeUtils {
    //总是保留前一个节点
    private static ThreadBiTree pre = null;
    private static int i = -1;

    /**
     * 线索化二叉树
     *
     * @param node
     */
    public static void threadNodes(ThreadBiTree node) {
        if (node != null) {
            threadNodes(node.getLchild());
            //先处理当前节点的前驱节点（左指针）
            if (node.getLchild() == null) {
                //当前节点的左指针指向前驱节点
                node.setLchild(pre);
                node.setlType(true);
            }
            if (pre != null && pre.getRchild() == null) {
                //后继节点
                pre.setRchild(node);
                pre.setrType(true);
            }
            pre = node;
            threadNodes(node.getRchild());
        }
    }

    public static void inOrderTraverseThread(ThreadBiTree node) {
        while (node != null) {
            while (!node.getlType()) {
                node = node.getLchild();
            }
            System.out.print(node.getData() + " ");
            while (node.getrType()) {
                node = node.getRchild();
                System.out.print(node.getData() + " ");
            }
            node = node.getRchild();
        }
    }

    /**
     * 创建二叉树（未线索化）
     *
     * @param node
     * @param nums
     * @return
     */
    public static ThreadBiTree createThreadBiTree(ThreadBiTree node, int[] nums) {
        if (i < nums.length) {
            i += 1;
            if (nums[i] != -1) {
                node = new ThreadBiTree(nums[i]);
                node.setLchild(createThreadBiTree(node.getLchild(), nums));
                node.setRchild(createThreadBiTree(node.getRchild(), nums));
            } else {
                node = null;
            }
        }
        return node;
    }

}
