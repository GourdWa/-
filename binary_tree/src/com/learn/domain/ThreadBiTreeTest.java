package com.learn.domain;

import com.learn.ThreadBiTree;
import com.learn.utils.ThreadBiTreeUtils;

/**
 * @author ZixiangHu
 * @create 2020-04-22  20:56
 * @description
 */
public class ThreadBiTreeTest {
    public static void main(String[] args) {
        int[] nums = {1,-1,3,5,-1,-1,-1};
        ThreadBiTree root = new ThreadBiTree();
        root = ThreadBiTreeUtils.createThreadBiTree(root,nums);
        System.out.println("创建二叉树完成");
        ThreadBiTreeUtils.threadNodes(root);
        System.out.println("线索化二叉树完成");
        ThreadBiTreeUtils.inOrderTraverseThread(root);
    }
}
