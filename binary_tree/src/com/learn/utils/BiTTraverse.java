package com.learn.utils;

import com.learn.BiTTree;

import java.util.*;

/**
 * @author ZixiangHu
 * @create 2020-04-21  20:56
 * @description
 */
public class BiTTraverse {

    /**
     * 二叉树递归前序遍历
     *
     * @param tree
     * @param list 前序遍历的结果
     * @return
     */
    public static void preOrderTraverseByRecursion(BiTTree tree, List<Integer> list) {
        if (tree != null) {
            list.add(tree.getData());
            preOrderTraverseByRecursion(tree.getLchild(), list);
            preOrderTraverseByRecursion(tree.getRchild(), list);
        }
    }

    /**
     * 二叉树的非递归前序遍历
     *
     * @param tree
     * @return 前序遍历的结果
     */
    public static List<Integer> preOrderTraverse(BiTTree tree) {
        Deque<BiTTree> stack = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        BiTTree tNode = tree;
        while (tNode != null || !stack.isEmpty()) {
            while (tNode != null) {
                list.add(tNode.getData());
                stack.push(tNode);
                tNode = tNode.getLchild();
            }
            if (!stack.isEmpty()) {
                BiTTree topNode = stack.pop();
                tNode = topNode.getRchild();
            }
        }
        return list;
    }

    /**
     * 二叉树递归中序遍历
     *
     * @param tree
     * @param list
     */
    public static void inOrderTraverseByRecursion(BiTTree tree, List<Integer> list) {
        if (tree != null) {
            inOrderTraverseByRecursion(tree.getLchild(), list);
            list.add(tree.getData());
            inOrderTraverseByRecursion(tree.getRchild(), list);
        }
    }

    /**
     * 二叉树的非递归中序遍历
     *
     * @param tree
     * @return
     */
    public static List<Integer> inOrderTraverse(BiTTree tree) {
        List<Integer> list = new ArrayList<>();
        Deque<BiTTree> stack = new LinkedList<>();
        BiTTree tNode = tree;
        while (tNode != null || !stack.isEmpty()) {
            while (tNode != null) {
                stack.push(tNode);
                tNode = tNode.getLchild();
            }
            if (!stack.isEmpty()) {
                BiTTree topNode = stack.pop();
                list.add(topNode.getData());
                tNode = topNode.getRchild();
            }

        }
        return list;
    }

    /**
     * 二叉树递归后序遍历
     *
     * @param tree
     * @param list
     */
    public static void postOrderTraverseByRecursion(BiTTree tree, List<Integer> list) {
        if (tree != null) {
            postOrderTraverseByRecursion(tree.getLchild(), list);
            postOrderTraverseByRecursion(tree.getRchild(), list);
            list.add(tree.getData());
        }
    }

    /**
     * 二叉树的非递归后序遍历
     *
     * @param tree
     * @return
     */
    public static List<Integer> postOrderTraverse(BiTTree tree) {
        List<Integer> list = new ArrayList<>();
        Deque<BiTTree> stack = new LinkedList<>();
        BiTTree curNode = null;
        BiTTree preNode = null;
        stack.add(tree);
        while (!stack.isEmpty()) {
            curNode = stack.peek();
            if ((curNode.getRchild() == null && curNode.getLchild() == null) ||
                    (preNode != null && (preNode == curNode.getLchild() || preNode == curNode.getRchild()))) {
                list.add(curNode.getData());
                stack.pop();
                preNode = curNode;
            } else {
                if (curNode.getRchild() != null)
                    stack.push(curNode.getRchild());
                if (curNode.getLchild() != null)
                    stack.push(curNode.getLchild());
            }
        }
        return list;
    }
}
